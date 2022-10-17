package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Expense {

    private double amount;
    private String purchaseName;
    private Date date;

    // date fields:
    private int day;
    private int month;
    private int year;

    // TODO: adding a category field for Expense,, scratc that purchase name is category

    // constructor:

    // TODO: REQUIRES:
    // TODO: EFFECTS:
    public Expense(double amount, String purchaseName, int day, int month, int year) {
        this.amount = amount;
        this.purchaseName = purchaseName;
        // date purchase was added
        this.date = new Date(day, month, year);
    }


    // getters:
    public double getAmount() {
        return amount;
    }
    public String getPurchaseName() {
        return purchaseName;
    }
    public Date getDate() {
        return date;
    }


    public String printExpense() {
        return this.purchaseName + " | $" + this.amount + " | " + this.getDate().getDay() + "/"
                + this.getDate().getMonth() + "/" + this.getDate().getYear();
    }
}
