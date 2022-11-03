package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfExpenseTest {

    Expense e1;
    Expense e2;
    Expense e3;
    Expense e4;
    Expense e5;

    ListOfExpense listOfExpense;


    @BeforeEach
    public void setup() {
        e1 = new Expense(7.39, "teddy bear", 6, 5, 2002);

        // all in the same mot

        // same month
        e2 = new Expense(51.00, "flowers", 4, 9, 2021);
        e3 = new Expense(200.00, "airpods", 29, 9, 2021);

        // same day
        e4 = new Expense(10.00, "snacks", 15, 12, 2021);
        e5 = new Expense(10.00, "doordash", 15, 12, 2021);

        listOfExpense = new ListOfExpense();

        listOfExpense.addExpense(e1);
        listOfExpense.addExpense(e2);
        listOfExpense.addExpense(e3);
        listOfExpense.addExpense(e4);
        listOfExpense.addExpense(e5);
    }

    @Test
    public void addExpenseTest() {
        boolean e1test = listOfExpense.getListOfExpense().contains(e1);
        boolean e2test = listOfExpense.getListOfExpense().contains(e2);
        boolean e3test = listOfExpense.getListOfExpense().contains(e3);
        boolean e4test  =listOfExpense.getListOfExpense().contains(e4);
        boolean e5test = listOfExpense.getListOfExpense().contains(e5);

        assertTrue(e1test);
        assertTrue(e2test);
        assertTrue(e3test);
        assertTrue(e4test);
        assertTrue(e5test);
    }

    @Test
    public void getExpenseAtDayTest() {
        // two items on the day
        ArrayList<Expense> testAtDay1 = new ArrayList<>();
        testAtDay1.add(e4);
        testAtDay1.add(e5);

        assertEquals(listOfExpense.getExpenseAtDay(15,12,2021), testAtDay1);

        // one item on the day
        ArrayList<Expense> testAtDay2 = new ArrayList<>();
        testAtDay2.add(e1);

        assertEquals(listOfExpense.getExpenseAtDay(6,5,2002), testAtDay2);

        // zero item on day
        ArrayList<Expense> testAtDay3 = new ArrayList<>();

        assertEquals(listOfExpense.getExpenseAtDay(12,12,2002), testAtDay3);
    }

    @Test
    public void getExpenseAtMonthTest() {
        // two items in specified month
        ArrayList<Expense> testAtMonth1 = new ArrayList<>();
        testAtMonth1.add(e2);
        testAtMonth1.add(e3);

        assertEquals(listOfExpense.getExpenseAtMonth(9,2021), testAtMonth1);

        // one item in specified month
        ArrayList<Expense> testAtMonth2 = new ArrayList<>();
        testAtMonth2.add(e1);

        assertEquals(listOfExpense.getExpenseAtMonth(5,2002), testAtMonth2);

        // no item in specified month
        ArrayList<Expense> testAtMonth3 = new ArrayList<>();

        assertEquals(listOfExpense.getExpenseAtMonth(10,2022), testAtMonth3);

        // e6 is month 12 but 2022, e4 & e5 are month 12 but 2021
        Expense e6 = new Expense(10.00, "doordash", 15, 12, 2022);
        listOfExpense.addExpense(e6);

        ArrayList<Expense> testAtMonth5 = new ArrayList<>();
        testAtMonth5.add(e6);

        assertEquals(listOfExpense.getExpenseAtMonth(12, 2022), testAtMonth5);
    }

    @Test
    public void getExpenseAtYearTest() {
        // 2021, 4 items
        ArrayList<Expense> testAtYear1 = new ArrayList<>();
        testAtYear1.add(e2);
        testAtYear1.add(e3);
        testAtYear1.add(e4);
        testAtYear1.add(e5);

        assertEquals(listOfExpense.getExpenseAtYear(2021), testAtYear1);

        // 2001, 1 item
        ArrayList<Expense> testAtYear2 = new ArrayList<>();
        testAtYear2.add(e1);

        assertEquals(listOfExpense.getExpenseAtYear(2002), testAtYear2);

        // 2022, 0 items
        ArrayList<Expense> testAtYear3 = new ArrayList<>();

        assertEquals(listOfExpense.getExpenseAtYear(2022), testAtYear3);



    }

}
