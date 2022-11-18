package ui.menu;

import model.Expense;
import model.ListOfExpense;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// TODO: change class descriptor
// represents application's view habitat menu window
public class DayViewMenu extends Menu {

    private ArrayList<JLabel> expenseList;

    // labels
    private JLabel titleLabel;
    private JLabel dayLabel;
    private JLabel monthLabel;
    private JLabel yearLabel;

    // buttons
    private JButton enter;

    // text field
    private JTextField dayInput;
    private JTextField monthInput;
    private JTextField yearInput;

    // table
    private DefaultTableModel tableModel;
    private JTable table;

    public DayViewMenu(ListOfExpense listOfExpense) {
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
        titleLabel = new JLabel("View By Day");
        dayLabel = new JLabel("Day:");
        monthLabel = new JLabel("Month:");
        yearLabel = new JLabel("Year:");

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        tableModel.addColumn("Name");
        tableModel.addColumn("Amount");
    }

    // MODIFIES: this
    // EFFECTS: Initializes all action listeners
    @Override
    public void initializeListeners() {
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Expense> checker = new ArrayList<>();
                tableModel.setRowCount(0);

                for (Expense exp : listOfExpense.getListOfExpense()) {
                    if (dayInput.getText().equals(Integer.toString(exp.getDay()))
                            && monthInput.getText().equals(Integer.toString(exp.getMonth()))
                            && yearInput.getText().equals(Integer.toString(exp.getYear()))) {
                        tableModel.addRow(new Object[]{exp.getName(), Double.toString(exp.getAmount())});
                    }
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: adds buttons that control the actions to viewing the habitat
    private void initializeButtons() {
        dayInput = new JTextField(20);
        monthInput = new JTextField(20);
        yearInput = new JTextField(20);
        enter = new JButton("Enter");
    }

    // MODIFIES: Menu (super)
    // EFFECTS: adds all the necessary components to the panel
    @Override
    public void addToPanel() {
        panel.add(titleLabel);

        panel.add(createSpaceLabel());

        panel.add(dayLabel);
        panel.add(dayInput);

        panel.add(monthLabel);
        panel.add(monthInput);

        panel.add(yearLabel);
        panel.add(yearInput);

        panel.add(new JScrollPane(table));

        panel.add(enter);
    }

    // MODIFIES: Menu (super)
    // EFFECTS: initializes the JFrame components
    private void initializeApp() {
        frame.setTitle("Budget Tracker: View by Day");
        panel.setLayout(new GridLayout(8, 1, 20, 10));
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    }

}
