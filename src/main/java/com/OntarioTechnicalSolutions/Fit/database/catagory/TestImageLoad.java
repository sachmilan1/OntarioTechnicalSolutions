package com.OntarioTechnicalSolutions.Fit.database.catagory;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class TestImageLoad {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Image Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        try {
            // Known public URL
            String photo = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/77/Delete_key1.jpg/320px-Delete_key1.jpg";
            System.out.println("Photo URL: " + photo);

            // Use ImageIO
            URL imageUrl = new URL(photo);
            BufferedImage img = ImageIO.read(imageUrl);

            if (img != null) {
                Image scaledImg = img.getScaledInstance(400, 300, Image.SCALE_SMOOTH);
                JLabel imageLabel = new JLabel(new ImageIcon(scaledImg));
                imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                panel.add(imageLabel);
            } else {
                JLabel errorLabel = new JLabel("Failed to load image (ImageIO null).");
                panel.add(errorLabel);
            }

        } catch (IOException e) {
            e.printStackTrace();
            JLabel errorLabel = new JLabel("Could not load image: " + e.getMessage());
            panel.add(errorLabel);
        }

        frame.add(panel);
        frame.setVisible(true);
    }
}
