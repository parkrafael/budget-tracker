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
        String e1Print = "Smirnoff Vodka || $20.0 || 17/9/2022";
        assertEquals(e1.printExpense(), e1.printExpense());
    }
    @Test
    public void getAmountTest() {
        assertEquals(20.00, e1.getAmount());
    }

    @Test
    public void getNameTest() {
        assertEquals("Smirnoff Vodka",e1.getName());
    }

    @Test
    public void getDayTest() {
        assertEquals(17,e1.getDay());
    }

    @Test
    public void getMonthTest() {
        assertEquals(9, e1.getMonth());
    }

    @Test
    public void getYearTest() {
        assertEquals(2022,e1.getYear());
    }


}
