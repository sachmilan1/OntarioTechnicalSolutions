package com.OntarioTechnicalSolutions.Fit;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
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

    @FXML Label clearButton;

    @FXML
    Label welcomeText;

    ObservableList<String> allSuggestions = FXCollections.observableArrayList("Cardio", "Legs", "Arms", "Back", "Chest");

    private Stage stage;
    private Scene scene;


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

    public void initWorkouts(ActionEvent event) throws IOException {
        AllWorkouts(event);
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
                        CurrentUser.getInstance().setAdmin(true);

                        JOptionPane.showMessageDialog(null, "Logged in as " + username, "Success", JOptionPane.INFORMATION_MESSAGE);
                        homeScreenAdmin(event);
                    }
                    else if(password.equals(pass)) {
                        CurrentUser.getInstance().setUserID(rs.getInt("User_PK"));
                        CurrentUser.getInstance().setUserName(rs.getString("username"));
                        CurrentUser.getInstance().setGuest(false);
                        CurrentUser.getInstance().setAdmin(false);

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

                        ImageView workoutImage = new ImageView();
                        byte[] imageData = ts.getBytes("image_url");

                        Label description = new Label(ts.getString("Description"));
                        description.setWrapText(true);
                        description.setPrefWidth(200);
                        description.setMaxWidth(200);
                        description.setTextFill(Color.WHITE);
                        HBox.setHgrow(description, Priority.ALWAYS);

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

                        HBox workoutBox = new HBox();
                        workoutBox.setSpacing(10);
                        workoutBox.setStyle("-fx-padding: 10px; -fx-border-width: 1px; -fx-border-color: black;");

                        Button removeButton = new Button("Remove from Favourites");
                        removeButton.setOnAction(event -> handleRemoveFavourites(event, userID, workoutID));
                        ScrollPane scrollPane = new ScrollPane();
                        scrollPane.setFitToWidth(true);
                        scrollPane.setContent(workoutBox);
                        scrollPane.setStyle("-fx-background: #222; -fx-border-color: transparent");

                        favouritesBox.setPadding(new Insets(20));
                        favouritesBox.getChildren().add(scrollPane);

                        workoutName.setTextFill(Color.WHITE);
                        removeButton.setStyle("-fx-padding: 5px; -fx-margin-left: 10px; -fx-background-color: #f25042;\n" +
                                "    -fx-border-radius: 1000px;\n" +
                                "    -fx-transition: transform 0.3s, box-shadow 0.3s;");

                        description.setWrapText(true);
                        workoutBox.getChildren().addAll(workoutImage, workoutName, removeButton, description);
                        favouritesBox.getChildren().add(workoutBox);

                        final Map<String, String> workoutDetails = new HashMap<>();
                        workoutDetails.put("id", String.valueOf(workoutID));
                        workoutDetails.put("name", ts.getString("name"));
                        workoutDetails.put("category", ts.getString("category"));
                        workoutDetails.put("calorie_burn", ts.getString("calorie_burn"));
                        workoutDetails.put("description", ts.getString("description"));
                        workoutDetails.put("video_url", ts.getString("video_url"));
                        workoutName.setOnMouseClicked(event -> {
                            showWorkoutDetails(workoutDetails);
                        });
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


    private void displayWorkouts(String searchItem) throws IOException {
        workoutVBOX.getChildren().clear();
        try {
            List<Map<String, String>> workoutsList = AddWorkout.searchWorkout(searchItem);
            if (!workoutsList.isEmpty()) {
                for (Map<String, String> workout : workoutsList) {

                        HBox workoutBox = new HBox(10);
                        workoutBox.setSpacing(10);
                        workoutBox.setStyle("-fx-padding: 10px; -fx-border-width: 1px; -fx-border-color: black;");

                        Label workoutLabel = new Label(workout.get("name"));
                        workoutLabel.setStyle("-fx-padding: 10px; -fx-border-width: 1px; -fx-border-color: black;");

                        Label workoutDescription = new Label(workout.get("description"));
                        workoutDescription.setWrapText(true);
                        workoutDescription.setPrefWidth(200);
                        workoutDescription.setMaxWidth(200);
                        workoutDescription.setTextFill(Color.WHITE);
                    HBox.setHgrow(workoutDescription, Priority.ALWAYS);

                        ImageView workoutImage = new ImageView();
                        byte[] imageData = AddWorkout.retrieveImage().get(workout.get("id"));

                        if (imageData.length > 0) {
                            Image image = new Image(new ByteArrayInputStream(imageData));
                            workoutImage.setImage(image);
                        } else {
                            workoutImage.setImage(new Image(String.valueOf(getClass().getClassLoader().getResource("images/no_image.jpg"))));
                        }


                        if ((CurrentUser.getInstance().getUserID() != -1 && !CurrentUser.getInstance().getUserName().isEmpty()) && !CurrentUser.getInstance().getAdmin()) {
                            Button addToFavouritesButton = new Button("Add to Favourites");
                            addToFavouritesButton.setOnAction(event -> {
                                try {
                                    System.out.println(workout.get("id"));
                                    AddToFavourites.addToFavourites(CurrentUser.getInstance().getUserID(), Integer.parseInt(workout.get("id")));
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                            addToFavouritesButton.setStyle("-fx-padding: 5px; -fx-margin-left: 10px; -fx-background-color: #f25042;\n" +
                                    "    -fx-border-radius: 1000px;\n" +
                                    "    -fx-transition: transform 0.3s, box-shadow 0.3s;");
                            workoutLabel.setTextFill(Color.WHITE);
                            workoutDescription.setTextFill(Color.WHITE);
                            workoutBox.getChildren().addAll(workoutImage, workoutLabel, addToFavouritesButton, workoutDescription);
                        } else {
                            workoutLabel.setTextFill(Color.WHITE);
                            workoutLabel.setTextFill(Color.WHITE);
                            workoutDescription.setTextFill(Color.WHITE);
                            workoutBox.getChildren().addAll(workoutImage, workoutLabel, workoutDescription);
                        }
                        workoutLabel.setOnMouseClicked(event -> {
                            showWorkoutDetails(workout);
                        });

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
                        workoutLabel.setOnMouseClicked(event -> {
                            showWorkoutDetails(workout);
                        });

                        ScrollPane scrollPane = new ScrollPane();
                        scrollPane.setFitToWidth(true);
                        scrollPane.setContent(workoutBox);
                        workoutBox.setStyle("-fx-background-color: #16161a");
                        scrollPane.setStyle("-fx-background-color: #16161a; -fx-border-color: transparent;");


                        workoutVBOX.setPadding(new Insets(20));
                        workoutVBOX.getChildren().add(scrollPane);

                }
            } else {
                Label noWorkouts = new Label("No Workouts Found");
                workoutVBOX.getChildren().add(noWorkouts);
                noWorkouts.setStyle("-fx-background-color: white;");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
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
            Label calories = styleLabel("Calories: " + workout.get("calorie_burn"), labelFont, labelColor);
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
            vBox.getChildren().addAll(workoutName, category, calories, webView);
            Scene scene = new Scene(vBox, 400, 500);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("FXML/webview.css")).toExternalForm());
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
        if (workoutVBOX != null) {
            try {
                displayWorkouts("");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

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


        if (accessLevels != null) {
            ObservableList<String> allAccessLevels = FXCollections.observableArrayList("True", "False");
            accessLevels.setItems(FXCollections.observableArrayList(allAccessLevels));
        }

        if (favouritesBox != null) {
            displayFavourites();
        }

        if (welcomeText != null) {
            welcomeText.setText("Welcome, " + CurrentUser.getInstance().getUserName());
        }

        if(suggestions != null) {
            suggestions.setVisible(false);
            searchbar.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.isEmpty()) {
                    suggestions.setVisible(false);
                    return;
                }
                ObservableList<String> filtered = FXCollections.observableArrayList();
                for (String suggestion : allSuggestions) {
                    if (suggestion.toLowerCase().startsWith(newValue.toLowerCase())) {
                        filtered.add(suggestion);
                    }
                }
                suggestions.setItems(filtered);
                suggestions.setVisible(!filtered.isEmpty());
            });

            clearButton.setOnMouseClicked(event -> {
                try {
                    displayWorkouts("");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            suggestions.setOnMouseClicked(event -> {
                String selected = suggestions.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    searchbar.setText(selected);
                    suggestions.setVisible(false);
                    try {
                        displayWorkouts(selected);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }

        if (workoutVBOX != null) {
            workoutVBOX.getStylesheets().add(getClass().getClassLoader().getResource("FXML/styleDesc.css").toExternalForm());
        }

    }


}
