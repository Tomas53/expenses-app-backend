package com.example.expenses.service;

import com.example.expenses.dto.PersonDTO;
import com.example.expenses.exception.ResourceNotFoundException;
import com.example.expenses.model.Person;
import com.example.expenses.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAllPeople() {
        Person tom = new Person(1L, "Tom", new ArrayList<>());
        Person bill = new Person(2L, "Bill", new ArrayList<>());
        List<Person> people = Arrays.asList(tom, bill);

        //Mockname personRepository.findAll() metofda ir imituojame jo response
        when(personRepository.findAll()).thenReturn(people);

        List<PersonDTO> actualResult = personService.getAllPeople();

        assertEquals(2, actualResult.size());
        assertEquals("Tom", actualResult.get(0).getName());
    }

    @Test
    void shouldReturnPersonById() {
        Person will = new Person(1L, "Will", new ArrayList<>());
        when(personRepository.findById(1L)).thenReturn(Optional.of(will));
        PersonDTO actualResult = personService.getPersonById(1L);


        assertNotNull(actualResult);
        assertEquals("Will", actualResult.getName());
    }

    @Test
    void shouldThrowAnExceptionWhenPersonIsNotFound(){
        assertThrows(ResourceNotFoundException.class, ()->personService.getPersonById(1L));
    }
    @Test
    void shouldDeletePerson(){
        when(personRepository.existsById(1L)).thenReturn(true);
        personService.deletePerson(1L);

        verify(personRepository, times(1)).deleteById(1L);
    }

}