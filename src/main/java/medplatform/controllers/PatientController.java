package medplatform.controllers;


import medplatform.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import medplatform.dtos.PatientDTO;
import medplatform.dtos.PatientDetailsDTO;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/patient")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PreAuthorize("hasAuthority('doctor') or hasAuthority('caregiver') or hasAuthority('patient')")
    @GetMapping()
    public ResponseEntity<List<PatientDTO>> getPatients() {
        List<PatientDTO> dtos = patientService.findPatients();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('doctor')")
    @PostMapping(value="/add")
    public ResponseEntity<UUID> insertPatient(@Valid @RequestBody PatientDetailsDTO patientDTO) {
        patientDTO.setPassword(encoder.encode(patientDTO.getPassword()));
        UUID patientID = patientService.insert(patientDTO);
        return new ResponseEntity<>(patientID, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('doctor')")
    @PutMapping(value="/update/{id}")
    public ResponseEntity<UUID> updatePatient(@PathVariable("id") UUID id, @Valid @RequestBody PatientDetailsDTO patientDTO) {
        patientDTO.setPassword(encoder.encode(patientDTO.getPassword()));
        UUID patientID = patientService.update(id, patientDTO);
        if(patientID == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(patientID, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('doctor')")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<UUID> deletePatient(@PathVariable("id") UUID id) {
        UUID patientID = patientService.delete(id);
        if(patientID == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(patientID, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('doctor') or hasAuthority('patient')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<PatientDTO> getPatient(@PathVariable("id") UUID patientId) {
        PatientDTO dto = patientService.findPatientById(patientId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
