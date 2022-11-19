package persistence;


import model.Expense;
import model.ListOfExpense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.persistence.JsonReader;
import ui.persistence.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    Expense e1;
    Expense e2;

    @BeforeEach
    void setup() {
        e1 = new Expense(10.00,"cheese", 3, 5, 2022);
        e2 = new Expense(12.30,"bread", 15, 6, 1999);
    }

    @Test
    void testWriterInvalidFile() {
        try {
            ListOfExpense listOfExpense = new ListOfExpense("All Expenses");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            ListOfExpense listOfExpense = new ListOfExpense("All Expenses");
            JsonWriter writer = new JsonWriter("./data/testWriterEmpty.json");

            writer.open();
            writer.write(listOfExpense);
            writer.close();

            // ==============================

            JsonReader reader = new JsonReader("./data/testWriterEmpty.json");
            listOfExpense = reader.read();

            assertEquals("All Expenses", listOfExpense.getName());
            assertEquals(0, listOfExpense.numOfExpenses());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            ListOfExpense listOfExpense = new ListOfExpense("All Expenses");
            listOfExpense.addExpense(e1);
            listOfExpense.addExpense(e2);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneral.json");

            writer.open();
            writer.write(listOfExpense);
            writer.close();

            // ==============================

            JsonReader reader = new JsonReader("./data/testWriterGeneral.json");
            listOfExpense = reader.read();

            assertEquals("All Expenses", listOfExpense.getName());

            ArrayList<Expense> expenses = listOfExpense.getListOfExpense();

            assertEquals(2, expenses.size());

            checkExpense(10.00,"cheese",3,5,2022,expenses.get(0));
            checkExpense(12.30,"bread",15,6,1999,expenses.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}