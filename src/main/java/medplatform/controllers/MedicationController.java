package medplatform.controllers;


import medplatform.services.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import medplatform.dtos.MedicationDTO;
import medplatform.dtos.MedicationDetailsDTO;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/medication")
public class MedicationController {

    private final MedicationService medicationService;

    @Autowired
    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @PreAuthorize("hasAuthority('doctor') or hasAuthority('patient') or hasAuthority('caregiver')")
    @GetMapping()
    public ResponseEntity<List<MedicationDTO>> getMedications() {
        List<MedicationDTO> dtos = medicationService.findMedications();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('doctor')")
    @PostMapping(value="/add")
    public ResponseEntity<UUID> insertMedication(@Valid @RequestBody MedicationDetailsDTO medicationDTO) {
        UUID medicationID = medicationService.insert(medicationDTO);
        return new ResponseEntity<>(medicationID, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('doctor')")
    @PutMapping(value="/update/{id}")
    public ResponseEntity<UUID> updateMedication(@PathVariable("id") UUID id, @Valid @RequestBody MedicationDetailsDTO medicationDTO) {
        UUID medicationID = medicationService.update(id, medicationDTO);
        if(medicationID == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(medicationID, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('doctor')")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<UUID> deleteMedication(@PathVariable("id") UUID id) {
        UUID medicationID = medicationService.delete(id);
        if(medicationID == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(medicationID, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('doctor')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<MedicationDTO> getMedication(@PathVariable("id") UUID medicationId) {
        MedicationDTO dto = medicationService.findMedicationById(medicationId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
