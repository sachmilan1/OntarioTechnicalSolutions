package com.OntarioTechnicalSolutions.Fit;

import javafx.application.Application;

import java.io.File;
import java.net.URL;


public class Main {
    public static void main(String[] args) {
        //new LoginScreen().login();
        URL url = Main.class.getResource("/FXML/RegisterScreen.fxml");
        System.out.println(url);
        Application.launch(LoginScreenFX.class, args);

    }

}
