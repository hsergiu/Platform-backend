package medplatform.controllers;


import medplatform.services.MedicationPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import medplatform.dtos.MedicationPlanDTO;
import medplatform.dtos.MedicationPlanDetailsDTO;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/medicationplan")
public class MedicationPlanController {

    private final MedicationPlanService medicationPlanService;

    @Autowired
    public MedicationPlanController(MedicationPlanService medicationPlanService) {
        this.medicationPlanService = medicationPlanService;
    }

    @PreAuthorize("hasAuthority('doctor') or hasAuthority('caregiver') or hasAuthority('patient')")
    @GetMapping()
    public ResponseEntity<List<MedicationPlanDTO>> getMedicationPlans() {
        List<MedicationPlanDTO> dtos = medicationPlanService.findMedicationPlans();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('doctor')")
    @PostMapping(value="/add")
    public ResponseEntity<UUID> insertMedicationPlan(@Valid @RequestBody MedicationPlanDetailsDTO medicationPlanDTO) {
        UUID medicationPlanID = medicationPlanService.insert(medicationPlanDTO);
        return new ResponseEntity<>(medicationPlanID, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('doctor')")
    @PutMapping(value="/update/{id}")
    public ResponseEntity<UUID> updateMedicationPlan(@PathVariable("id") UUID id, @Valid @RequestBody MedicationPlanDetailsDTO medicationPlanDTO) {
        UUID medicationPlanID = medicationPlanService.update(id, medicationPlanDTO);
        if(medicationPlanID == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(medicationPlanID, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('doctor')")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<UUID> deleteMedicationPlan(@PathVariable("id") UUID id) {
        UUID medicationPlanID = medicationPlanService.delete(id);
        if(medicationPlanID == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(medicationPlanID, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('doctor') or hasAuthority('caregiver') or hasAuthority('patient')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<MedicationPlanDTO> getMedicationPlan(@PathVariable("id") UUID medicationPlanId) {
        MedicationPlanDTO dto = medicationPlanService.findMedicationPlanById(medicationPlanId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
