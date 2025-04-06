package com.OntarioTechnicalSolutions.Fit;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AddWorkout {

    public static void addToDatabase(String workout,
                                     String category,
                                     String Calorie_burn,
                                     String description,
                                     String videoURL,
                                     byte[] imageURL) {

        try {
            Connection con = ConnectionProvider.getCon();
            PreparedStatement st = con.prepareStatement("insert into workouts(name, category, calorie_burn, Description, video_url, image_url) values (?, ?, ?, ?, ?, ?)");
            st.setString(1, workout);
            st.setString(2, category);
            st.setString(3, Calorie_burn);
            st.setString(4, description);
            st.setString(5, videoURL);
            st.setBytes(6, imageURL);
            st.executeUpdate();
            System.out.println("Workout added to Database");
            st.close();
            // DO NOT CLOSE THE CONNECTION HERE
            // con.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void editWorkouts(String workoutEdit, String newName, String newCategory, String newCalorieBurn, String newDescription, String newVideoURL, String newImageURL) throws SQLException {
        Connection con = ConnectionProvider.getCon();
        PreparedStatement st = con.prepareStatement("UPDATE workouts SET name = ?, category = ?, calorie_burn = ?, Description = ?, video_url = ?, image_url = ? WHERE name = ?");
        st.setString(1, newName);
        st.setString(2, newCategory);
        st.setString(3, newCalorieBurn);
        st.setString(4, newDescription);
        st.setString(5, newVideoURL);
        st.setString(6, newImageURL);

        // Workout to Edit
        st.setString(7, workoutEdit);
        st.executeUpdate();
        st.close();
        // DO NOT CLOSE THE CONNECTION HERE
        // con.close();
        JOptionPane.showMessageDialog(null, "Workout Edited Successfully");
    }

    public static void removeWorkout(String workout) throws SQLException {
        Connection con = ConnectionProvider.getCon();
        PreparedStatement st = con.prepareStatement("DELETE FROM workouts WHERE name = ?");

        st.setString(1, workout);
        st.executeUpdate();
        st.close();
        // DO NOT CLOSE THE CONNECTION HERE
        // con.close();

        JOptionPane.showMessageDialog(null, "Workout Removed Successfully");
    }


    public static List<Map<String, String>> retrieveWorkout() {
        List<Map<String, String>> workouts = new ArrayList<Map<String, String>>();

        try {
            Connection con = ConnectionProvider.getCon();
            PreparedStatement st = con.prepareStatement("SELECT workout_PK, name, category, calorie_burn, Description, video_url FROM workouts");
            ResultSet rs = st.executeQuery();

            System.out.println("Retrieving workouts");
            while (rs.next()) {
                Map<String, String> workout = new HashMap<String, String>();
                workout.put("id", String.valueOf(rs.getInt("workout_PK")));
                workout.put("name", rs.getString("name"));
                workout.put("category", rs.getString("category"));
                workout.put("calorie_burn", rs.getString("calorie_burn"));
                workout.put("description", rs.getString("Description"));
                workout.put("video_url", rs.getString("video_url"));
                workouts.add(workout);
            }
            st.close();
            // DO NOT CLOSE THE CONNECTION HERE
            // con.close();
            return workouts;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, byte[]> retrieveImage() {
        Map<String, byte[]>  image = new HashMap<>();

        try {
            Connection con = ConnectionProvider.getCon();
            PreparedStatement st = con.prepareStatement("SELECT workout_PK, image_url FROM workouts");
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int workoutID = rs.getInt("workout_PK");
                byte[] imageData = rs.getBytes("image_url");
                image.put(String.valueOf(workoutID), imageData);
            }
            st.close();
            // DO NOT CLOSE THE CONNECTION HERE
            // con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return image;
    }

    public static List<Map<String, String>> searchWorkout(String search) throws SQLException {
        List<Map<String, String>> workouts = new ArrayList<>();
        Connection con = ConnectionProvider.getCon();
        PreparedStatement st = con.prepareStatement("SELECT workout_PK, name, category, calorie_burn, Description, video_url FROM workouts WHERE LOWER(category) LIKE ?");
        st.setString(1, "%" + search.toLowerCase() + "%");
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Map<String, String> workout = new HashMap<>();
            workout.put("id", String.valueOf(rs.getInt("workout_PK")));
            workout.put("name", rs.getString("name"));
            workout.put("category", rs.getString("category"));
            workout.put("calorie_burn", rs.getString("calorie_burn"));
            workout.put("description", rs.getString("Description"));
            workout.put("video_url", rs.getString("video_url"));
            workouts.add(workout);
        }
        st.close();
        // DO NOT CLOSE THE CONNECTION HERE
        // con.close();
        return workouts;

    }
}