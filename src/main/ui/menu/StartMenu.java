package ui.menu;

import model.ListOfExpense;

import ui.persistence.JsonReader;
import ui.persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// represents application's start menu window
public class StartMenu extends Menu {

    // labels
    private JLabel titleLabel;
    private JLabel saveMessage = new JLabel("Saved");
    private JLabel loadMessage = new JLabel("Loaded");
    private JLabel saveErrorMessage = new JLabel("Error Saving");
    private JLabel loadErrorMessage = new JLabel("Error Loading");

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

    public StartMenu(ListOfExpense listofExpense, String name) {
        super(listofExpense);

        String directory = "./data/" + name + ".json";

        jsonWriter = new JsonWriter(directory);
        jsonReader = new JsonReader(directory);

        loadFunction();

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
        }); monthView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MonthViewMenu monthViewMenu = new MonthViewMenu(listOfExpense);
            }
        }); yearView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                YearViewMenu yearViewMenu = new YearViewMenu(listOfExpense);
            }
        }); nameView.addActionListener(new ActionListener() {
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
//            panel.remove(saveMessage);
//            panel.add(saveMessage);

        } catch (FileNotFoundException e) {
            // error message
        }
    }


    // MODIFIES: this
    // EFFECTS: if load function is successful, it loads the zoo from the json file to be viewed
    //          else, provides error message
    private void loadFunction() {
        try {
            // instantiates jsonReader
            listOfExpense = jsonReader.read();

            // success
//            panel.remove(loadMessage);
//            panel.add(loadMessage);

        } catch (IOException e) {
            // error message
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
        save = new JButton("Save");
        load = new JButton("Load");
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
