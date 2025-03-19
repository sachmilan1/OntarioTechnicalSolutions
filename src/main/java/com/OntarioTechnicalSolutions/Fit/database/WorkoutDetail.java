package com.OntarioTechnicalSolutions.Fit.database;

import com.OntarioTechnicalSolutions.Fit.ConnectionProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.*;

public class WorkoutDetail {
    public static void detail(String name) {
        JFrame frame = new JFrame(name);
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);

        try {
            Connection con = ConnectionProvider.getCon();
            String sql = "Select * from Workouts where name =?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                String desc = rs.getString("Description");
                String cat = rs.getString("Category");
                String muscle = rs.getString("Muscle");
                String video = rs.getString("Video");
                String photo = rs.getString("Photo");

                System.out.println("Photo URL: " + photo);

                // ===== IMAGE SECTION =====
                try {
                    URL imageUrl = new URL(photo);
                    ImageIcon imageIcon = new ImageIcon(imageUrl);
                    // Scaling the image (important!)
                    Image scaledImage = imageIcon.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
                    JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
                    imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    contentPanel.add(imageLabel);
                } catch (Exception e) {
                    JLabel errorLabel = new JLabel("Could not load image.");
                    contentPanel.add(errorLabel);
                }

                contentPanel.add(Box.createRigidArea(new Dimension(0, 10))); // spacing

                // ===== TEXT SECTION =====
                JTextArea text = new JTextArea(
                        "Workout Name: " + name + "\n" +
                                "Category: " + cat + "\n" +
                                "Target Muscle: " + muscle + "\n" +
                                "Description: " + desc + "\n"
                );
                text.setEditable(false);
                text.setLineWrap(true);
                text.setWrapStyleWord(true);
                text.setFont(new Font("Arial", Font.PLAIN, 14));
                text.setBackground(Color.LIGHT_GRAY);
                text.setMaximumSize(new Dimension(700, 150));
                contentPanel.add(text);

                contentPanel.add(Box.createRigidArea(new Dimension(0, 10))); // spacing

                // ===== VIDEO BUTTON =====
                JButton videoButton = new JButton("Watch Video");
                videoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                videoButton.addActionListener((ActionEvent e) -> {
                    try {
                        Desktop.getDesktop().browse(new URL(video).toURI());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Failed to open video link.");
                    }
                });
                contentPanel.add(videoButton);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // ===== ADD TO FRAME =====
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        frame.add(scrollPane);

        frame.setSize(800, 700);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
