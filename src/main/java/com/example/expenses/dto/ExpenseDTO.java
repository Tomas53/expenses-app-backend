package com.example.expenses.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class ExpenseDTO {
    // Add ID field
    private Long id;
    private String description;
    private BigDecimal amount;

    // Add constructor with ID (new constructor)
    public ExpenseDTO(Long id, String description, BigDecimal amount) {
        this.id = id;
        this.description = description;
        this.amount = amount;
    }

    // Keep original constructor for backward compatibility
    public ExpenseDTO(String description, BigDecimal amount) {
        this.description = description;
        this.amount = amount;
    }
}