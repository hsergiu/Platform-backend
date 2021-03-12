package medplatform.services;

import medplatform.dtos.builders.PersonBuilder;
import medplatform.entities.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import medplatform.controllers.handlers.exceptions.model.ResourceNotFoundException;
import medplatform.dtos.PersonDTO;
import medplatform.dtos.PersonDetailsDTO;
import medplatform.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonDTO> findPersons() {
        List<Person> personList = personRepository.findAll();
        return personList.stream()
                .map(PersonBuilder::toPersonDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findPersonById(UUID id) {
        Optional<Person> prosumerOptional = personRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Person with id {} was not found in db", id);
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with id: " + id);
        }
        return PersonBuilder.toPersonDTO(prosumerOptional.get());
    }

    public UUID insert(PersonDetailsDTO personDTO) {
        Person person = PersonBuilder.toEntity(personDTO);
        person = personRepository.save(person);
        LOGGER.debug("Person with id {} was inserted in db", person.getId());
        return person.getId();
    }

    public UUID update(UUID id, PersonDetailsDTO personDTO) {
        Person person = personRepository.findById(id).orElse(null);
        Person aux = PersonBuilder.toEntity(personDTO);
        person.setAddress(aux.getAddress());
        person.setAge(aux.getAge());
        person.setName(aux.getName());
        person = personRepository.save(person);
        LOGGER.debug("Person with id {} was updated in db", person.getId());
        return person.getId();
    }

    public UUID delete(PersonDetailsDTO personDTO) {
        Person person = PersonBuilder.toEntity(personDTO);
        personRepository.delete(person);
        LOGGER.debug("Person with id {} was removed from the db", person.getId());
        return person.getId();
    }

}
