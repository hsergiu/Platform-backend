package medplatform.controllers;


import medplatform.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import medplatform.dtos.PersonDTO;
import medplatform.dtos.PersonDetailsDTO;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public ResponseEntity<List<PersonDTO>> getPersons() {
        List<PersonDTO> dtos = personService.findPersons();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping(value="/add")
    public ResponseEntity<UUID> insertPerson(@Valid @RequestBody PersonDetailsDTO personDTO) {
        UUID personID = personService.insert(personDTO);
        return new ResponseEntity<>(personID, HttpStatus.CREATED);
    }

    @PutMapping(value="/update/{id}")
    public ResponseEntity<UUID> updatePerson(@PathVariable("id") UUID id, @Valid @RequestBody PersonDetailsDTO personDTO) {
        UUID personID = personService.update(id, personDTO);
        return new ResponseEntity<>(personID, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<UUID> deletePerson(@Valid @RequestBody PersonDetailsDTO personDTO) {
        UUID personID = personService.delete(personDTO);
        return new ResponseEntity<>(personID, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonDTO> getPerson(@PathVariable("id") UUID personId) {
        PersonDTO dto = personService.findPersonById(personId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
