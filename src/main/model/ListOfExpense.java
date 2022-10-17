package model;

import java.util.ArrayList;

public class ListOfExpense {

    // fields:
    ArrayList<Expense> allExpense;

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

}
