package medplatform.services;

import medplatform.dtos.builders.MedicationPlanBuilder;
import medplatform.entities.Medication;
import medplatform.entities.MedicationPlan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import medplatform.controllers.handlers.exceptions.model.ResourceNotFoundException;
import medplatform.dtos.MedicationPlanDTO;
import medplatform.dtos.MedicationPlanDetailsDTO;
import medplatform.repositories.MedicationPlanRepository;
import medplatform.repositories.MedicationRepository;
import medplatform.rpc.MedicationPlanServiceInterface;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MedicationPlanService implements MedicationPlanServiceInterface {
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicationPlanService.class);
    private final MedicationPlanRepository medicationPlanRepository;
    private final MedicationRepository medicationRepository;
    private final SimpMessageSendingOperations socketTemplate;

    @Autowired
    public MedicationPlanService(MedicationPlanRepository medicationPlanRepository, MedicationRepository medicationRepository, SimpMessageSendingOperations socketTemplate) {
        this.medicationPlanRepository = medicationPlanRepository;
        this.medicationRepository = medicationRepository;
        this.socketTemplate = socketTemplate;
    }

    @Override
    public List<MedicationPlan> findPlansByPatient(String name){
        List<MedicationPlan> medicationPlanList = medicationPlanRepository.findAll();
        medicationPlanList.removeIf(plan -> !plan.getPatient().getUsername().equals(name));
        return medicationPlanList;
    }

    @Override
    public MedicationPlan deleteTaken(Medication medication, MedicationPlan medicationPlan){
        List<Medication> medications = medicationPlan.getMedications();
        medications.remove(medication);
        medicationPlan.setMedications(medications);

        medicationPlanRepository.save(medicationPlan);
        return medicationPlan;
    }

    @Override
    public void informNotTaken(String name, Medication medication){
        String message = name + " did not take " + medication.getName() + " within the interval";

        System.out.println(message);
        socketTemplate.convertAndSend("/topic", message);
    }

    public List<MedicationPlanDTO> findMedicationPlans() {
        List<MedicationPlan> medicationPlanList = medicationPlanRepository.findAll();
        return medicationPlanList.stream()
                .map(MedicationPlanBuilder::toMedicationPlanDTO)
                .collect(Collectors.toList());
    }

    public MedicationPlanDTO findMedicationPlanById(UUID id) {
        Optional<MedicationPlan> prosumerOptional = medicationPlanRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("MedicationPlan with id {} was not found in db", id);
            throw new ResourceNotFoundException(MedicationPlan.class.getSimpleName() + " with id: " + id);
        }
        return MedicationPlanBuilder.toMedicationPlanDTO(prosumerOptional.get());
    }

    public UUID insert(MedicationPlanDetailsDTO medicationPlanDTO) {
        MedicationPlan medicationPlan = MedicationPlanBuilder.toEntity(medicationPlanDTO);
        medicationPlan = medicationPlanRepository.saveAndFlush(medicationPlan);
        LOGGER.debug("MedicationPlan with id {} was inserted in db", medicationPlan.getId());
        return medicationPlan.getId();
    }

    public UUID update(UUID id, MedicationPlanDetailsDTO medicationPlanDTO) {
        MedicationPlan medicationPlan = medicationPlanRepository.findById(id).orElse(null);
        if(medicationPlan == null){
            return null;
        }
        MedicationPlan aux = MedicationPlanBuilder.toEntity(medicationPlanDTO);
        medicationPlan.setIntakeinterval(aux.getIntakeinterval());
        medicationPlan.setPeriod(aux.getPeriod());
        medicationPlan.setPatient(aux.getPatient());
        medicationPlan = medicationPlanRepository.save(medicationPlan);
        LOGGER.debug("MedicationPlan with id {} was updated in db", medicationPlan.getId());
        return medicationPlan.getId();
    }

    public UUID delete(UUID id) {
        MedicationPlan medicationPlan = medicationPlanRepository.findById(id).orElse(null);
        if(medicationPlan == null) {
            return null;
        }
        medicationPlanRepository.delete(medicationPlan);
        LOGGER.debug("MedicationPlan with id {} was removed from the db", medicationPlan.getId());
        return medicationPlan.getId();
    }

}
