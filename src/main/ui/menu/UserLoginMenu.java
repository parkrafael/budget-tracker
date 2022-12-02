
package ui.menu;

import model.ListOfExpense;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// represents application's log in menu
public class UserLoginMenu extends Menu {
    // ==============================
    // FIELDS:

    // data persistence
    private String directory;

    // labels
    private JLabel userLabel;

    // text fields
    private JTextField userText;

    // buttons
    private JButton button;

    // ==============================
    // CONSTRUCTOR:

    public UserLoginMenu(ListOfExpense listOfExpense) throws IOException {
        super(listOfExpense);

        BufferedImage myPicture = ImageIO.read(new File("./data/title.jpeg"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        addLabel(picLabel,0,panel);

        initializeApp();
        initializeLabels();
        initializeButtons();
        initializeListeners();

        addToPanel();
        panel.revalidate();
        panel.repaint();
    }

    // ==============================
    // METHODS:

    // MODIFIES: this
    // EFFECTS: initializes and adds text to all the present labels in this menu
    @Override
    public void initializeLabels() {
        userLabel = new JLabel("User: ");
        userText = new JTextField();
    }

    // MODIFIES: this
    // EFFECTS: initializes all action listeners
    @Override
    public void initializeListeners() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = userText.getText();
                directory = "./data/" + name + ".json";
                Path path = Paths.get(directory);

                if (Files.exists(path)) {
                    listOfExpense.changeName(name);
                    frame.dispose();
                    new StartMenu(listOfExpense, name);
                } else {
                    directory = "./data/" + name + ".json";
                    listOfExpense.changeName(name);
                    listOfExpense.logCreateEvent();
                    new StartMenu(listOfExpense, name);
                    frame.dispose();
                    createFrame("That user does not exist. Created new user, " + name + ".");
                }
            }
        });
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
        frame1.setSize(366, 63);
        frame1.setVisible(true);
        panel1.add(label1);
    }

    // MODIFIES: this
    // EFFECTS: initializes all buttons & text fields used in menu
    private void initializeButtons() {
        button = new JButton("Login");
        button.setBounds(10,80,80,25);
    }

    // MODIFIES: Menu (super)
    // EFFECTS: adds all the necessary components to the panel
    @Override
    public void addToPanel() {
        addLabelandTextField(userLabel,userText,1,panel);
        addButton(button,2, panel);
    }

    // MODIFIES: Menu (super)
    // EFFECTS: initializes the JFrame components
    private void initializeApp() {
        frame.setTitle("Budget Tracker: Login");
    }

}

