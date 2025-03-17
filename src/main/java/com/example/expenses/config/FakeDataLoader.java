package com.example.expenses.config;

import com.example.expenses.model.Expense;
import com.example.expenses.model.Person;
import com.example.expenses.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

//when application starts this method in hte class will be executed
@Component
@RequiredArgsConstructor
public class FakeDataLoader implements CommandLineRunner {
    private final PersonRepository personRepository;
    @Override
    public void run(String... args) throws Exception {
        Person john = new Person(null, "John", null);
        //sarysis i viena puse Expense->Person
        Expense food = new Expense(null, "Food", new BigDecimal(150), john);
        Expense supplies = new Expense(null, "Supplies", new BigDecimal(220), john);
        john.setExpenses(Arrays.asList(food, supplies));

        //sarysis i kita puse Person->Expense
        personRepository.save(john);
        System.out.println("Command line runner works");
    }
}
