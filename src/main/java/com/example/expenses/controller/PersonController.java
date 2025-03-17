package com.example.expenses.controller;

import com.example.expenses.dto.PersonDTO;
import com.example.expenses.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/people")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public ResponseEntity<List<PersonDTO>> getAllPeople() {
        List<PersonDTO> allPeople = personService.getAllPeople();
        return ResponseEntity.ok(allPeople);
       // return new ResponseEntity<>(allPeople, HttpStatus.OK);// taip irgi galima
    }
    @PostMapping
    public ResponseEntity<PersonDTO> createPerson(@RequestBody PersonDTO personDTO){
        return ResponseEntity.ok(personService.createPersonWithExpenses(personDTO));
    }
    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable Long id){
        return ResponseEntity.ok(personService.getPersonById(id));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long id){
        personService.deletePerson(id);
        return ResponseEntity.accepted().build();
    }
    // In PersonController.java

    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable Long id, @RequestBody PersonDTO personDTO) {
        return ResponseEntity.ok(personService.updatePerson(id, personDTO));
    }

}
