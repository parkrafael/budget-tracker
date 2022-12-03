package model;

import org.json.JSONArray;
import org.json.JSONObject;
import ui.persistence.Writable;

import java.util.ArrayList;

// Represents a ListOfExpense having a collection of Expenses
public class ListOfExpense implements Writable {
    // ==============================
    // FIELDS:

    // constructor:
    private final ArrayList<Expense> listOfExpense;
    private String name;

    // ==============================
    // CONSTRUCTOR:

    // REQUIRES: N/A
    // MODIFIES: this
    // EFFECTS: instantiates a new ListOfExpense
    public ListOfExpense(String name) {
        this.name = name;
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

    // REQUIRES: N/A
    // MODIFIES: this
    // EFFECTS: adds Expense to given ListOfExpense
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

    public void changeName(String name) {
        this.name = name;
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

    // ==============================
    // EVENT LOG:

    // MODIFIES: EventLog
    // EFFECTS: logs event for adding Expense to ListOfExpense
    public void logAddExpenseEvent() {
        EventLog.getInstance().logEvent(new Event("Added Expense to " + name + "'s" + " list of expenses"));
    }

    // MODIFIES: EventLog
    // EFFECTS: logs event for creating ListOfExpense to EventLog
    public void logCreateEvent() {
        EventLog.getInstance().logEvent(new Event("Created a new List of Expenses, name: " + name));
    }

    // MODIFIES: EventLog
    // EFFECTS: logs event for saving ListOfExpense to EventLog
    public void logSaveEvent() {
        EventLog.getInstance().logEvent(new Event("Saved " + name + "'s " + "List of expenses."));
    }

    // MODIFIES: EventLog
    // EFFECTS: logs event for loading ListOfExpense to EventLog
    public void logLoadEvent() {
        EventLog.getInstance().logEvent(new Event("Loaded " + name + "'s " + "list of expenses."));
    }

    // MODIFIES: EventLog
    // EFFECTS: logs event for viewing by day
    public void logDayViewEvent() {
        EventLog.getInstance().logEvent(new Event("Viewed " + name + "'s " + "purchases by day"));
    }

    // MODIFIES: EventLog
    // EFFECTS: logs event for viewing by month
    public void logMonthViewEvent() {
        EventLog.getInstance().logEvent(new Event("Viewed " + name + "'s " + "purchases by month"));
    }

    // MODIFIES: EventLog
    // EFFECTS: logs event for viewing by year
    public void logYearViewEvent() {
        EventLog.getInstance().logEvent(new Event("Viewed " + name + "'s " + "purchases by year"));
    }

    // MODIFIES: EventLog
    // EFFECTS: logs event for viewing by name
    public void logNameViewEvent() {
        EventLog.getInstance().logEvent(new Event("Viewed " + name + "'s " + "purchases by name"));
    }

    // EFFECTS: prints to the console all the events that have been logged since the application started
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
        }
    }

}
