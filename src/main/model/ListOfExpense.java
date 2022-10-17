package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListOfExpense {

    ArrayList<Expense> allExpense;

    public ListOfExpense() {
        this.allExpense = new ArrayList<>();
    }

    // getters:
    public ArrayList<Expense> getAllExpense() {
        return allExpense;
    }

    // TODO: REQUIRES
    // TODO: MODIFIES
    // TODO: EFFECTS
    public ArrayList<Expense> getExpenseAtDay(int day, int month, int year) {

        ArrayList<Expense> expenseAtDay = new ArrayList<>();

        for (Expense e : this.getAllExpense()) {
            if (e.getDate().getDay() == day && e.getDate().getMonth() == month && e.getDate().getYear() == year) {
                expenseAtDay.add(e);
            }
        }

        return expenseAtDay;
    }

    // TODO: REQUIRES
    // TODO: MODIFIES
    // TODO: EFFECTS
    public ArrayList<Expense> getExpenseAtMonth(int month, int year) {

        ArrayList<Expense> expenseAtMonth = new ArrayList<>();

        for (Expense e : this.getAllExpense()) {
            if (e.getDate().getMonth() == month && e.getDate().getYear() == year) {
                expenseAtMonth.add(e);
            }
        }

        return expenseAtMonth;
    }

    // TODO: REQUIRES
    // TODO: MODIFIES
    // TODO: EFFECTS
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
