package medplatform.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import medplatform.entities.MonitoredData;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import medplatform.dtos.PatientDTO;
import medplatform.repositories.MonitoredDataRepository;
import medplatform.services.PatientService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Service
public class RabbitMQListener implements MessageListener {

    public ObjectMapper objectMapper = new ObjectMapper();
    private final PatientService patientService;
    private final MonitoredDataRepository monitoredDataRepository;
    private final SimpMessageSendingOperations socketTemplate;

    public RabbitMQListener(PatientService patientService, MonitoredDataRepository monitoredDataRepository, SimpMessageSendingOperations socketTemplate) {
        this.patientService = patientService;
        this.socketTemplate = socketTemplate;
        this.monitoredDataRepository = monitoredDataRepository;
    }

    public void onMessage(Message message) {
        String data = new String(message.getBody());
        MonitoredData monitoredData = new MonitoredData();

        try {
            monitoredData = objectMapper.readValue(data, MonitoredData.class);
            monitoredData.setActivity(monitoredData.getActivity());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime start = LocalDateTime.parse(monitoredData.getStart(), formatter);
        LocalDateTime end = LocalDateTime.parse(monitoredData.getEnd(), formatter);

        long minutesDiff = ChronoUnit.MINUTES.between(start, end);

        PatientDTO patientDTO = null;
        String alert = null;

        if(monitoredData.getActivity().equals("Sleeping") && minutesDiff > 420) {
            patientDTO = patientService.findPatientById(monitoredData.getPatient());
            alert = "Sleeping period is longer than 7 hours for " + patientDTO.getUsername();
        }
        else if(monitoredData.getActivity().equals("Leaving") && minutesDiff > 300){
            patientDTO = patientService.findPatientById(monitoredData.getPatient());
            alert = "Leaving activity is longer than 5 hours for " + patientDTO.getUsername();
        }
        else if(monitoredData.getActivity().equals("Toileting") && minutesDiff > 30){
            patientDTO = patientService.findPatientById(monitoredData.getPatient());
            alert = "Toileting activity is longer than 30 minutes for " + patientDTO.getUsername();
        }
        else if(monitoredData.getActivity().equals("Grooming") && minutesDiff > 30){
            patientDTO = patientService.findPatientById(monitoredData.getPatient());
            alert = "Grooming activity is longer than 30 minutes for " + patientDTO.getUsername();
        }
        else if(monitoredData.getActivity().equals("Showering") && minutesDiff > 30){
            patientDTO = patientService.findPatientById(monitoredData.getPatient());
            alert = "Showering activity is longer than 30 minutes for " + patientDTO.getUsername();
        }

        System.out.println("Received - " + monitoredData.toString());

        monitoredDataRepository.save(monitoredData);

        if(alert != null) {
            System.out.println("Alert: " + alert + " to caregiver:" + patientDTO.getCaregiver().getUsername());
            socketTemplate.convertAndSend("/queue/" + patientDTO.getCaregiver().getUsername(), alert);
        }
    }

}
