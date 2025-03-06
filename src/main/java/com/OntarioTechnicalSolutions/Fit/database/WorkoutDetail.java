package com.OntarioTechnicalSolutions.Fit.database;

import com.OntarioTechnicalSolutions.Fit.ConnectionProvider;

import javax.swing.*;
import java.sql.*;

public class WorkoutDetail {
    public static void detail(String name){
        JFrame frame = new JFrame("name");
        JPanel panel = new JPanel();
        JTextArea text = new JTextArea();
        text.setEditable(false);

        try{
            Connection con = ConnectionProvider.getCon();
            String sql = "Select * from Workouts where name =?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,name);
            ResultSet rs = st.executeQuery();

            if(rs.next()){
                String desc = rs.getString("Description");
                String cat = rs.getString("Category");
                String muscle = rs.getString("Muscle");
                String video = rs.getString("Video");
                String photo = rs.getString("Photo");

                text.setText(
                        "Workout Name: " + name + "\n" +
                                "Category: " + cat + "\n" +
                                "Target Muscle: " + muscle + "\n" +
                                "Description: " + desc + "\n" +
                                "Video: " + video + "\n" +
                                "Photo: " + photo
                );

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    panel.add(text);
        frame.add(panel);
        frame.setSize(1000,1000);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
