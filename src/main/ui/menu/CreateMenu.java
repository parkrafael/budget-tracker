package ui.menu;

import model.ListOfExpense;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents application's create habitat menu window
public class CreateMenu extends Menu {

    // labels
    private JLabel titleLabel;
    private JLabel nameLabel;
    private JLabel tempLabel;
    private JLabel capLabel;

    private JButton enter1;
    private JTextField nameInput;
    private JTextField tempInput;
    private JTextField capInput;

    public CreateMenu(ListOfExpense listOfExpense) {
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
        titleLabel = new JLabel("Please enter the following information for your habitat:");
        nameLabel = new JLabel("Name:");
        tempLabel = new JLabel("Average Temperature:");
        capLabel = new JLabel("Capacity:");
    }

    // MODIFIES: this
    // EFFECTS: Initializes all action listeners
    @Override
    public void initializeListeners() {
        enter1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: adds buttons that control the actions to creating a habitat
    private void initializeButtons() {
        nameInput = new JTextField(20);
        tempInput = new JTextField(20);
        capInput = new JTextField(20);
        enter1 = new JButton("Enter");

    }

    // MODIFIES: Menu (super)
    // EFFECTS: adds all the necessary components to the panel
    @Override
    public void addToPanel() {
        panel.add(titleLabel);
        panel.add(nameLabel);
        panel.add(nameInput);
        panel.add(tempLabel);
        panel.add(tempInput);
        panel.add(capLabel);
        panel.add(capInput);
        panel.add(enter1);
    }

    // MODIFIES: Menu (super)
    // EFFECTS: initializes the JFrame components p
    private void initializeApp() {
        frame.setTitle("Budget Tracker: View Expenses");
        panel.setLayout(new GridLayout(8, 2, 20, 10));
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    }
}
