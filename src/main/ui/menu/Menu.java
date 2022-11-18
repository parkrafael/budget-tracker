package ui.menu;

import model.Expense;
import model.ListOfExpense;

import javax.swing.*;
import java.awt.*;

public abstract class Menu extends JFrame {

    protected ListOfExpense listOfExpense;
    protected Expense expense;

    public static final int WIDTH = 900;
    public static final int HEIGHT = 600;

    protected JFrame frame;
    protected JPanel panel;

    // creates a generic menu
    public Menu(ListOfExpense listOfExpense) {
        // creates new ListOfExpense
        this.listOfExpense = listOfExpense;

        // instantiates new JFrame and JPanel
        frame = new JFrame();
        panel = new JPanel();

        // instantiates frame
        frame.setSize(WIDTH, HEIGHT);
        frame.setTitle("Budget Tracker");
        frame.setVisible(true);
        frame.add(panel, BorderLayout.CENTER);

        // instantiates panel
        panel.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        panel.setLayout(new GridLayout(5, 1, 8, 10));
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    }

    // EFFECTS: generates empty label to act as an empty space
    protected JLabel createSpaceLabel() {
        JLabel space = new JLabel("   ");
        return space;
    }

    // MODIFIES: this
    // EFFECTS: initializes all present labels to be added to the panel that are not empty or need to
    // added multiple times
    public abstract void initializeLabels();

    // MODIFIES: this
    // EFFECTS: initializes all listeners for every button present in the menu
    public abstract void initializeListeners();

    // MODIFIES: this
    // EFFECTS: adds all the necessary components to the panel
    public abstract void addToPanel();

}
