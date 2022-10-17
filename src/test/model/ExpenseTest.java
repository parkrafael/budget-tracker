package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenseTest {

    Expense e1;

    @BeforeEach
    public void setup() {
        e1 = new Expense(20.00, "Smirnoff Vodka", 17,9,2022);
    }

    @Test
    public void printExpenseTest() {
        String e1Print = "Smirnoff Vodka | $20.0 | 17/9/2022";
        assertEquals(e1.printExpense(), e1Print);
    }
}
