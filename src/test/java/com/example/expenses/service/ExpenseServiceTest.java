package com.example.expenses.service;

import com.example.expenses.dto.PersonDTO;
import com.example.expenses.model.Expense;
import com.example.expenses.model.Person;
import com.example.expenses.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ExpenseServiceTest {
    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetExpensesFromPersonByIdTest() {
        List<Expense> expenses = new ArrayList<>();
        Person tom = Person.builder()
                .name("Tomas")
                .expenses(new ArrayList<Expense>())
                .build();

        Expense food = new Expense(1L, "Maistas", new BigDecimal("0.03"), tom);
        tom.setExpenses(Arrays.asList(food));
        when(personRepository.findById(1L)).thenReturn(Optional.of(tom));
        PersonDTO tomDTO = personService.getPersonById(1L);
        assertEquals(tomDTO, personService.getPersonById(1L));




    }


}