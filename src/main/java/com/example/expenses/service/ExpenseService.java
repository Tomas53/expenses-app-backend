package com.example.expenses.service;

import com.example.expenses.dto.ExpenseDTO;
import com.example.expenses.exception.ResourceNotFoundException;
import com.example.expenses.model.Expense;
import com.example.expenses.model.Person;
import com.example.expenses.repository.ExpenseRepository;
import com.example.expenses.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    private final PersonRepository personRepository;

    public List<ExpenseDTO> getExpensesFromPersonById(Long id) {
        Person personInDtatabase = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person with this id was not found" + id));
        return PersonMapper.toPersonDTO(personInDtatabase).getExpenses();
    }

    public List<ExpenseDTO> getDescendingExpensesByPersonName(String name) {
        Person personInDatabase = personRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Person with name: " + name + "was not found"));

        return PersonMapper.toPersonDTO(personInDatabase).getExpenses()
                .stream()
                .sorted(Comparator.comparing(ExpenseDTO::getAmount).reversed())
                .collect(Collectors.toList());
    }

    public ExpenseDTO createExpense(ExpenseDTO expenseDTO, Long personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person with id " + personId + " not found"));

        Expense expense = new Expense();
        expense.setDescription(expenseDTO.getDescription());
        expense.setAmount(expenseDTO.getAmount());
        expense.setPerson(person);

        expense = expenseRepository.save(expense);
        return new ExpenseDTO(expense.getId(), expense.getDescription(), expense.getAmount());
    }

    public ExpenseDTO getExpenseById(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense with id " + id + " not found"));

        return new ExpenseDTO(expense.getId(), expense.getDescription(), expense.getAmount());
    }

    public ExpenseDTO updateExpense(Long id, ExpenseDTO expenseDTO) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense with id " + id + " not found"));

        expense.setDescription(expenseDTO.getDescription());
        expense.setAmount(expenseDTO.getAmount());

        expense = expenseRepository.save(expense);
        return new ExpenseDTO(expense.getId(), expense.getDescription(), expense.getAmount());
    }

    public void deleteExpense(Long id) {
        if (!expenseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Expense with id " + id + " not found");
        }
        expenseRepository.deleteById(id);
    }
}