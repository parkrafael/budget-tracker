package persistence;

import model.Expense;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkExpense(Double amount, String name, int day, int month, int year, Expense expense) {
        assertEquals(amount, expense.getAmount());
        assertEquals(name, expense.getName());
        assertEquals(day, expense.getDay());
        assertEquals(month, expense.getMonth());
        assertEquals(year, expense.getYear());
    }
}
