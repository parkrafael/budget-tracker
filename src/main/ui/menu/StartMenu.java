package ui.menu;

import model.ListOfExpense;

import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Year;

// represents application's start menu window
public class StartMenu extends Menu {

    private String file = "./data/Max.json";

    // labels
    private JLabel titleLabel;
    private JLabel saveMessage = new JLabel("Saved to ");
    private JLabel loadMessage = new JLabel("Loaded");

    //buttons
    private JButton name;
    private JButton dayView;
    private JButton monthView;
    private JButton yearView;
    private JButton nameView;
    private JButton save;
    private JButton load;

    // json
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public StartMenu(ListOfExpense listofExpense) {
        // creates a generic menu with ListOfExpense
         super(listofExpense);

        // String loginName = "Rafael";

        // instantiates jsonWriter + jsonReader
        // String file = "./data/" + loginName + ".json";

        jsonWriter = new JsonWriter(file);
        jsonReader = new JsonReader(file);

        // initialization
        initializeApp();
        initializeLabels();
        initializeButtons();
        initializeListeners();

        // creates new panel
        JPanel imagePanel = new JPanel();
        imagePanel.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        imagePanel.setLayout(new GridLayout(5, 0, 0, 0));
        imagePanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        // adds all buttons to panel
        addToPanel();
        panel.revalidate();
        panel.repaint();
    }

    // MODIFIES: Menu (super)
    // EFFECTS: initializes the JFrame components present in the menu
    private void initializeApp() {
        frame.setTitle("Budget Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(new GridLayout(8, 8, 20, 10));
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    }

    // MODIFIES: this
    // EFFECTS: Initializes and adds text to all the present labels in this menu
    @Override
    public void initializeLabels() {
        titleLabel = new JLabel("Budget Tracker");
    }

    // MODIFIES: this
    // EFFECTS: Initializes all action listeners with helper functions
    @Override
    public void initializeListeners() {
        initializeNameListener();
        initializeCreateEditListeners();
        initializeSaveLoadListeners();
    }

    // MODIFIES: this
    // EFFECTS: Initializes button listener for name
    private void initializeNameListener() {
        name.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddExpenseMenu addExpenseMenu = new AddExpenseMenu(listOfExpense);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: Initializes button listeners for create and edit
    private void initializeCreateEditListeners() {
        dayView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DayViewMenu dayViewMenu = new DayViewMenu(listOfExpense);
            }
        });
        monthView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MonthViewMenu monthViewMenu = new MonthViewMenu(listOfExpense);
            }
        });
        yearView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                YearViewMenu yearViewMenu = new YearViewMenu(listOfExpense);
            }
        });
        nameView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NameViewMenu nameViewMenu = new NameViewMenu(listOfExpense);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: Initializes button listeners for save and load
    private void initializeSaveLoadListeners() {

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFunction();
            }
        });

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadFunction();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: If save function is successful, it saves the zoo onto the JSON file
    //          else, provides error message
    private void saveFunction() {
        try {
            // instantiates jsonWriter
            jsonWriter.open();
            jsonWriter.write(listOfExpense);
            jsonWriter.close();

            // saves it to file according to name
            listOfExpense.toJson();

            // success message

            panel.remove(saveMessage);
            panel.add(saveMessage);

            panel.revalidate();
            panel.repaint();

        } catch (FileNotFoundException e) {
            // error message
            panel.add(loadMessage);
            panel.add(loadMessage);

            panel.revalidate();
            panel.repaint();

        }
    }


    // MODIFIES: this
    // EFFECTS: if load function is successful, it loads the zoo from the json file to be viewed
    //          else, provides error message
    private void loadFunction() {
        try {
            // instantiates jsonReader
            listOfExpense = jsonReader.read();

            // success message
            panel.add(saveMessage);
            panel.remove(saveMessage);

            panel.revalidate();
            panel.repaint();

        } catch (IOException e) {
            // error message
            panel.add(loadMessage);
            panel.remove(loadMessage);

            panel.revalidate();
            panel.repaint();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds buttons that control the actions to handle naming zoo, creating and viewing habitat,
    // loading and saving
    private void initializeButtons() {
        name = new JButton("Add Expense");
        dayView = new JButton("View by Day");
        monthView = new JButton("View By Month");
        yearView = new JButton("View By Year");
        nameView = new JButton("View By Name");
        load = new JButton("Load");
        save = new JButton("Save");
    }

    // MODIFIES: Menu (super)
    // EFFECTS: adds all the necessary components to the panel
    @Override
    public void addToPanel() {
        panel.add(titleLabel);
        panel.add(name);
        panel.add(dayView);
        panel.add(monthView);
        panel.add(yearView);
        panel.add(nameView);
        panel.add(save);
        panel.add(load);
    }


}
