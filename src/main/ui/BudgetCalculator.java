package ui;

import model.Expense;
import model.ListOfExpense;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BudgetCalculator {

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/workroom.json";

    // - fields -
    // expense
    private double amount;
    private String name;

    // date
    private int day;
    private int month;
    private int year;

    // sum (for calculating total)
    double sum;

    // scanner
    Scanner scan = new Scanner(System.in);

    // instantiating expense + listOfExpense
    Expense exp;
    ListOfExpense expAllList = new ListOfExpense();

    // - constructor -
    // EFFECTS: runs the budget calculator application
    public BudgetCalculator() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runBudgetCalculator();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runBudgetCalculator() {
        // initial menu
        initMenu();
        String option = scan.next();

        if (option.equals("a")) {
            addExpenseMenu();
            resetScreen();
        } else if (option.equals("b")) {
            viewExpenseMenu();
            resetScreen();
        } else if (option.equals("c")) {
            saveWorkRoom();
        } else if (option.equals("d")) {
            loadWorkRoom();
        } else if (option.equals("e")) {
            return;
        } else {
            System.out.println("Please select a valid option...");
            runBudgetCalculator();
        }
    }

    // MODIFIES: this
    // EFFECTS: menu for the initial screen
    private void initMenu() {
        System.out.println("Welcome to your Budget Book! Please select an option.");
        System.out.println("(a) Add expense");
        System.out.println("(b) View expense");
        System.out.println("(c) Save ");
        System.out.println("(d) Load");
        System.out.println("(e) Quit");
    }

    // MODIFIES: this
    // EFFECTS: gives user a prompt to reset screen to the home page, or exit
    private void resetScreen() {
        System.out.println("Please press r if you would like to return back to the main menu");
        String option = scan.next();

        if (option.equals("r")) {
            // just a tracker for myself to see that the expenses are being added properly to the list
            System.out.println(expAllList.getAllExpense());
            runBudgetCalculator();
        }
    }

    // MODIFIES: this
    // EFFECTS: creates new expense with input from user, adds expense to expAllList
    private void addExpenseMenu() {
        System.out.print("Expense amount: $");
        amount = scan.nextDouble();
        System.out.print("Expense name: ");
        name = scan.next();
        System.out.print("Day of purchase: ");
        day = scan.nextInt();
        System.out.print("Month of purchase: ");
        month = scan.nextInt();
        System.out.print("Year of purchase: ");
        year = scan.nextInt();

        exp = new Expense(amount, name, day, month, year);
        expAllList.getAllExpense().add(exp);
    }

    // MODIFIES: N/A
    // EFFECTS: gives prompt for user to choose what view of expenses they would like to see, then it gives that view
    //          + the total spent for that category/date
    private void viewExpenseMenu() {
        initViewMenu();
        String option = scan.next();

        if (option.equals("a")) {
            viewByDay();
            resetScreen();
        } else if (option.equals("b")) {
            viewByMonth();
            resetScreen();
        } else if (option.equals("c")) {
            viewByYear();
            resetScreen();
        } else if (option.equals("d")) {
            System.out.print("Name:");
            viewByName(scan.next());
            resetScreen();
        }
    }

    // MODIFIES: N/A
    // EFFECTS: menu for the viewExpenseMenu
    private void initViewMenu() {
        System.out.println("Please select an option.");
        System.out.println("(a) View by Day");
        System.out.println("(b) View by Month");
        System.out.println("(c) View by Year");
        System.out.println("(d) View by Name");
    }

    // MODIFIES: N/A
    // EFFECTS: gives a receipt view of the expenses spent on the day chosen by user
    private void viewByDay() {
        System.out.print("Day: ");
        int day = scan.nextInt();
        System.out.print("Month: ");
        int month = scan.nextInt();
        System.out.print("Year: ");
        int year = scan.nextInt();

        ArrayList<Expense> expAtDay = expAllList.getExpenseAtDay(day,month,year);

        for (Expense e : expAtDay) {
            this.sum += e.getAmount();
            System.out.println(e.printExpense());
        }
        System.out.println("Total: " + sum);
        // reset sum count as we're using the same variable for all sum
        this.sum = 0;
    }

    // MODIFIES: N/A
    // EFFECTS: gives a receipt view of the expenses spent on the month chosen by user
    private void viewByMonth() {
        System.out.print("Month: ");
        int month = scan.nextInt();
        System.out.print("Year: ");
        int year = scan.nextInt();

        ArrayList<Expense> expAtMonth = expAllList.getExpenseAtMonth(month,year);

        for (Expense e : expAtMonth) {
            this.sum += e.getAmount();
            System.out.println(e.printExpense());
        }
        System.out.println("Total: " + sum);
        this.sum = 0;
    }

    // MODIFIES: N/A
    // EFFECTS: gives a receipt view of the expenses spent on the year chosen by user
    private void viewByYear() {
        System.out.print("Year: ");
        int year = scan.nextInt();

        ArrayList<Expense> expAtYear = expAllList.getExpenseAtYear(year);

        for (Expense e : expAtYear) {
            this.sum += e.getAmount();
            System.out.println(e.printExpense());
        }
        System.out.println("Total: " + sum);
        this.sum = 0;
    }

    // MODIFIES: N/A
    // EFFECTS: gives a receipt view of the expenses spent on the category chosen by user
    private void viewByName(String purchaseName) {
        ArrayList<Expense> expAtYear = expAllList.getAllExpense();

        for (Expense e : expAtYear) {
            if (e.getName().equals(purchaseName)) {
                this.sum += e.getAmount();
                System.out.println(e.printExpense());
            }
        }
        System.out.println("Total: " + sum);
        this.sum = 0;
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadWorkRoom() {
        try {
            expAllList = jsonReader.read();
            System.out.println("Loaded " + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    private void saveWorkRoom() {
        try {
            jsonWriter.open();
            jsonWriter.write(expAllList);
            jsonWriter.close();
            System.out.println("Saved " + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }
}
