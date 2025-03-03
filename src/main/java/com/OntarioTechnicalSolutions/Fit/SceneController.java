package com.OntarioTechnicalSolutions.Fit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.util.Objects;
import java.sql.*;

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
    Label welcomeText = new Label("Welcome");

    @FXML
    private Label isConnected;

    // Test HashMap
    private Stage stage;
    private Scene scene;
    private Parent root;


    //--------------------------SWITCH PAGES----------------------//
    public void switchToHomePage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/HomePage.fxml")));
        welcomeText.setText("Welcome" + usernameLogin.getText());
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
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/RegisterScreen.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void loginScreen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/LoginScreen.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    //-------------------------------------------------------------//

    //-------ADMIN SCREEN------------//
    public void homeScreenAdmin(ActionEvent event) throws IOException {
        Parent root = null;
    }


    //-------------------------------//





    public void loginDetails(ActionEvent event) throws IOException {
        String username = usernameLogin.getText();
        String password = passwordLogin.getText();

        if(username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please Enter Username and Password", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String query = "SELECT password, isAdmin FROM users WHERE username = ?";
        try (Connection con = ConnectionProvider.getCon()){
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    String pass = rs.getString("password");
                    boolean isAdmin = rs.getBoolean("isAdmin");
                    if(isAdmin && pass.equals(password)) {
                        homeScreenAdmin(event);
                    }
                    if(password.equals(pass)) {
                        JOptionPane.showMessageDialog(null, "Successfully logged in", "Success", JOptionPane.INFORMATION_MESSAGE);
                        switchToHomePage(event);
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect Username/Password", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "User not Found", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void registerDetails(ActionEvent event) throws IOException {
        if (registerUsername.getText() != null && !registerPassword.getText().isEmpty() && registerUsername.getText() != null && !name.getText().isEmpty()) {
            if (RepetitionChecker.emailChecker(email.getText())) {
                JOptionPane.showMessageDialog(null, "A user with this email already exists", "Error", JOptionPane.WARNING_MESSAGE);
            } else if (RepetitionChecker.nameChecker(registerUsername.getText())) {
                JOptionPane.showMessageDialog(null, "User Name already exists", "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                try (Connection con = ConnectionProvider.getCon();) {

                    PreparedStatement st = con.prepareStatement("insert into users(name,email,username,password,isAdmin)values(?,?,?,?,?)");

                    st.setString(1, name.getText());
                    st.setString(2, email.getText());
                    st.setString(3, registerUsername.getText());
                    st.setString(4, registerPassword.getText());

                    if (name.getText().equals("admin") && registerUsername.getText().equals("admin")) {
                        st.setBoolean(5, true);
                    } else {
                        st.setBoolean(5, false);
                    }
                    st.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Successfully Registered!");
                    con.close();
                    st.close();
                    loginScreen(event);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Cannot Leave Fields Empty", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }


//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        String[] items = new String[] {"Home", "Favourites", "Workouts"};
//        comboBox.getItems().addAll(items);
//    }


}

