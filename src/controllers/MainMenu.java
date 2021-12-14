package controllers;

import models.User;

import java.util.ArrayList;

public class MainMenu {
    
    private ArrayList<User> users;
    private User user;
    
    public MainMenu(ArrayList<User> users, User user) {
        this.users = users;
        this.user = user;
        if (users.isEmpty()) {
            menu();
        }
        else {
            adminMenu();
        }
    }

    private void menu() {
        new CheckStock();
    }

    private void adminMenu() {
        new CheckStock();
    }
}
