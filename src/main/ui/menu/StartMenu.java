package ui.menu;

import model.EventLog;
import model.ListOfExpense;

import ui.menu.view.DayViewMenu;
import ui.menu.view.MonthViewMenu;
import ui.menu.view.NameViewMenu;
import ui.menu.view.YearViewMenu;
import ui.persistence.JsonReader;
import ui.persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

// represents application's start menu window
public class StartMenu extends Menu {
    // ==============================
    // FIELDS:

    // labels
    private JLabel titleLabel;

    //buttons
    private JButton addExpense;
    private JButton dayView;
    private JButton monthView;
    private JButton yearView;
    private JButton nameView;
    private JButton save;
    private JButton load;

    // json
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // ==============================
    // CONSTRUCTOR:

    // EFFECTS: creates a start view menu
    public StartMenu(ListOfExpense listofExpense, String addExpense) {
        super(listofExpense);

        String directory = "./data/" + addExpense + ".json";

        jsonWriter = new JsonWriter(directory);
        jsonReader = new JsonReader(directory);

        loadFunction();

        // initialization
        initializeApp();
        initializeLabels();
        initializeButtons();
        initializeListeners();


        // adds all buttons to panel
        addToPanel();

        frame.setSize(530, 306);
    }

    // ==============================
    // METHODS:

    // MODIFIES: Menu (super)
    // EFFECTS: initializes the JFrame components present in the menu
    private void initializeApp() {
        frame.setTitle("Budget Tracker");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                listOfExpense.printLog(EventLog.getInstance());
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // MODIFIES: this
    // EFFECTS: Initializes and adds text to all the present labels in this menu
    @Override
    public void initializeLabels() {
        titleLabel = new JLabel("Budget Tracker");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 14f));
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
        addExpense.addActionListener(new ActionListener() {
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

            createFrame("Saved");
            listOfExpense.logSaveEvent();
        } catch (FileNotFoundException e) {
            // error
            createFrame("Error Saving");
        }
    }

    // MODIFIES: this
    // EFFECTS: if load function is successful, it loads the zoo from the json file to be viewed
    //          else, provides error message
    private void loadFunction() {
        try {
            // instantiates jsonReader
            listOfExpense = jsonReader.read();

            createFrame("Loaded");
            listOfExpense.logLoadEvent();
        } catch (IOException e) {
            // error
            createFrame("Error Loading");
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new frame that indicates whether it has been saved/loaded
    private void createFrame(String message) {
        JFrame frame1 = new JFrame();
        JPanel panel1 = new JPanel();
        JLabel label1 = new JLabel(message);

        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setVerticalAlignment(SwingConstants.CENTER);
        frame1.add(panel1);
        frame1.setSize(148, 59);
        frame1.setVisible(true);
        panel1.add(label1);
    }

    // MODIFIES: this
    // EFFECTS: adds buttons that control the actions to handle naming zoo, creating and viewing habitat,
    // loading and saving
    private void initializeButtons() {
        addExpense = new JButton("Add Expense");
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
        addLabel(titleLabel,0,panel);
        addButton(addExpense,1,panel);
        addButton(dayView,2,panel);
        addButton(monthView,3,panel);
        addButton(yearView,4,panel);
        addButton(nameView,5,panel);
        addButton(save,6,panel);
        addButton(load,7,panel);
    }

}
