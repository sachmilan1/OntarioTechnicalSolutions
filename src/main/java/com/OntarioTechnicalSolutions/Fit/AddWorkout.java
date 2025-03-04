package com.OntarioTechnicalSolutions.Fit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddWorkout {

    public static void addToDatabase(String workout,
                                     String category,
                                     String Calorie_burn,
                                     String description,
                                     String videoURL,
                                     String imageURL) {

        try {
            Connection con = ConnectionProvider.getConWorkoutDB();
            PreparedStatement st = con.prepareStatement("insert into workouts(name, category, calorie_burn, Description, video_url, image_url) values (?, ?, ?, ?, ?, ?)");
            st.setString(1, workout);
            st.setString(2, category);
            st.setString(3, Calorie_burn);
            st.setString(4, description);
            st.setString(5, videoURL);
            st.setString(6, imageURL);
            st.executeUpdate();
            System.out.println("Workout added to Database");
            st.close();
            con.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
