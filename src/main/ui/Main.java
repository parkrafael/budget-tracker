package ui;

import model.ListOfExpense;
import ui.menu.StartMenu;

public class Main {
    public static void main(String[] args) {
        new StartMenu(new ListOfExpense("ALL EXPENSES"));
    }
}
