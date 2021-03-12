package medplatform.services;

import medplatform.dtos.builders.MedicationBuilder;
import medplatform.entities.Medication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import medplatform.controllers.handlers.exceptions.model.ResourceNotFoundException;
import medplatform.dtos.MedicationDTO;
import medplatform.dtos.MedicationDetailsDTO;
import medplatform.repositories.MedicationRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MedicationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicationService.class);
    private final MedicationRepository medicationRepository;

    @Autowired
    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public List<MedicationDTO> findMedications() {
        List<Medication> medicationList = medicationRepository.findAll();
        return medicationList.stream()
                .map(MedicationBuilder::toMedicationDTO)
                .collect(Collectors.toList());
    }

    public MedicationDTO findMedicationById(UUID id) {
        Optional<Medication> prosumerOptional = medicationRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Medication with id {} was not found in db", id);
            throw new ResourceNotFoundException(Medication.class.getSimpleName() + " with id: " + id);
        }
        return MedicationBuilder.toMedicationDTO(prosumerOptional.get());
    }

    public UUID insert(MedicationDetailsDTO medicationDTO) {
        Medication medication = MedicationBuilder.toEntity(medicationDTO);
        medication = medicationRepository.save(medication);
        LOGGER.debug("Medication with id {} was inserted in db", medication.getId());
        return medication.getId();
    }

    public UUID update(UUID id, MedicationDetailsDTO medicationDTO) {
        Medication medication = medicationRepository.findById(id).orElse(null);
        if(medication == null){
            return null;
        }
        Medication aux = MedicationBuilder.toEntity(medicationDTO);
        medication.setName(aux.getName());
        medication.setSideEffects(aux.getSideEffects());
        medication.setDosage(aux.getDosage());
        medication.setMedicationPlans(aux.getMedicationPlans());
        medication = medicationRepository.save(medication);
        LOGGER.debug("Medication with id {} was updated in db", medication.getId());
        return medication.getId();
    }

    public UUID delete(UUID id) {
        Medication medication = medicationRepository.findById(id).orElse(null);
        if(medication == null) {
            return null;
        }
        medicationRepository.delete(medication);
        LOGGER.debug("Medication with id {} was removed from the db", medication.getId());
        return medication.getId();
    }

}
