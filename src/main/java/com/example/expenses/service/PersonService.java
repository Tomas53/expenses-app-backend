package com.example.expenses.service;

import com.example.expenses.dto.PersonDTO;
import com.example.expenses.exception.ResourceNotFoundException;
import com.example.expenses.model.Person;
import com.example.expenses.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    //constructor based DI personRepository->personService
    private final PersonRepository personRepository;
   // private final PersonMapper personMapper;

    public List<PersonDTO> getAllPeople() {
        //we will get person entities from db
        //we wil need to convert them to personDto
        return personRepository.findAll().stream()
                .map(PersonMapper::toPersonDTO)//    .map(person -> personMapper.toPersonDTO(person))
                .toList();
    }

    public PersonDTO createPersonWithExpenses(PersonDTO personDTO) {
        Person person = PersonMapper.toPersonEntity(personDTO);
        personRepository.save(person);
        return PersonMapper.toPersonDTO(person);
    }

    public PersonDTO getPersonById(Long id) {
        Person personInDatabase = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person with id " + id + " was not found"));
        return PersonMapper.toPersonDTO(personInDatabase);
    }

    public void deletePerson(Long id) {
        if(!personRepository.existsById(id)){
            throw new ResourceNotFoundException("Person with id " + id + "was not found");
        }
        personRepository.deleteById(id);
    }
    // In PersonService.java

    public PersonDTO updatePerson(Long id, PersonDTO personDTO) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person with id " + id + " not found"));

        person.setName(personDTO.getName());
        // Handle expense updates if needed

        personRepository.save(person);
        return PersonMapper.toPersonDTO(person);
    }
}
