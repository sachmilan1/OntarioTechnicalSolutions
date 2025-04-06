package com.OntarioTechnicalSolutions.demo;

import com.OntarioTechnicalSolutions.Fit.*;
import org.junit.jupiter.api.*;

import java.sql.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IntegrationTest {

    private Connection connection;

    @BeforeAll
    void setupDatabase() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite::memory:");
        ConnectionProvider.setConnection(connection);
        Statement st = connection.createStatement();

        // Create tables
        st.executeUpdate("CREATE TABLE users (User_PK INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, username TEXT, password TEXT, isAdmin BOOLEAN)");
        st.executeUpdate("CREATE TABLE workouts (workout_PK INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, category TEXT, calorie_burn TEXT, Description TEXT, video_url TEXT, image_url BLOB)");
        st.executeUpdate("CREATE TABLE user_favorites (user_id INTEGER, workout_id INTEGER, PRIMARY KEY(user_id, workout_id))");
    }

    @Test
    void testFullIntegration() throws Exception {
        // 1. Insert user
        PreparedStatement userStmt = connection.prepareStatement(
                "INSERT INTO users(name, email, username, password, isAdmin) VALUES (?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        );
        userStmt.setString(1, "Test User");
        userStmt.setString(2, "test@example.com");
        userStmt.setString(3, "testuser");
        userStmt.setString(4, "password123");
        userStmt.setBoolean(5, false);
        userStmt.executeUpdate();

        ResultSet userKeys = connection.createStatement().executeQuery("SELECT last_insert_rowid()");
        userKeys.next();
        int userId = userKeys.getInt(1);

        // 2. Add workout
        byte[] imageBytes = new byte[]{1, 2, 3};  // Dummy image
        AddWorkout.addToDatabase("Burpees", "Cardio", "300", "Explosive full body workout", "https://youtube.com", imageBytes);

        // 3. Get the workout ID
        List<Map<String, String>> workouts = AddWorkout.retrieveWorkout();
        assertFalse(workouts.isEmpty());
        int workoutId = Integer.parseInt(workouts.get(0).get("id"));

        // 4. Add to favourites
        AddToFavourites.addToFavourites(userId, workoutId);

        // 5. Verify favourite exists
        PreparedStatement favCheck = connection.prepareStatement("SELECT * FROM user_favorites WHERE user_id = ? AND workout_id = ?");
        favCheck.setInt(1, userId);
        favCheck.setInt(2, workoutId);
        ResultSet favResult = favCheck.executeQuery();
        assertTrue(favResult.next());
    }

    @AfterAll
    void tearDown() throws SQLException {
        connection.close();
    }
}