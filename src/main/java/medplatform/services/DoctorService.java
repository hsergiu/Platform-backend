package medplatform.services;

import medplatform.dtos.builders.DoctorBuilder;
import medplatform.entities.Doctor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import medplatform.controllers.handlers.exceptions.model.ResourceNotFoundException;
import medplatform.dtos.DoctorDTO;
import medplatform.dtos.DoctorDetailsDTO;
import medplatform.repositories.DoctorRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DoctorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorService.class);
    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<DoctorDTO> findDoctors() {
        List<Doctor> doctorList = doctorRepository.findAll();
        return doctorList.stream()
                .map(DoctorBuilder::toDoctorDTO)
                .collect(Collectors.toList());
    }

    public DoctorDTO findDoctorById(UUID id) {
        Optional<Doctor> prosumerOptional = doctorRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Doctor with id {} was not found in db", id);
            throw new ResourceNotFoundException(Doctor.class.getSimpleName() + " with id: " + id);
        }
        return DoctorBuilder.toDoctorDTO(prosumerOptional.get());
    }

    public UUID insert(DoctorDetailsDTO doctorDTO) {
        Doctor doctor = DoctorBuilder.toEntity(doctorDTO);
        doctor = doctorRepository.save(doctor);
        LOGGER.debug("Doctor with id {} was inserted in db", doctor.getId());
        return doctor.getId();
    }

    public UUID update(UUID id, DoctorDetailsDTO doctorDTO) {
        Doctor doctor = doctorRepository.findById(id).orElse(null);
        Doctor aux = DoctorBuilder.toEntity(doctorDTO);
        doctor.setUsername(aux.getUsername());
        doctor.setPassword(aux.getPassword());
        doctor = doctorRepository.save(doctor);
        LOGGER.debug("Doctor with id {} was updated in db", doctor.getId());
        return doctor.getId();
    }

    public UUID delete(UUID id) {
        Doctor doctor = doctorRepository.findById(id).orElse(null);
        doctorRepository.delete(doctor);
        LOGGER.debug("Doctor with id {} was removed from the db", doctor.getId());
        return doctor.getId();
    }

}
