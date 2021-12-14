package models;

import java.io.Serializable;

public class User implements Serializable {

    String forename;
    String surname;
    String userID;
    boolean admin;
    String password;

    public User(String forename, String surname, String userID, boolean admin, String password) {
        this.forename = forename;
        this.surname = surname;
        this.userID = userID;
        this.admin = admin;
        this.password = password;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

