package com.OntarioTechnicalSolutions.Fit;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddToFavourites {

    public static void addToFavourites(int userID, int workoutID) throws SQLException {
        try (Connection con = ConnectionProvider.getCon()){
            PreparedStatement st = con.prepareStatement("INSERT INTO user_favorites (user_id, workout_id) VALUES (?, ?)");
            st.setInt(1, userID);
            st.setInt(2, workoutID);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Added To Favourites");
        } catch (SQLException e) {
            if(e.getMessage().equals("[SQLITE_CONSTRAINT_PRIMARYKEY] A PRIMARY KEY constraint failed (UNIQUE constraint failed: user_favorites.user_id, user_favorites.workout_id)")){
                JOptionPane.showMessageDialog(null, "Workout is already in Favourites", "Error",JOptionPane.ERROR_MESSAGE);
            }
            throw e;
        }
    }
}
