package medplatform.services;

import medplatform.dtos.builders.CaregiverBuilder;
import medplatform.entities.Caregiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import medplatform.controllers.handlers.exceptions.model.ResourceNotFoundException;
import medplatform.dtos.CaregiverDTO;
import medplatform.dtos.CaregiverDetailsDTO;
import medplatform.repositories.CaregiverRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CaregiverService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CaregiverService.class);
    private final CaregiverRepository caregiverRepository;

    @Autowired
    public CaregiverService(CaregiverRepository caregiverRepository) {
        this.caregiverRepository = caregiverRepository;
    }

    public List<CaregiverDTO> findCaregivers() {
        List<Caregiver> caregiverList = caregiverRepository.findAll();
        return caregiverList.stream()
                .map(CaregiverBuilder::toCaregiverDTO)
                .collect(Collectors.toList());
    }

    public CaregiverDTO findCaregiverById(UUID id) {
        Optional<Caregiver> prosumerOptional = caregiverRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Caregiver with id {} was not found in db", id);
            throw new ResourceNotFoundException(Caregiver.class.getSimpleName() + " with id: " + id);
        }
        return CaregiverBuilder.toCaregiverDTO(prosumerOptional.get());
    }

    public UUID insert(CaregiverDetailsDTO caregiverDTO) {
        Caregiver caregiver = CaregiverBuilder.toEntity(caregiverDTO);
        caregiver = caregiverRepository.save(caregiver);
        LOGGER.debug("Caregiver with id {} was inserted in db", caregiver.getId());
        return caregiver.getId();
    }

    public UUID update(UUID id, CaregiverDetailsDTO caregiverDTO) {
        Caregiver caregiver = caregiverRepository.findById(id).orElse(null);
        if(caregiver == null) {
            return null;
        }
        Caregiver aux = CaregiverBuilder.toEntity(caregiverDTO);
        caregiver.setUsername(aux.getUsername());
        caregiver.setPassword(aux.getPassword());
        caregiver.setBirthdate(aux.getBirthdate());
        caregiver.setAddress(aux.getAddress());
        caregiver.setGender(aux.getGender());
        caregiver = caregiverRepository.save(caregiver);
        LOGGER.debug("Caregiver with id {} was updated in db", caregiver.getId());
        return caregiver.getId();
    }

    public UUID delete(UUID id) {
        Caregiver caregiver = caregiverRepository.findById(id).orElse(null);
        if(caregiver == null) {
            return null;
        }
        caregiverRepository.delete(caregiver);
        LOGGER.debug("Caregiver with id {} was removed from the db", caregiver.getId());
        return caregiver.getId();
    }

}
