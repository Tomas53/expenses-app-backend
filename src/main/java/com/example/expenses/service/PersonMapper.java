package com.example.expenses.service;

import com.example.expenses.dto.ExpenseDTO;
import com.example.expenses.dto.PersonDTO;
import com.example.expenses.model.Expense;
import com.example.expenses.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonMapper {
    public static PersonDTO toPersonDTO(final Person personEntity){
        PersonDTO personDTO = new PersonDTO();
        // Include ID in DTO
        personDTO.setId(personEntity.getId());
        personDTO.setName(personEntity.getName());
        //reikia tikrinti visus personEntity expenses ir is ju sukurti sarasa dto
        List<ExpenseDTO> expenseDtos=personEntity.getExpenses().stream()
                // Include expense ID in DTO
                .map(expense -> new ExpenseDTO(expense.getId(), expense.getDescription(), expense.getAmount()))
                .toList();
        personDTO.setExpenses(expenseDtos);
        return personDTO;
    }

    public static Person toPersonEntity(final PersonDTO personDTO){
        Person person= new Person();
        // Set ID if provided (important for updates)
        person.setId(personDTO.getId());
        person.setName(personDTO.getName());
        List<Expense>expenses= personDTO.getExpenses().stream()
                .map(expenseDTO -> {
                    Expense expense = new Expense(
                            expenseDTO.getId(),  // Use ID from DTO if available
                            expenseDTO.getDescription(),
                            expenseDTO.getAmount(),
                            person
                    );
                    return expense;
                })
                .toList();
        person.setExpenses(expenses);
        return person;
    }
}