package ui;

import model.Expense;
import model.ListOfExpense;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BudgetCalculator {
    // ==============================
    // FIELDS:

    // data persistence:
    private final JsonWriter jsonWriter;
    private static final String JSON_STORE = "./data/workroom.json";

    // sum (for calculating total):
    double sum;

    // scanner:
    Scanner scan = new Scanner(System.in);

    // instantiating expense & listOfExpense:
    Expense expense;
    ListOfExpense listOfExpense = new ListOfExpense();

    // ==============================
    // CONSTRUCTOR:

    // EFFECTS: runs the budget calculator application
    public BudgetCalculator() {
        jsonWriter = new JsonWriter(JSON_STORE);
//        jsonReader = new JsonReader(JSON_STORE);
        runBudgetCalculator();
    }

    // ==============================
    // METHODS:

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runBudgetCalculator() {
        // initial menu:
        initMenu();

        // instantiating scanner:
        String option = scan.next();

        // options:
        switch (option) {
            case "a":
                addExpenseMenu();
                resetScreen();
                break;
            case "b":
                viewExpenseMenu();
                resetScreen();
                break;
            case "c":
                saveWorkRoom();
                resetScreen();
                break;
            case "d":
                // TODO: add load option here
                return;
            case "e":
        }
    }

    // MODIFIES: this
    // EFFECTS: menu for the initial screen
    private void initMenu() {
        System.out.println("Welcome to your Budget Book! Please select an option.");
        System.out.println("(a) Add expense");
        System.out.println("(b) View expense");
        System.out.println("(c) Save");
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
            System.out.println(listOfExpense.getListOfExpense());
            runBudgetCalculator();
        }
    }

    // MODIFIES: this
    // EFFECTS: creates new expense with input from user, adds expense to expAllList
    private void addExpenseMenu() {
        // fields:
        double amount;
        String name;
        int day;
        int month;
        int year;

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

        expense = new Expense(amount, name, day, month, year);
        listOfExpense.addExpense(expense);
    }

    // MODIFIES: N/A
    // EFFECTS: gives prompt for user to choose what view of expenses they would like to see, then it gives that view
    //          + the total spent for that category/date
    private void viewExpenseMenu() {
        // menu for expense view:
        initViewMenu();

        // scanner:
        String option = scan.next();

        // options:
        switch (option) {
            case "a":
                viewByDay();
                resetScreen();
                break;
            case "b":
                viewByMonth();
                resetScreen();
                break;
            case "c":
                viewByYear();
                resetScreen();
            case "d":
                System.out.print("Name:");
                viewByName(scan.next());
                resetScreen();
                break;
            default:
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

        ArrayList<Expense> expAtDay = listOfExpense.getExpenseAtDay(day,month,year);

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

        ArrayList<Expense> expAtMonth = listOfExpense.getExpenseAtMonth(month,year);

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

        ArrayList<Expense> expAtYear = listOfExpense.getExpenseAtYear(year);

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
        ArrayList<Expense> expAtYear = listOfExpense.getListOfExpense();

        for (Expense e : expAtYear) {
            if (e.getName().equals(purchaseName)) {
                this.sum += e.getAmount();
                System.out.println(e.printExpense());
            }
        }
        System.out.println("Total: " + sum);
        this.sum = 0;
    }

    // ==============================
    // DATA PERSISTENCE:

    private void saveWorkRoom() {
        try {
            jsonWriter.open();
            jsonWriter.write(listOfExpense);
            jsonWriter.close();
            System.out.println("Saved " + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

//    // MODIFIES: this
//    // EFFECTS: loads workroom from file
//    private void loadWorkRoom() {
//        try {
//            expAllList = jsonReader.read();
//            System.out.println("Loaded " + " from " + JSON_STORE);
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_STORE);
//        }
//    }

}
