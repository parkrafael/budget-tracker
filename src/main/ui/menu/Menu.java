package ui.menu;

import model.Expense;
import model.ListOfExpense;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public abstract class Menu extends JFrame {
    // ==============================
    // FIELDS:

    protected ListOfExpense listOfExpense;
    protected Expense expense;

    public static final int WIDTH = 530;
    public static final int HEIGHT = 650;

    protected JFrame frame;
    protected JPanel panel;
    protected Border border;
    protected Border margin;

    // ==============================
    // CONSTRUCTOR:

    // EFFECTS: creates a generic menu
    public Menu(ListOfExpense listOfExpense) {
        // creates new ListOfExpense
        this.listOfExpense = listOfExpense;

        // new JFrame and JPanel
        frame = new JFrame();
        panel = new JPanel();

        // instantiates panel
        // https://stackoverflow.com/questions/3180535/how-to-make-an-input-form-in-java-code-not-netbeans-using-jform
        border = panel.getBorder();
        margin = new EmptyBorder(10, 10, 10, 10);
        panel.setBorder(new CompoundBorder(border, margin));

        GridBagLayout panelGridBagLayout = new GridBagLayout();
        panelGridBagLayout.columnWidths = new int[] { 10, 86, 0 };
        panelGridBagLayout.rowHeights = new int[] { 20, 20, 20, 20, 20, 0 };
        panelGridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
        panelGridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel.setLayout(panelGridBagLayout);

        // instantiates frame
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.add(panel);
    }

    // ==============================
    // METHODS:

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

    // MODIFIES: this
    // EFFECTS: adds/aligns label and text fields to panels
    // https://stackoverflow.com/questions/3180535/how-to-make-an-input-form-in-java-code-not-netbeans-using-jform
    public void addLabelandTextField(JLabel labelText, JTextField textField, int y, Container container) {
        GridBagConstraints gridBagConstraintForLabel = new GridBagConstraints();
        gridBagConstraintForLabel.fill = GridBagConstraints.BOTH;
        gridBagConstraintForLabel.insets = new Insets(0, 0, 5, 5);
        gridBagConstraintForLabel.gridx = 0;
        gridBagConstraintForLabel.gridy = y;
        panel.add(labelText, gridBagConstraintForLabel);

        GridBagConstraints gridBagConstraintForTextField = new GridBagConstraints();
        gridBagConstraintForTextField.fill = GridBagConstraints.BOTH;
        gridBagConstraintForTextField.insets = new Insets(0, 0, 5, 0);
        gridBagConstraintForTextField.gridx = 1;
        gridBagConstraintForTextField.gridy = y;
        textField.setColumns(10);
        panel.add(textField, gridBagConstraintForTextField);
    }

    // MODIFIES: this
    // EFFECTS: adds/aligns label fields to panels
    // https://stackoverflow.com/questions/3180535/how-to-make-an-input-form-in-java-code-not-netbeans-using-jform
    public void addLabel(JLabel label, int y, Container container) {
        GridBagConstraints gridBagConstraintForLabel = new GridBagConstraints();
        gridBagConstraintForLabel.fill = GridBagConstraints.CENTER;
        gridBagConstraintForLabel.insets = new Insets(0, 0, 5, 5);
        gridBagConstraintForLabel.gridx = 1;
        gridBagConstraintForLabel.gridy = y;
        panel.add(label, gridBagConstraintForLabel);
    }

    // MODIFIES: this
    // EFFECTS: adds/aligns text fields to panels
    // https://stackoverflow.com/questions/3180535/how-to-make-an-input-form-in-java-code-not-netbeans-using-jform
    public void addTextField(JTextField textField, int y, Container container) {
        GridBagConstraints gridBagConstraintForLabel = new GridBagConstraints();
        gridBagConstraintForLabel.fill = GridBagConstraints.CENTER;
        gridBagConstraintForLabel.insets = new Insets(0, 0, 5, 5);
        gridBagConstraintForLabel.gridx = 1;
        gridBagConstraintForLabel.gridy = y;
        panel.add(textField, gridBagConstraintForLabel);
    }

    // MODIFIES: this
    // EFFECTS: adds/aligns button fields to panels
    // https://stackoverflow.com/questions/3180535/how-to-make-an-input-form-in-java-code-not-netbeans-using-jform
    public void addButton(JButton button, int y, Container container) {
        GridBagConstraints gridBagConstraintForLabel = new GridBagConstraints();
        gridBagConstraintForLabel.fill = GridBagConstraints.CENTER;
        gridBagConstraintForLabel.insets = new Insets(0, 0, 5, 5);
        gridBagConstraintForLabel.gridx = 1;
        gridBagConstraintForLabel.gridy = y;
        panel.add(button, gridBagConstraintForLabel);
    }

    // MODIFIES: this
    // EFFECTS: adds/aligns table fields to panels
    // https://stackoverflow.com/questions/3180535/how-to-make-an-input-form-in-java-code-not-netbeans-using-jform
    public void addTable(JScrollPane table, int y, Container container) {
        GridBagConstraints gridBagConstraintForLabel = new GridBagConstraints();
        gridBagConstraintForLabel.fill = GridBagConstraints.CENTER;
        gridBagConstraintForLabel.insets = new Insets(0, 0, 5, 5);
        gridBagConstraintForLabel.gridx = 1;
        gridBagConstraintForLabel.gridy = y;
        panel.add(table, gridBagConstraintForLabel);
    }

}
