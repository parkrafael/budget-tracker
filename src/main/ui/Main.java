package ui;

import model.ListOfExpense;
import ui.menu.UserLoginMenu;
import ui.menu.view.DayViewMenu;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            UserLoginMenu userLoginMenu = new UserLoginMenu(new ListOfExpense(""));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
