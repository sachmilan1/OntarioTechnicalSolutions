package com.OntarioTechnicalSolutions.Fit;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class LoginScreenFX extends Application {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @Override
    public void start(Stage stage) throws IOException {

        URL fxmlFile = getClass().getResource("/FXML/LoginScreen.fxml");
        System.out.println("FXML File Path: " + fxmlFile);

        if (fxmlFile == null) {
            throw new RuntimeException("FXML file not found! Check the file path.");
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/LoginScreen.fxml"));
        Parent root = fxmlLoader.load();
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/icon.png"))));
        stage.setTitle("Fitness App");
        stage.setScene(new Scene(root));
        stage.show();
    }

}