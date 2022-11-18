package ui.menu;

import model.Expense;
import model.ListOfExpense;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// TODO: change class description
// represents application's name zoo menu window
public class AddExpenseMenu extends Menu {

    // labels
    private JLabel nameLabel;
    private JLabel amountLabel;
    private JLabel dayLabel;
    private JLabel monthLabel;
    private JLabel yearLabel;

    private JButton enter;

    private JTextField nameInput;
    private JTextField amountInput;
    private JTextField dayInput;
    private JTextField monthInput;
    private JTextField yearInput;


    public AddExpenseMenu(ListOfExpense listOfExpense) {
        super(listOfExpense);

        initializeApp();
        initializeLabels();
        initializeButtons();
        initializeListeners();

        addToPanel();
        panel.revalidate();
        panel.repaint();
    }

    // MODIFIES: this
    // EFFECTS: Initializes and adds text to all the present labels in this menu
    @Override
    public void initializeLabels() {
        nameLabel = new JLabel("Enter name of purchase");
        amountLabel = new JLabel("Enter amount");
        dayLabel = new JLabel("Enter day of purchase");
        monthLabel = new JLabel("Enter month of purchase");
        yearLabel = new JLabel("Enter year of purchase");
    }

    // MODIFIES: this
    // EFFECTS: Initializes all action listeners
    @Override
    public void initializeListeners() {
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Expense expense1 = new Expense(
                        Double.parseDouble(amountInput.getText()),
                        nameInput.getText(),
                        Integer.parseInt(dayInput.getText()),
                        Integer.parseInt(monthInput.getText()),
                        Integer.parseInt(yearInput.getText()));

                listOfExpense.addExpense(expense1);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: adds buttons that control the actions naming the zoo
    private void initializeButtons() {
        nameInput = new JTextField(20);
        amountInput = new JTextField(20);
        dayInput = new JTextField(20);
        monthInput = new JTextField(20);
        yearInput = new JTextField(20);






        enter = new JButton("Enter");

    }

    // MODIFIES: Menu (super)
    // EFFECTS: adds all necessary elements to the panel
    @Override
    public void addToPanel() {
        // Expense name
        panel.add(nameLabel);
        panel.add(nameInput);

        // Expense amount
        panel.add(amountLabel);
        panel.add(amountInput);

        // Expense day
        panel.add(dayLabel);
        panel.add(dayInput);

        // Expense month
        panel.add(monthLabel);
        panel.add(monthInput);

        // Expense year
        panel.add(yearLabel);
        panel.add(yearInput);

        panel.add(enter);
    }

    // MODIFIES: Menu (super)
    // EFFECTS: initializes the JFrame components
    private void initializeApp() {
        frame.setTitle("Budget Tracker: Add Expense");
        panel.setLayout(new GridLayout(8, 2, 20, 10));
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    }

}
