package com.OntarioTechnicalSolutions.Fit;

import javax.swing.*;
import java.util.Objects;

public class CurrentUser {
    private static CurrentUser currentUser;
    private int userID;
    private String userName;
    private boolean isGuest;
    private boolean isAdmin;

    private CurrentUser() {
        userID = -1;
        userName = "";
        isGuest = false;
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

    public void setGuest(boolean isGuest) {
        this.isGuest = isGuest;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public int getUserID() {
        if (userID == -1) {
            return -1;
        }
        return userID;
    }

    public boolean getAdmin() {
        return isAdmin;
    }

    public String getUserName() {
        if (Objects.equals(userName, "")) {

            return "";
        }
        return userName;
    }
}