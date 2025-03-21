package com.OntarioTechnicalSolutions.Fit;

import javax.swing.*;

public class CurrentUser {
    private static CurrentUser currentUser;
    private int userID;
    private String userName;

    private CurrentUser() {
        userID = -1;
        userName = "";
    }

    public static CurrentUser getInstance() {
        if (currentUser == null) {
            currentUser = new CurrentUser();
        }
        return currentUser;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserID() {
        if (userID == -1) {
            JOptionPane.showMessageDialog(null, "You are not logged in");
            return -1;
        }
        return userID;
    }

    public String getUserName() {
        if (userName == "") {
            JOptionPane.showMessageDialog(null, "You are not logged in");
            return "";
        }
        return userName;
    }
}
