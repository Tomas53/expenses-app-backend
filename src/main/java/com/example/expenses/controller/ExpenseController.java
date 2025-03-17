package com.example.expenses.controller;

import com.example.expenses.dto.ExpenseDTO;
import com.example.expenses.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;

    @GetMapping("/person/{id}")
    public ResponseEntity<?> getExpensesByPersonId(@PathVariable Long id){
        return ResponseEntity.ok(expenseService.getExpensesFromPersonById(id));
    }
    @GetMapping("/person/name/{name}")
    public ResponseEntity<?> getExpensesByPersonName(@PathVariable String name){
        return ResponseEntity.ok(expenseService.getDescendingExpensesByPersonName(name));
    }
    // In ExpenseController.java

    @PostMapping
    public ResponseEntity<ExpenseDTO> createExpense(@RequestBody ExpenseDTO expenseDTO, @RequestParam Long personId) {
        return ResponseEntity.ok(expenseService.createExpense(expenseDTO, personId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDTO> getExpenseById(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.getExpenseById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseDTO> updateExpense(@PathVariable Long id, @RequestBody ExpenseDTO expenseDTO) {
        return ResponseEntity.ok(expenseService.updateExpense(id, expenseDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.accepted().build();
    }

}
