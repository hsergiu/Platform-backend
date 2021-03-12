package medplatform.controllers;


import medplatform.services.CaregiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import medplatform.dtos.CaregiverDTO;
import medplatform.dtos.CaregiverDetailsDTO;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/caregiver")
public class CaregiverController {

    private final CaregiverService caregiverService;

    @Autowired
    public CaregiverController(CaregiverService caregiverService) {
        this.caregiverService = caregiverService;
    }

    @Autowired
    PasswordEncoder encoder;

    @PreAuthorize("hasAuthority('doctor')")
    @GetMapping()
    public ResponseEntity<List<CaregiverDTO>> getCaregivers() {
        List<CaregiverDTO> dtos = caregiverService.findCaregivers();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('doctor')")
    @PostMapping(value="/add")
    public ResponseEntity<UUID> insertCaregiver(@Valid @RequestBody CaregiverDetailsDTO caregiverDTO) {
        caregiverDTO.setPassword(encoder.encode(caregiverDTO.getPassword()));
        UUID caregiverID = caregiverService.insert(caregiverDTO);
        return new ResponseEntity<>(caregiverID, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('doctor')")
    @PutMapping(value="/update/{id}")
    public ResponseEntity<UUID> updateCaregiver(@PathVariable("id") UUID id, @Valid @RequestBody CaregiverDetailsDTO caregiverDTO) {
        caregiverDTO.setPassword(encoder.encode(caregiverDTO.getPassword()));
        UUID caregiverID = caregiverService.update(id, caregiverDTO);
        if(caregiverID == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(caregiverID, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('doctor')")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<UUID> deleteCaregiver(@PathVariable("id") UUID id) {
        UUID caregiverID = caregiverService.delete(id);
        if(caregiverID == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(caregiverID, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('doctor')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<CaregiverDTO> getCaregiver(@PathVariable("id") UUID caregiverId) {
        CaregiverDTO dto = caregiverService.findCaregiverById(caregiverId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
