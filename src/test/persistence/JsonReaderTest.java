package persistence;


import model.Expense;
import model.ListOfExpense;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");

        try {
            ListOfExpense listOfExpense = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmpty.json");

        try {
            ListOfExpense listOfExpense = reader.read();
            assertEquals("All Expenses", listOfExpense.getName());
            assertEquals(0, listOfExpense.numOfExpenses());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneral.json");

        try {
            ListOfExpense listOfExpense = reader.read();
            assertEquals("All Expenses", listOfExpense.getName());

            ArrayList<Expense> expenses = listOfExpense.getListOfExpense();

            assertEquals(2, expenses.size());

            checkExpense(12.81, "flowers",3,10,2022, expenses.get(0));
            checkExpense(301.11,"Airpods",1,11,2019, expenses.get(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}