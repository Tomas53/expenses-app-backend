package com.example.expenses.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false)
    private String description;

    private BigDecimal amount;

    @ManyToOne//daug expences vienam personuii
    @JoinColumn(name="person_id", nullable = false)// jei jau pridedam expence tai turi buti priskirta kazkokiam zmogui
    @JsonBackReference//need ed to avoid infinite loop in response if we used entities in requests insted of dtos
    private Person person;
}
