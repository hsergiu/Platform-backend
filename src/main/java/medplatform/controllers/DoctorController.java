package medplatform.controllers;


import medplatform.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import medplatform.dtos.DoctorDTO;
import medplatform.dtos.DoctorDetailsDTO;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping()
    public ResponseEntity<List<DoctorDTO>> getDoctors() {
        List<DoctorDTO> dtos = doctorService.findDoctors();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping(value="/add")
    public ResponseEntity<UUID> insertDoctor(@Valid @RequestBody DoctorDetailsDTO doctorDTO) {
        UUID doctorID = doctorService.insert(doctorDTO);
        return new ResponseEntity<>(doctorID, HttpStatus.CREATED);
    }

    @PutMapping(value="/update/{id}")
    public ResponseEntity<UUID> updateDoctor(@PathVariable("id") UUID id, @Valid @RequestBody DoctorDetailsDTO doctorDTO) {
        UUID doctorID = doctorService.update(id, doctorDTO);
        return new ResponseEntity<>(doctorID, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<UUID> deleteDoctor(@PathVariable("id") UUID id) {
        UUID doctorID = doctorService.delete(id);
        return new ResponseEntity<>(doctorID, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DoctorDTO> getDoctor(@PathVariable("id") UUID doctorId) {
        DoctorDTO dto = doctorService.findDoctorById(doctorId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
