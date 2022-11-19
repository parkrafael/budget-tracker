package ui.menu;

import model.Expense;
import model.ListOfExpense;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents application's view expenses on specific day
public class DayViewMenu extends Menu {
    // labels
    private JLabel titleLabel;
    private JLabel dayLabel;
    private JLabel monthLabel;
    private JLabel yearLabel;
    private JLabel totalAmount;

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

        frame.setTitle("Budget Tracker: View by Day");

        initializeButtons();
        initializeLabels();
        initializeListeners();

        addToPanel();
        panel.revalidate();
        panel.repaint();
    }

    // MODIFIES: this
    // EFFECTS: initializes & adds text to all the present labels in this menu
    //          + initializes the table & adds columns
    @Override
    public void initializeLabels() {
        // labels
        titleLabel = new JLabel("View By Day");
        dayLabel = new JLabel("Day:");
        monthLabel = new JLabel("Month:");
        yearLabel = new JLabel("Year:");
        totalAmount = new JLabel("Total Amount: 0.00");

        // table
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
                tableModel.setRowCount(0);

                // add expense to rows
                for (Expense exp : listOfExpense.getListOfExpense()) {
                    if (dayInput.getText().equals(Integer.toString(exp.getDay()))
                            && monthInput.getText().equals(Integer.toString(exp.getMonth()))
                            && yearInput.getText().equals(Integer.toString(exp.getYear()))) {
                        tableModel.addRow(new Object[]{exp.getName(), Double.toString(exp.getAmount())});
                    }
                }

                // sums up all expenses in column
                Double sum = 0.00;

                for (int d = 0; d < tableModel.getRowCount(); d++) {
                    sum = sum + Double.parseDouble(tableModel.getValueAt(d, 1).toString());
                }

                totalAmount.setText("Total Amount" + Double.toString(sum));
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: initializes all buttons used in menu
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

        panel.add(totalAmount);
    }

}
