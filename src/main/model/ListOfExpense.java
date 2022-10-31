package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

public class ListOfExpense implements Writable {

    // fields:
    private ArrayList<Expense> allExpense;

    // constructor:
    // REQUIRES: N/A
    // MODIFIES: this
    // EFFECTS: instantiates a new ListOfExpense
    public ListOfExpense() {
        this.allExpense = new ArrayList<>();
    }

    // getters:
    public ArrayList<Expense> getAllExpense() {
        return allExpense;
    }

    // TODO: this method
    public void addExpense(Expense expense) {
        ArrayList<Expense> allExpense = getAllExpense();
        allExpense.add(expense);
    }

    // REQUIRES: day has the range of all positive numbers between [1-31]
    //           month has the range of all positive numbers between [1-12]
    //           year has the range of all positive non-zero numbers
    // MODIFIES: N/A
    // EFFECTS: returns all Expenses in ListOfExpense that fits the Date criteria given.
    public ArrayList<Expense> getExpenseAtDay(int day, int month, int year) {
        ArrayList<Expense> expenseAtDay = new ArrayList<>();

        for (Expense e : this.getAllExpense()) {
            if (e.getDate().getDay() == day && e.getDate().getMonth() == month && e.getDate().getYear() == year) {
                expenseAtDay.add(e);
            }
        }
        return expenseAtDay;
    }

    // REQUIRES: month has the range of all positive numbers between [1-12]
    //           year has the range of all positive non-zero numbers
    // MODIFIES: N/A
    // EFFECTS: returns all Expenses in ListOfExpense that fits the Date criteria given.
    public ArrayList<Expense> getExpenseAtMonth(int month, int year) {
        ArrayList<Expense> expenseAtMonth = new ArrayList<>();

        for (Expense e : this.getAllExpense()) {
            if (e.getDate().getMonth() == month && e.getDate().getYear() == year) {
                expenseAtMonth.add(e);
            }
        }
        return expenseAtMonth;
    }

    // REQUIRES: year has the range of all positive non-zero numbers
    // MODIFIES: N/A
    // EFFECTS: returns all Expenses in ListOfExpense that fits the Date criteria given.
    public ArrayList<Expense> getExpenseAtYear(int year) {
        ArrayList<Expense> expenseAtYear = new ArrayList<>();

        for (Expense e : this.getAllExpense()) {
            if (e.getDate().getYear() == year) {
                expenseAtYear.add(e);
            }
        }
        return expenseAtYear;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("allExpense", thingiesToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray thingiesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Expense e : allExpense) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }

}
