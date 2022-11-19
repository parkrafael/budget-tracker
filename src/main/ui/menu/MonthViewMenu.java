package ui.menu;

import model.Expense;
import model.ListOfExpense;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// represents application's view expenses on specific month
public class MonthViewMenu extends Menu {
    // labels
    private JLabel titleLabel;
    private JLabel monthLabel;
    private JLabel yearLabel;
    private JLabel totalAmount;

    // buttons
    private JButton enter;

    // text field
    private JTextField monthInput;
    private JTextField yearInput;

    // table
    private DefaultTableModel tableModel;
    private JTable table;

    public MonthViewMenu(ListOfExpense listOfExpense) {
        super(listOfExpense);

        frame.setTitle("Budget Tracker: View by Month");

        initializeLabels();
        initializeButtons();
        initializeListeners();

        addToPanel();
        panel.revalidate();
        panel.repaint();
    }

    // MODIFIES: this
    // EFFECTS: initializes and adds text to all the present labels in this menu
    @Override
    public void initializeLabels() {
        titleLabel = new JLabel("View By Month");
        monthLabel = new JLabel("Month:");
        yearLabel = new JLabel("Year:");
        totalAmount = new JLabel("Total Amount: 0.00");

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        tableModel.addColumn("Name");
        tableModel.addColumn("Amount");
    }

    // MODIFIES: this
    // EFFECTS: initializes all action listeners
    @Override
    public void initializeListeners() {
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Expense> checker = new ArrayList<>();
                tableModel.setRowCount(0);

                for (Expense exp : listOfExpense.getListOfExpense()) {
                    if (monthInput.getText().equals(Integer.toString(exp.getMonth()))
                            && yearInput.getText().equals(Integer.toString(exp.getYear()))) {
                        tableModel.addRow(new Object[]{exp.getName(), Double.toString(exp.getAmount())});
                    }
                }

                Double sum = 0.00;

                for (int d = 0; d < tableModel.getRowCount(); d++) {
                    sum = sum + Double.parseDouble(tableModel.getValueAt(d, 1).toString());
                }

                totalAmount.setText("Total Amount: " + Double.toString(sum));
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: initializes all buttons & text fields used in menu
    private void initializeButtons() {
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

        panel.add(monthLabel);
        panel.add(monthInput);

        panel.add(yearLabel);
        panel.add(yearInput);

        panel.add(new JScrollPane(table));

        panel.add(enter);

        panel.add(totalAmount);
    }

}
