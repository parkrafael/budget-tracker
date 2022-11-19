package model;

import org.json.JSONObject;
import ui.persistence.Writable;

// Represents a Expense, having a amount, name, and date (day/month/year)
public class Expense implements Writable {
    // ==============================
    // FIELDS:

    // constructor:
    private final double amount;
    private final String name;
    private final int day;
    private final int month;
    private final int year;

    // ==============================
    // CONSTRUCTOR:

    // REQUIRES: string has a non-zero length
    //           day has the range of all positive numbers between [1-31]
    //           month has the range of all positive numbers between [1-12]
    //           year has the range of all positive non-zero numbers
    // MODIFIES: this
    // EFFECTS: instantiates Expense with the variables given
    public Expense(double amount, String name, int day, int month, int year) {
        this.amount = amount;
        this.name = name;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    // ==============================
    // GETTERS:

    public double getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    // ==============================
    // METHODS:

    // REQUIRES: N/A
    // MODIFIES: N/A
    // EFFECTS: prints out a receipt for the expense given (including name, amount, and date all in one string
    public String printExpense() {
        String receipt;

        receipt = name + " || $" + amount + " || " + day + "/" + month + "/" + year;

        return receipt;
    }

    // ==============================
    // DATA PERSISTENCE:

    // code derived from P2 example
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("amount", amount);
        json.put("name", name);
        json.put("day", day);
        json.put("month", month);
        json.put("year", year);

        return json;
    }

}
