package model;

import org.json.JSONObject;
import persistence.Writable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Expense implements Writable {

    // fields:
    private double amount;
    private String name;
    private Date date;

    // constructor:
    // REQUIRES: string has a non-zero length
    //           day has the range of all positive numbers between [1-31]
    //           month has the range of all positive numbers between [1-12]
    //           year has the range of all positive non-zero numbers
    // MODIFIES: this
    // EFFECTS: instantiates Expense with the variables given
    public Expense(double amount, String name, int day, int month, int year) {
        this.amount = amount;
        this.name = name;
        this.date = new Date(day, month, year);
    }

    // getters:
    public double getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    // TODO: tests
    // REQUIRES: N/A
    // MODIFIES: N/A
    // EFFECTS: prints out a receipt for the expense given (including name, amount, and date all in one string
    public String printExpense() {
        String receipt;
        receipt = this.name + " | $" + this.amount + " | " + this.getDate().getDay() + "/" + this.getDate().getMonth()
                + "/" + this.getDate().getYear();

        return receipt;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("amount", amount);
        json.put("name", name);
        json.put("day", date);

        return json;
    }
}
