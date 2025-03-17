package com.example.expenses.dto;

import lombok.Data;

import java.util.List;

@Data
public class PersonDTO {
    // Add ID field
    private Long id;
    //todo add @notblank for @Valid requestbody validation with Spring
    private String name;
    private List<ExpenseDTO> expenses;
}