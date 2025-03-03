package com.OntarioTechnicalSolutions.Fit;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        //new LoginScreen().login();
        Connection con = ConnectionProvider.getCon();
        Application.launch(LoginScreenFX.class, args);

    }

}
