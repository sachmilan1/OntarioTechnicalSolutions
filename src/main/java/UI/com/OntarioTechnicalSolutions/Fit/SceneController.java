package com.OntarioTechnicalSolutions.Fit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;
import java.sql.*;
import java.util.ResourceBundle;

public class SceneController {
    @FXML
    TextField usernameLogin;
    @FXML
    PasswordField passwordLogin;

    @FXML
    TextField name;
    @FXML
    TextField email;
    @FXML
    TextField registerUsername;
    @FXML
    TextField registerPassword;

    @FXML
    Label errorLabel;
    @FXML
    ComboBox<String> comboBox;

    @FXML
    private Label isConnected;

    // Test HashMap
    private Stage stage;
    private Scene scene;
    private Parent root;


    public void switchToHomePage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HomePage.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToFavourites(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToRegister(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("RegisterScreen.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToFavourites() {

    }

    public void loginScreen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("com/OntarioTechnicalSolutions/Fit/LoginScreen.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    Image loginImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/download.jpg")));
    ImageView loginImageView;

    public void displayImage() {
        loginImageView.setImage(loginImage);
    }

    public void loginDetails(ActionEvent event) throws IOException {
        String getName = usernameLogin.getText();
        String getPassword = passwordLogin.getText();
//
//        if (getName != null && !getPassword.isEmpty()) {
//            try {
//                PreparedStatement st = ConnectionProvider.getCon().prepareStatement("Select * FROM Users where username=?");
//                st.setString(1, getName);
//                ResultSet rs = st.executeQuery();
//
//                if (rs.next()) {
//                    String pass = rs.getString("password");
//                    if (pass.equals(getPassword)) {
//                        switchToHomePage(event);
//                    } else {
//                        errorLabel.setText("Username or Password is Incorrect");
//                    }
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
        switchToHomePage(event);
    }
    public void registerDetails(ActionEvent event) throws IOException {
        loginScreen(event);
    }

//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        String[] items = new String[] {"Home", "Favourites", "Workouts"};
//        comboBox.getItems().addAll(items);
//    }


}

