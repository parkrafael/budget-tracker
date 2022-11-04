package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// TODO: class descriptor
public class ListOfExpense implements Writable {
    // ==============================
    // FIELDS:

    // constructor:
    private final ArrayList<Expense> listOfExpense;
    private final String name;

    // ==============================
    // CONSTRUCTOR:

    // REQUIRES: N/A
    // MODIFIES: this
    // EFFECTS: instantiates a new ListOfExpense
    public ListOfExpense() {
        this.name = "All Expenses";
        this.listOfExpense = new ArrayList<>();
    }

    // ==============================
    // GETTERS:

    public ArrayList<Expense> getListOfExpense() {
        return listOfExpense;
    }

    public String getName() {
        return name;
    }

    public int numOfExpenses() {
        return listOfExpense.size();
    }

    // ==============================
    // METHODS:

    // TODO: add specification for addExpense
    // TODO: add tests for addExpense
    public void addExpense(Expense expense) {
        listOfExpense.add(expense);
    }

    // REQUIRES: day has the range of all positive integers between [1-31]
    //           month has the range of all positive integers between [1-12]
    //           year is a positive non-zero integer
    // MODIFIES: N/A
    // EFFECTS: returns all Expenses in ListOfExpense that fits the day/month/year criteria given.
    public ArrayList<Expense> getExpenseAtDay(int day, int month, int year) {
        ArrayList<Expense> expenseAtDay = new ArrayList<>();

        for (Expense e : this.getListOfExpense()) {
            if (e.getDay() == day && e.getMonth() == month && e.getYear() == year) {
                expenseAtDay.add(e);
            }
        }
        return expenseAtDay;
    }

    // REQUIRES: month has the range of all positive integers between [1-12]
    //           year is a positive non-zero integer
    // MODIFIES: N/A
    // EFFECTS: returns all Expenses in ListOfExpense that fits the month/year criteria given.
    public ArrayList<Expense> getExpenseAtMonth(int month, int year) {
        ArrayList<Expense> expenseAtMonth = new ArrayList<>();

        for (Expense e : this.getListOfExpense()) {
            if (e.getMonth() == month && e.getYear() == year) {
                expenseAtMonth.add(e);
            }
        }
        return expenseAtMonth;
    }

    // REQUIRES: year is a positive non-zero integer
    // MODIFIES: N/A
    // EFFECTS: returns all Expenses in ListOfExpense that fits the year criteria given.
    public ArrayList<Expense> getExpenseAtYear(int year) {
        ArrayList<Expense> expenseAtYear = new ArrayList<>();

        for (Expense e : this.getListOfExpense()) {
            if (e.getYear() == year) {
                expenseAtYear.add(e);
            }
        }
        return expenseAtYear;
    }

    // ==============================
    // DATA PERSISTENCE:

    // code derived from P2 example
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("expenses", expenseToJson());
        return json;
    }

    private JSONArray expenseToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Expense e : listOfExpense) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }

}
