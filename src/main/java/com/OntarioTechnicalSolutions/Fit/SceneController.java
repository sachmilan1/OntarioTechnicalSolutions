package com.OntarioTechnicalSolutions.Fit;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.sql.*;
import java.util.List;


public class SceneController implements Initializable {
    @FXML
    TextField usernameLogin;
    @FXML
    PasswordField passwordLogin;
    @FXML
    Label welcomeLabel;

    @FXML
    TextField name;
    @FXML
    TextField email;
    @FXML
    TextField registerUsername;
    @FXML
    TextField registerPassword;

    @FXML
    ComboBox<String> menuItems;
    @FXML
    ComboBox<String> guestItems;
    @FXML
    ComboBox<String> adminBoxOptions;

    @FXML
    TextField searchbar;
    @FXML
    ListView<String> suggestions;

    @FXML
    VBox workoutVBOX;

    @FXML
    Label welcomeText;




    ArrayList<String> category = new ArrayList<>();



    private Stage stage;
    private Scene scene;
    private Parent root;


    //--------------------------SWITCH PAGES----------------------//
    public void switchToHomePage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXML/HomePage.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    public void switchToFavourites(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXML/FavouritesPage.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToRegister(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXML/RegisterScreen.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void loginScreen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXML/LoginScreen.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void switchToGuestHome(ActionEvent event) throws IOException {
        CurrentUser.getInstance().setGuest(true);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXML/GuestHomePage.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }
    public void guestWorkouts(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXML/GuestAllWorkouts.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void switchGuestPage(ActionEvent event) throws IOException {
        if (guestItems.getValue().equals("Home")) {
            switchToGuestHome(event);
        } else if (guestItems.getValue().equals("Logout")) {
            loginScreen(event);
        }
    }


    public void switchToAllWorkouts(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXML/workoutsREGULAR/AllWorkouts.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void searchToReg(ActionEvent event) throws IOException {
        String selectedItem = suggestions.getSelectionModel().getSelectedItem();
        if (selectedItem.equals("Arms")) {
            switchToArmsReg(event);
        }
        if(selectedItem.equals("Legs")) {
            switchToLegsReg(event);
        }
        if(selectedItem.equals("Cardio")) {
            switchToCardioReg(event);
        }
        if(selectedItem.equals("Back")) {
            switchToBackReg(event);
        }
        if (selectedItem.equals("Chest")) {
            switchToChestReg(event);
        }
    }

    public void menuSwitch(ActionEvent event) throws IOException {
        if (menuItems.getValue().equals("Home")) {
            switchToHomePage(event);
        }
        else if(menuItems.getSelectionModel().getSelectedItem().equals("Favourites")) {
            switchToFavourites(event);
        }
        else if (menuItems.getSelectionModel().getSelectedItem().equals("Logout")) {
            loginScreen(event);
        }
    }
    public void switchToBackReg(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXML/workoutsREGULAR/Back.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void switchToArmsReg(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXML/workoutsREGULAR/Arms.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void switchToLegsReg(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXML/workoutsREGULAR/Legs.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void switchToCardioReg(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXML/workoutsREGULAR/Cardio.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void switchToChestReg(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXML/workoutsREGULAR/Chest.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    //-------------------------------------------------------------//





    //--------------------------------------------------------------//


    //-------ADMIN SCREEN------------//
    @FXML ComboBox<String> workoutCategories;
    @FXML TextField workoutName;
    @FXML TextField workoutCalorieBurn;
    @FXML TextArea workoutDescription;
    @FXML TextField videoURL;
    @FXML TextField imageURL;
    @FXML ComboBox<String> workoutToEdit;
    @FXML ComboBox<String> usersDropBox;
    @FXML ComboBox<String> accessLevels;
    TextFormatter<Integer> textFormatter = new TextFormatter<>(new IntegerStringConverter());

    public void homeScreenAdmin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXML/admin/AdminHomeScreen.fxml")));
        System.out.println(root.toString());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void adminAddWorkouts(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXML/admin/AdminAddWorkouts.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


    public void adminRemoveWorkouts(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXML/admin/RemoveWorkouts.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void removeWorkoutFromDatabase(ActionEvent event) throws SQLException, IOException {
        if(workoutToEdit != null) {
            AddWorkout.removeWorkout(workoutToEdit.getValue());
            adminRemoveWorkouts(event);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a workout to remove");
        }
    }

    public void adminAccessEdit(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXML/admin/EditAdminAccess.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void accessEdit() throws IOException, SQLException {
        if(usersDropBox == null || accessLevels == null) {
            JOptionPane.showMessageDialog(null, "Please select a user to access");
        } else {
            if(accessLevels.getValue().equals("True")) {
                EditAdminAccess.editAdminAccess(usersDropBox.getValue(), true);
            } else {
                EditAdminAccess.editAdminAccess(usersDropBox.getValue(), false);
            }
        }
    }

    public void adminMenuSwitch(ActionEvent event) throws IOException {
        if (adminBoxOptions.getValue().equals("Home")) {
            homeScreenAdmin(event);
        }
        else if (adminBoxOptions.getSelectionModel().getSelectedItem().equals("Logout")) {
            loginScreen(event);
        }
    }


    public void addImage() {
        JFrame frame = new JFrame();
        JFileChooser chooser = new JFileChooser();
        frame.add(chooser);
        frame.setVisible(true);
        frame.setSize(800, 600);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.addActionListener(e -> {
            if (e.getActionCommand().equals("ApproveSelection")) {
                ProcessImage.getInstance().setPathToImage(chooser.getSelectedFile().getAbsolutePath());
                frame.dispose();
            } else {
                frame.dispose();
            }
        });
    }
    public void addWorkoutToDatabase() throws IOException {
        if (workoutName.getText().isEmpty() || workoutCalorieBurn.getText().isEmpty() || workoutCategories.getSelectionModel().getSelectedItem() == null || videoURL.getText().isEmpty() || workoutDescription.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill ALL fields");
        } else {
            AddWorkout.addToDatabase(workoutName.getText(), workoutCategories.getValue(), workoutCalorieBurn.getText(), workoutDescription.getText(), videoURL.getText(), ProcessImage.getInstance().getImage());
        }
    }

    public void editPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXML/admin/EditWorkout.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void editDatabaseWorkout() throws SQLException {
        if (workoutToEdit.getValue() == null || workoutName.getText().isEmpty() || workoutCalorieBurn.getText().isEmpty() || workoutCategories.getSelectionModel().getSelectedItem() == null || videoURL.getText().isEmpty() || imageURL.getText().isEmpty() || workoutDescription.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill ALL fields");
        } else {
            AddWorkout.editWorkouts(workoutToEdit.getValue(), workoutName.getText(), workoutCategories.getValue(), workoutCalorieBurn.getText(), workoutDescription.getText(), videoURL.getText(), imageURL.getText());
        }
    }

    public void AllWorkouts(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXML/workoutsADMIN/AllWorkouts.fxml")));
        System.out.println(root.toString());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void searchTo(ActionEvent event) throws IOException {
        String selectedItem = suggestions.getSelectionModel().getSelectedItem();
        if (selectedItem.equals("Arms")) {
            switchToArms(event);
        }
        if(selectedItem.equals("Legs")) {
            switchToLegs(event);
        }
        if(selectedItem.equals("Cardio")) {
            switchToCardio(event);
        }
        if(selectedItem.equals("Back")) {
            switchToBack(event);
        }
        if (selectedItem.equals("Chest")) {
            switchToChest(event);
        }
    }

    public void switchToBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXML/workoutsADMIN/Back.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void switchToArms(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXML/workoutsADMIN/Arms.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void switchToLegs(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXML/workoutsADMIN/Legs.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void switchToCardio(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXML/workoutsADMIN/Cardio.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void switchToChest(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXML/workoutsADMIN/Chest.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    //-------------------------------//



    public void loginDetails(ActionEvent event) throws IOException {
        String username = usernameLogin.getText();
        String password = passwordLogin.getText();

        if(username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please Enter Username and Password", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String query = "SELECT User_PK, username, password, isAdmin FROM users WHERE username = ?";
        try (Connection con = ConnectionProvider.getCon()){
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    String pass = rs.getString("password");
                    boolean isAdmin = rs.getBoolean("isAdmin");
                    if(isAdmin && pass.equals(password)) {
                        CurrentUser.getInstance().setUserID(rs.getInt("User_PK"));
                        CurrentUser.getInstance().setUserName(rs.getString("username"));
                        CurrentUser.getInstance().setGuest(false);

                        JOptionPane.showMessageDialog(null, "Logged in as " + username, "Success", JOptionPane.INFORMATION_MESSAGE);
                        homeScreenAdmin(event);
                    }
                    else if(password.equals(pass)) {
                        CurrentUser.getInstance().setUserID(rs.getInt("User_PK"));
                        CurrentUser.getInstance().setUserName(rs.getString("username"));
                        CurrentUser.getInstance().setGuest(false);

                        System.out.println(CurrentUser.getInstance().getUserID());
                        JOptionPane.showMessageDialog(null, "Successfully logged in as " + username, "Success", JOptionPane.INFORMATION_MESSAGE);
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

    @FXML VBox favouritesBox;
    private void displayFavourites() {
        favouritesBox.getChildren().clear();

        int userID = CurrentUser.getInstance().getUserID();
        if (userID == -1) {
            return;
        }

        try (Connection con = ConnectionProvider.getCon()) {
            PreparedStatement st = con.prepareStatement(
                    "SELECT workout_id FROM user_favorites WHERE user_id = ?");

            st.setInt(1, userID);
            List<Integer> workoutIDs = new ArrayList<>();
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                workoutIDs.add(resultSet.getInt("workout_id"));
            }



            for (int workoutID : workoutIDs) {
                try (PreparedStatement ps = con.prepareStatement(
                        "SELECT * FROM workouts WHERE workout_PK = ?"
                )) {
                    ps.setInt(1, workoutID);
                    ResultSet ts = ps.executeQuery();

                    if (ts.next()) {
                        Label workoutName = new Label(ts.getString("name"));

                        HBox workoutBox = new HBox();

                        Button removeButton = new Button("Remove from Favourites");
                        removeButton.setOnAction(event -> handleRemoveFavourites(event, userID, workoutID));
                        workoutName.setTextFill(Color.WHITE);
                        workoutBox.getChildren().addAll(workoutName, removeButton);
                        favouritesBox.getChildren().add(workoutBox);
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleRemoveFavourites(ActionEvent event, int userID, int workoutID) {
        try (Connection con = ConnectionProvider.getCon()) {
            PreparedStatement st = con.prepareStatement(
                    "DELETE FROM user_favorites WHERE user_id = ? AND workout_id = ?"
            );
            st.setInt(1, userID);
            st.setInt(2, workoutID);
            st.executeUpdate();

            displayFavourites();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void displayWorkouts() throws IOException {
        List<Map<String, String>> workoutsList = AddWorkout.retrieveWorkout();
        Map<String, byte[]> images = AddWorkout.retrieveImage();

        if (workoutsList != null && !workoutsList.isEmpty()) {
            for (Map<String, String> workout : workoutsList) {
                HBox workoutBox = new HBox();
                workoutBox.setSpacing(10);
                workoutBox.setStyle("-fx-padding: 10px; -fx-border-width: 1px; -fx-border-color: black;");

                Label workoutLabel = new Label(workout.get("name"));
                workoutLabel.setStyle("-fx-padding: 10px; -fx-border-width: 1px; -fx-border-color: black;");

                ImageView workoutImage = new ImageView();
                String workoutID = workout.get("id");
                byte[] imageData = images.get(workoutID);

                if (imageData.length > 0) {
                    Image image = new Image(new ByteArrayInputStream(imageData));
                    workoutImage.setImage(image);
                } else {
                    workoutImage.setImage(new Image(String.valueOf(getClass().getClassLoader().getResource("images/no_image.jpg"))));
                }

                workoutImage.setFitWidth(100);
                workoutImage.setFitHeight(100);
                workoutImage.setSmooth(true);
                workoutImage.setCache(true);
                workoutImage.setPreserveRatio(true);

                if (CurrentUser.getInstance().getUserID() != -1 && !CurrentUser.getInstance().getUserName().isEmpty()) {
                    Button addToFavouritesButton = new Button("Add to Favourites");
                    addToFavouritesButton.setOnAction(event -> {
                        try {
                            System.out.println(workout.get("id"));
                            AddToFavourites.addToFavourites(CurrentUser.getInstance().getUserID(), Integer.parseInt(workout.get("id")));
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    workoutLabel.setTextFill(Color.WHITE);
                    workoutBox.getChildren().addAll(workoutImage, workoutLabel, addToFavouritesButton);
                    workoutLabel.setTextFill(Color.WHITE);
                }

                else {
                    workoutLabel.setTextFill(Color.WHITE);
                    workoutBox.getChildren().addAll(workoutLabel);
                    workoutLabel.setTextFill(Color.WHITE);
                }
                workoutLabel.setOnMouseClicked(event -> {
                    showWorkoutDetails(workout);
                });

                workoutVBOX.getChildren().add(workoutBox);
            }
        } else {
            Label noWorkouts = new Label("No Workouts Found");
            workoutVBOX.getChildren().add(noWorkouts);
        }
    }


    @FXML VBox arms;
    private void displayArmsWorkouts() throws SQLException, IOException {
        List<Map<String, String>> workoutsList = AddWorkout.retrieveArmsWorkout();

        if (workoutsList != null && !workoutsList.isEmpty()) {
            for (Map<String, String> workout : workoutsList) {
                HBox workoutBox = new HBox();
                workoutBox.setSpacing(10);
                workoutBox.setStyle("-fx-padding: 10px; -fx-border-width: 1px; -fx-border-color: black;");

//                byte[] imageData = ProcessImage.getInstance().setPathToImage(workout.get("image_url"));
//                ImageView workoutImageView = new ImageView();
//                workoutLabel.setStyle("-fx-padding: 10px; -fx-border-width: 1px; -fx-border-color: black;");
//
                Label workoutLabel = new Label(workout.get("name"));
                workoutLabel.setStyle("-fx-text-fill: white;");
                Button addToFavouritesButton = new Button("Add to Favourites");
                addToFavouritesButton.setOnAction(event -> {
                    try {
                        System.out.println(workout.get("id"));
                        AddToFavourites.addToFavourites(CurrentUser.getInstance().getUserID(), Integer.parseInt(workout.get("id")));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                workoutLabel.setOnMouseClicked(event -> {
                    showWorkoutDetails(workout);
                });
                workoutLabel.setTextFill(Color.WHITE);
                workoutBox.getChildren().addAll(workoutLabel, addToFavouritesButton);
                arms.getChildren().add(workoutBox);
            }
        } else {
            Label noWorkouts = new Label("No Workouts Found");
            arms.getChildren().add(noWorkouts);
        }
    }

    @FXML VBox back;
    private void displayBackWorkouts() throws SQLException {
        List<Map<String, String>> workoutsList = AddWorkout.retrieveBackWorkout();

        if (workoutsList != null && !workoutsList.isEmpty()) {
            for (Map<String, String> workout : workoutsList) {
                HBox workoutBox = new HBox();
                workoutBox.setSpacing(10);
                workoutBox.setStyle("-fx-padding: 10px; -fx-border-width: 1px; -fx-border-color: black;");

                Label workoutLabel = new Label(workout.get("name"));
                workoutLabel.setStyle("-fx-padding: 10px; -fx-border-width: 1px; -fx-border-color: black;");

                Label label = new Label(workout.get("name"));

                Button addToFavouritesButton = new Button("Add to Favourites");
                addToFavouritesButton.setOnAction(event -> {
                    try {
                        System.out.println(workout.get("id"));
                        AddToFavourites.addToFavourites(CurrentUser.getInstance().getUserID(), Integer.parseInt(workout.get("id")));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                workoutLabel.setOnMouseClicked(event -> {
                    showWorkoutDetails(workout);
                });
                workoutLabel.setTextFill(Color.WHITE);
                workoutBox.getChildren().addAll(workoutLabel, addToFavouritesButton);
                back.getChildren().add(workoutBox);
            }
        } else {
            Label noWorkouts = new Label("No Workouts Found");
            back.getChildren().add(noWorkouts);
        }
    }



    @FXML VBox cardio;
    private void displayCardioWorkouts() throws SQLException {
        List<Map<String, String>> workoutsList = AddWorkout.retrieveCardioWorkout();

        if (workoutsList != null && !workoutsList.isEmpty()) {
            for (Map<String, String> workout : workoutsList) {
                HBox workoutBox = new HBox();
                workoutBox.setSpacing(10);
                workoutBox.setStyle("-fx-padding: 10px; -fx-border-width: 1px; -fx-border-color: black;");

                Label workoutLabel = new Label(workout.get("name"));
                workoutLabel.setStyle("-fx-padding: 10px; -fx-border-width: 1px; -fx-border-color: black;");

                Label label = new Label(workout.get("name"));

                Button addToFavouritesButton = new Button("Add to Favourites");
                addToFavouritesButton.setOnAction(event -> {
                    try {
                        System.out.println(workout.get("id"));
                        AddToFavourites.addToFavourites(CurrentUser.getInstance().getUserID(), Integer.parseInt(workout.get("id")));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                workoutLabel.setOnMouseClicked(event -> {
                    showWorkoutDetails(workout);
                });
                workoutLabel.setTextFill(Color.WHITE);
                workoutBox.getChildren().addAll(workoutLabel, addToFavouritesButton);
                cardio.getChildren().add(workoutBox);
            }
        } else {
            Label noWorkouts = new Label("No Workouts Found");
            cardio.getChildren().add(noWorkouts);
        }
    }

    @FXML VBox chest;
    private void displayChestWorkouts() throws SQLException {
        List<Map<String, String>> workoutsList = AddWorkout.retrieveChestWorkout();

        if (workoutsList != null && !workoutsList.isEmpty()) {
            for (Map<String, String> workout : workoutsList) {
                HBox workoutBox = new HBox();
                workoutBox.setSpacing(10);
                workoutBox.setStyle("-fx-padding: 10px; -fx-border-width: 1px; -fx-border-color: black;");

                Label workoutLabel = new Label(workout.get("name"));
                workoutLabel.setStyle("-fx-padding: 10px; -fx-border-width: 1px; -fx-border-color: black;");

                Label label = new Label(workout.get("name"));

                Button addToFavouritesButton = new Button("Add to Favourites");
                addToFavouritesButton.setOnAction(event -> {
                    try {
                        System.out.println(workout.get("id"));
                        AddToFavourites.addToFavourites(CurrentUser.getInstance().getUserID(), Integer.parseInt(workout.get("id")));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                workoutLabel.setOnMouseClicked(event -> {
                    showWorkoutDetails(workout);
                });
                workoutLabel.setTextFill(Color.WHITE);
                workoutBox.getChildren().addAll(workoutLabel, addToFavouritesButton);
                chest.getChildren().add(workoutBox);
            }
        } else {
            Label noWorkouts = new Label("No Workouts Found");
            chest.getChildren().add(noWorkouts);
        }
    }

    @FXML VBox legs;
    private void displayLegsWorkouts() throws SQLException {
        List<Map<String, String>> workoutsList = AddWorkout.retrieveLegsWorkout();

        if (workoutsList != null && !workoutsList.isEmpty()) {
            for (Map<String, String> workout : workoutsList) {
                HBox workoutBox = new HBox();
                workoutBox.setSpacing(10);
                workoutBox.setStyle("-fx-padding: 10px; -fx-border-width: 1px; -fx-border-color: black;");

                Label workoutLabel = new Label(workout.get("name"));
                workoutLabel.setStyle("-fx-padding: 10px; -fx-border-width: 1px; -fx-border-color: black;");

                Label label = new Label(workout.get("name"));

                Button addToFavouritesButton = new Button("Add to Favourites");
                addToFavouritesButton.setOnAction(event -> {
                    try {
                        System.out.println(workout.get("id"));
                        AddToFavourites.addToFavourites(CurrentUser.getInstance().getUserID(), Integer.parseInt(workout.get("id")));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                workoutLabel.setOnMouseClicked(event -> {
                    showWorkoutDetails(workout);
                });

                workoutLabel.setTextFill(Color.WHITE);
                workoutBox.getChildren().addAll(workoutLabel, addToFavouritesButton);
                legs.getChildren().add(workoutBox);
            }
        } else {
            Label noWorkouts = new Label("No Workouts Found");
            legs.getChildren().add(noWorkouts);
        }
    }

    private void showWorkoutDetails(Map<String, String> workout) {
        Platform.runLater(() -> {
            Stage videoStage = new Stage();
            VBox vBox = new VBox();
            vBox.setSpacing(10);
            vBox.setStyle("-fx-background-color: #666666;");

            Font labelFont = Font.font("OpenSans", FontWeight.BOLD, 16);
            Color labelColor = Color.WHITE;
            Label workoutName = styleLabel("Workout: " + workout.get("name"), labelFont, labelColor);
            Label category = styleLabel("Category: " + workout.get("category"), labelFont, labelColor);
            Label calories = styleLabel("Calories: " + workout.get("calories"), labelFont, labelColor);
            Label workoutDescription = styleLabel("Description: " + workout.get("description"), labelFont, labelColor);
            String videoURL = workout.get("video_url");
            if(videoURL == null || videoURL.isEmpty()) {
                videoURL = "https://www.youtube.com/embed/ykJmrZ5v0Oo";
            }

            WebView webView = new WebView();
            WebEngine webEngine = webView.getEngine();
            String embeddedVideoURL = "<html><body style='margin:0; padding:0;'>" +
                    "<div style='position: relative; padding-bottom: 56.25%; height: 0; overflow: hidden; '>" +
                    "<iframe width='320' height='240' src='" + videoURL +
                    "' frameborder='0' allowfullscreen></iframe></body></html>";

            webEngine.loadContent(embeddedVideoURL);
            workoutName.setTextFill(labelColor);
            workoutName.setFont(labelFont);
            vBox.getChildren().addAll(workoutName, category, calories, workoutDescription, webView);
            Scene scene = new Scene(vBox, 400, 500);
            videoStage.setScene(scene);
            videoStage.setTitle("Workout Details");
            videoStage.show();
        });
    }
    private Label styleLabel(String text, Font font, Color color) {
        Label label = new Label(text);
        label.setFont(font);
        label.setTextFill(color);
        label.setWrapText(true);
        return label;
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

        if (workoutToEdit != null) {
            List<Map<String, String>> names = AddWorkout.retrieveWorkout();
            ArrayList<String> workoutCB = new ArrayList<>();

            for (Map<String, String> workout : names) {
                workoutCB.add(workout.get("name"));
            }
            workoutToEdit.setItems(FXCollections.observableArrayList(workoutCB));
        }

        if (menuItems != null) {
            menuItems.setItems(FXCollections.observableArrayList("Home", "Favourites", "Logout"));
        }
        if (adminBoxOptions != null) {
            adminBoxOptions.setItems(FXCollections.observableArrayList("Home", "Favourites", "Logout"));
        }

        if (usersDropBox != null) {
            try {
                ArrayList<String> names = EditAdminAccess.retrieveUsers();
                usersDropBox.setItems(FXCollections.observableArrayList(names));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (adminBoxOptions != null) {
            adminBoxOptions.setItems(FXCollections.observableArrayList("Home", "Logout"));
        }
        if (guestItems != null) {
            guestItems.setItems(FXCollections.observableArrayList("Home", "Logout"));
        }


        if(suggestions != null) {
            suggestions.setVisible(false);
            ObservableList<String> allSuggestions = FXCollections.observableArrayList("Cardio", "Legs", "Arms", "Back", "Chest");

            searchbar.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.isEmpty()) {
                    suggestions.setVisible(false);
                    return;
                }

                ObservableList<String> filteredSuggestions = FXCollections.observableArrayList();
                for (String suggestion : allSuggestions) {
                    if (suggestion.toLowerCase().startsWith(newValue.toLowerCase())) {
                        filteredSuggestions.add(suggestion);
                    }
                }

                suggestions.setItems(filteredSuggestions);
                suggestions.setVisible(!filteredSuggestions.isEmpty());
            });
        }

        if (accessLevels != null) {
            ObservableList<String> allAccessLevels = FXCollections.observableArrayList("True", "False");
            accessLevels.setItems(FXCollections.observableArrayList(allAccessLevels));
        }

        if (workoutVBOX != null) {
            try {
                displayWorkouts();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (arms != null) {
            try {
                displayArmsWorkouts();
            }  catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (back != null) {
            try {
                displayBackWorkouts();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (cardio != null) {
            try {
                displayCardioWorkouts();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (chest != null) {
            try {
                displayChestWorkouts();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (legs != null) {
            try {
                displayLegsWorkouts();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (favouritesBox != null) {
            displayFavourites();
        }

        if (welcomeText != null) {
            welcomeText.setText("Welcome, " + CurrentUser.getInstance().getUserName());
        }


    }


}

