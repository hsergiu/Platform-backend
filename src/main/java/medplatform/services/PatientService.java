package medplatform.services;

import medplatform.entities.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import medplatform.controllers.handlers.exceptions.model.ResourceNotFoundException;
import medplatform.dtos.PatientDTO;
import medplatform.dtos.PatientDetailsDTO;
import medplatform.dtos.builders.PatientBuilder;
import medplatform.repositories.PatientRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PatientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientService.class);
    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientDTO> findPatients() {
        List<Patient> patientList = patientRepository.findAll();
        return patientList.stream()
                .map(PatientBuilder::toPatientDTO)
                .collect(Collectors.toList());
    }

    public PatientDTO findPatientById(UUID id) {
        Optional<Patient> prosumerOptional = patientRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Patient with id {} was not found in db", id);
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with id: " + id);
        }
        return PatientBuilder.toPatientDTO(prosumerOptional.get());
    }

    public UUID insert(PatientDetailsDTO patientDTO) {
        Patient patient = PatientBuilder.toEntity(patientDTO);
        patient = patientRepository.save(patient);
        LOGGER.debug("Patient with id {} was inserted in db", patient.getId());
        return patient.getId();
    }

    public UUID update(UUID id, PatientDetailsDTO patientDTO) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if(patient == null){
            return null;
        }
        Patient aux = PatientBuilder.toEntity(patientDTO);
        patient.setUsername(aux.getUsername());
        patient.setPassword(aux.getPassword());
        patient.setBirthdate(aux.getBirthdate());
        patient.setAddress(aux.getAddress());
        patient.setGender(aux.getGender());
        patient.setMedicalRecord(aux.getMedicalRecord());
        patient.setCaregiver(aux.getCaregiver());
        patient = patientRepository.save(patient);
        LOGGER.debug("Patient with id {} was updated in db", patient.getId());
        return patient.getId();
    }

    public UUID delete(UUID id) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if(patient == null) {
            return null;
        }
        patientRepository.delete(patient);
        LOGGER.debug("Patient with id {} was removed from the db", patient.getId());
        return patient.getId();
    }

}
