package com.OntarioTechnicalSolutions.Fit.database;

import com.OntarioTechnicalSolutions.Fit.ConnectionProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EditWorkout {
    public static void edit(String name) {
        String desc = "";
        String picture = "";
        String vid = "";
        String cat = "";
        String msc = "";

        JFrame frame = new JFrame("Edit a Workout");
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel descLabel = new JLabel("Description:");
        JTextArea descArea = new JTextArea(3, 20);

        JLabel photoLabel = new JLabel("Photo:");
        JButton photoButton = new JButton("Choose Photo");

        JLabel videoLabel = new JLabel("Video:");
        JButton videoButton = new JButton("Choose Video");

        JLabel catLabel = new JLabel("Which Part it will hit:");
        String[] categories = {"Arms", "Back", "Chest", "Core", "Legs", "Shoulders"};
        JComboBox<String> catBox = new JComboBox<>(categories);

        JLabel muscleLabel = new JLabel("Which muscle it will hit:");
        JComboBox<String> muscleBox = new JComboBox<>();

        // Fetch workout details
        try {
            Connection con = ConnectionProvider.getCon();
            String sql = "SELECT * FROM Workouts WHERE Name = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                desc = rs.getString("Description");
                picture = rs.getString("Photo");
                vid = rs.getString("Video");
                cat = rs.getString("Category");
                msc = rs.getString("Muscle");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }

        // Set fetched values
        nameField.setText(name);
        descArea.setText(desc);
        catBox.setSelectedItem(cat);
        muscleBox.setSelectedItem(msc);

        // Handle category change
        catBox.addActionListener(e -> {
            muscleBox.removeAllItems();
            String selectedCategory = (String) catBox.getSelectedItem();

            String[] muscles;
            switch (selectedCategory) {
                case "Arms":
                    muscles = new String[]{"Biceps", "Triceps", "Forearm"};
                    break;
                case "Back":
                    muscles = new String[]{"Lats", "Traps", "Rhomboids"};
                    break;
                case "Chest":
                    muscles = new String[]{"Upper Chest", "Middle Chest", "Lower Chest"};
                    break;
                case "Core":
                    muscles = new String[]{"Abs", "Obliques", "Lower Back"};
                    break;
                case "Legs":
                    muscles = new String[]{"Quads", "Hamstrings", "Calves"};
                    break;
                case "Shoulders":
                    muscles = new String[]{"Front Delts", "Side Delts", "Rear Delts"};
                    break;
                default:
                    muscles = new String[]{};
            }

            for (String muscle : muscles) {
                muscleBox.addItem(muscle);
            }
        });

        // Set initial muscle selection
        if (msc != null) {
            muscleBox.setSelectedItem(msc);
        }

        // Save button (UPDATE instead of INSERT)
        JButton save = new JButton("Save");
        save.addActionListener(e -> {
            try {
                Connection con = ConnectionProvider.getCon();
                String updateSQL = "UPDATE Workouts SET Description = ?, Category = ?, Muscle = ? WHERE Name = ?";
                PreparedStatement st = con.prepareStatement(updateSQL);

                String newName = nameField.getText();
                String newDesc = descArea.getText();
                String newCat = (String) catBox.getSelectedItem();
                String newMuscle = (String) muscleBox.getSelectedItem();

                if (!newName.isEmpty() && !newDesc.isEmpty() && !newCat.isEmpty() && !newMuscle.isEmpty()) {
                    st.setString(1, newDesc);
                    st.setString(2, newCat);
                    st.setString(3, newMuscle);
                    st.setString(4, newName);
                    st.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Workout updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception f) {
                JOptionPane.showMessageDialog(null, f.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Add components to the panel
        panel.add(nameLabel);
        panel.add(nameField);

        panel.add(descLabel);
        panel.add(new JScrollPane(descArea));

        panel.add(photoLabel);
        panel.add(photoButton);

        panel.add(videoLabel);
        panel.add(videoButton);

        panel.add(catLabel);
        panel.add(catBox);

        panel.add(muscleLabel);
        panel.add(muscleBox);

        panel.add(save);

        // Frame setup
        frame.add(panel);
        frame.setSize(600, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        EditWorkout.edit("abc");
    }
}
