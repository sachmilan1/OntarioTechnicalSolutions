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

public class LoginScreenFX extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("FXML/LoginScreen.fxml"));
        Parent root = fxmlLoader.load();
        stage.getIcons().add(new Image("images/icon.png"));
        stage.setTitle("Fitness App");
        stage.setScene(new Scene(root));
        stage.show();
    }

}