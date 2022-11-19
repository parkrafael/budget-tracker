
package ui.menu;

import model.ListOfExpense;

import javax.imageio.ImageIO;
import javax.swing.*;
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

    // data persistence
    private String directory;

    // labels
    private JLabel userLabel;
    private JLabel success;

    // text fields
    private JTextField userText;

    // buttons
    private JButton button;

    public UserLoginMenu(ListOfExpense listOfExpense) throws IOException {
        super(listOfExpense);

        BufferedImage myPicture = ImageIO.read(new File("./data/title.jpeg"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        panel.add(picLabel);

        initializeApp();
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
        userLabel = new JLabel("User");
        userLabel.setBounds(10, 20, 80, 25);

        userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);

        success = new JLabel("");
        success.setBounds(10,110,300,25);
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
                    new StartMenu(listOfExpense, name);

                } else {
                    directory = "./data/" + name + ".json";
                    listOfExpense.changeName(name);
                    new StartMenu(listOfExpense, name);
                }
            }
        });
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
        panel.add(userLabel);
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userText);
        userText.setBounds(100, 20, 165, 25);
        panel.add(button);
    }

    // MODIFIES: Menu (super)
    // EFFECTS: initializes the JFrame components
    private void initializeApp() {
        frame.setTitle("Budget Tracker: Login");
    }

}

