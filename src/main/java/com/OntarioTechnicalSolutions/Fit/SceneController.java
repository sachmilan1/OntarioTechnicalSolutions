package com.OntarioTechnicalSolutions.Fit;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.sql.*;
import java.util.ResourceBundle;

public class SceneController implements Initializable {
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


    // Test HashMap
    private Stage stage;
    private Scene scene;
    private Parent root;


    //--------------------------SWITCH PAGES----------------------//
    public void switchToHomePage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/HomePage.fxml")));
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
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    //-------------------------------------------------------------//




    //-------ADMIN SCREEN------------//
    public void homeScreenAdmin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/admin/AdminHomeScreen.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML ComboBox<String> workoutCategories;
    @FXML TextField workoutName;
    @FXML TextField workoutCalorieBurn;
    @FXML TextArea workoutDescription;
    @FXML TextField videoURL;
    @FXML TextField imageURL;
    TextFormatter<Integer> textFormatter = new TextFormatter<>(new IntegerStringConverter());
    public void addWorkoutScreen(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/admin/AdminAddWorkouts.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    public void addWorkoutToDatabase() {
        if (workoutName.getText().isEmpty() || workoutCalorieBurn.getText().isEmpty() || workoutCategories.getSelectionModel().getSelectedItem() == null || videoURL.getText().isEmpty() || imageURL.getText().isEmpty() || workoutDescription.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill ALL fields");
        } else {
            AddWorkout.addToDatabase(workoutName.getText(), workoutCategories.getValue(), workoutCalorieBurn.getText(), workoutDescription.getText(), videoURL.getText(), imageURL.getText());
        }
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
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    String pass = rs.getString("password");
                    boolean isAdmin = rs.getBoolean("isAdmin");
                    if(isAdmin && pass.equals(password)) {
                        JOptionPane.showMessageDialog(null, "Admin Login", "Success", JOptionPane.INFORMATION_MESSAGE);
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
                    PreparedStatement st = con.prepareStatement("insert into users(name,email,username,password,isAdmin) values (?,?,?,?,?)");

                    st.setString(1, name.getText());
                    st.setString(2, email.getText());
                    st.setString(3, registerUsername.getText());
                    st.setString(4, registerPassword.getText());
                    st.setBoolean(5, name.getText().equals("admin") && registerUsername.getText().equals("admin"));

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


    // Initialize Specific FXML Components
    @Override
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        if (workoutCategories != null) {
            workoutCategories.setItems(FXCollections.observableArrayList("Cardio", "Legs", "Arms", "Back", "Chest"));
        }
        if (workoutCalorieBurn != null) {
            workoutCalorieBurn.setTextFormatter(textFormatter);
        }

    }


}