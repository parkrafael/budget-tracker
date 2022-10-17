package ui;

import model.Date;
import model.Expense;
import model.ListOfExpense;

import java.util.List;
import java.util.Scanner;

public class BudgetCalculator {

    private double amount;
    private String purchaseName;
    private Date date;

    // date fields:
    private int day;
    private int month;
    private int year;

    // new Expense + listOfExpense
    private Expense exp;
    private ListOfExpense expAllList;

    // scanner
    Scanner scan = new Scanner(System.in);

    // EFFECTS: runs the budget calculator application
    public BudgetCalculator() {
        runBudgetCalculator();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runBudgetCalculator() {
        // display menu
        initMenu();
        String option = scan.next();

        if (option.equals("a")) {
            addExpenseMenu();
            System.out.println("Please press r if you would like to return back to the main menu");
            String returnScan = scan.next();
            if (returnScan.equals("r")) {
                runBudgetCalculator();
            }
        } else if (option.equals("b")) {
            viewExpenseMenu();
            System.out.println("Please press r if you would like to return back to the main menu");
            String returnScan = scan.next();
            if (returnScan.equals("r")) {
                runBudgetCalculator();
            }
        } else if (option.equals("c")) {
            return;
        } else {
            System.out.println("Please select a valid option...");
            runBudgetCalculator();
        }
    }



    // TODO: MODIFIES:
    // TODO: EFFECTS:
    private void initMenu() {
        System.out.println("Welcome to your Budget Book! Please select an option.");
        System.out.println("(a) Add expense");
        System.out.println("(b) View expense");
        System.out.println("(c) Quit");
    }

    // creates new expense, adds it to a new instance of listOfExpense
    // TODO: MODIFIES:
    // TODO: EFFECTS:
    private void addExpenseMenu() {
        System.out.print("Expense amount: $");
        amount = scan.nextDouble();
        System.out.print("Expense name: ");
        purchaseName = scan.next();
        System.out.print("Day of purchase: ");
        day = scan.nextInt();
        System.out.print("Month of purchase: ");
        month = scan.nextInt();
        System.out.print("Year of purchase: ");
        year = scan.nextInt();

        exp = new Expense(amount, purchaseName, day, month, year);
        expAllList = new ListOfExpense();
        expAllList.getAllExpense().add(exp);
    }

    // TODO: MODIFIES:
    // TODO: EFFECTS:
    private void viewExpenseMenu() {
        initViewMenu();
        String option = scan.next();

        if (option.equals("a")) {
            viewByDay();
        } else if (option.equals("b")) {
            viewByMonth();
        } else if (option.equals("c")) {
            viewByYear();
        } else {
            return;
        }

    }

    // TODO: MODIFIES:
    // TODO: EFFECTS:
    private void viewByYear() {

    }

    // TODO: MODIFIES:
    // TODO: EFFECTS:
    private void viewByMonth() {

    }

    // TODO: MODIFIES:
    // TODO: EFFECTS:
    private void viewByDay() {
    }

    // TODO: MODIFIES:
    // TODO: EFFECTS:
    private void initViewMenu() {
        System.out.println("Please select an option.");
        System.out.println("(a) View by Day");
        System.out.println("(b) View by Month");
        System.out.println("(c) View by Year");
    }


}
