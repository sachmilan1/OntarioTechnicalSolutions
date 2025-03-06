package com.OntarioTechnicalSolutions.Fit.database;

import com.OntarioTechnicalSolutions.Fit.ConnectionProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class AddWorkOut {
    public static void add() {
        JFrame frame = new JFrame("Add a Workout");
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10)); // Auto-rows, 2 columns, 10px padding

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(); // No default text

        JLabel descLabel = new JLabel("Description:");
        JTextArea descArea = new JTextArea(3, 20); // 3 rows, 20 columns

        JLabel photoLabel = new JLabel("Photo:");
        JButton photoButton = new JButton("Choose Photo");

        JLabel videoLabel = new JLabel("Video:");
        JButton videoButton = new JButton("Choose Video");

        JLabel catLabel = new JLabel("Which Part it will hit:");
        String[] categories = {"Arms", "Back", "Chest", "Core", "Legs", "Shoulders"};
        JComboBox<String> catBox = new JComboBox<>(categories);

        JLabel muscleLabel = new JLabel("Which muscle it will hit:");
        JComboBox<String> muscleBox = new JComboBox<>();

        // Action listener to update muscleBox dynamically
        catBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                muscleBox.removeAllItems(); // Clear previous items
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
            }
        });

        // Photo button action
        photoButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                System.out.println("Selected Photo: " + fileChooser.getSelectedFile().getPath());
            }
        });

        // Video button action
//        videoButton.addActionListener(e -> {
//            JFileChooser fileChooser = new JFileChooser();
//            int returnValue = fileChooser.showOpenDialog(null);
//            if (returnValue == JFileChooser.APPROVE_OPTION) {
//                System.out.println("Selected Video: " + fileChooser.getSelectedFile().getPath());
//            }
//        });

        JButton save = new JButton("Save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Connection con = ConnectionProvider.getCon();
                    PreparedStatement st = con.prepareStatement("insert into Workouts(Name,Description,Category,Muscle)" +
                            "VALUES(?,?,?,?)");
                    String name= nameField.getText();
                    String desc = descArea.getText();
                    String cat = (String)catBox.getSelectedItem();
                    String muscle = (String)muscleBox.getSelectedItem();

                    if(!name.isEmpty()&&!desc.isEmpty()&&!cat.isEmpty()&&!muscle.isEmpty()){
                        st.setString(1,name);
                        st.setString(2,desc);
                        st.setString(3,cat);
                        st.setString(4,muscle);
                        st.executeUpdate();
                        JOptionPane.showMessageDialog(null,"Workout added to the database","Success",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                catch (Exception f){
                    JOptionPane.showMessageDialog(null,f.getMessage(),"ERROR",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        // Add components to the panel
        panel.add(nameLabel);
        panel.add(nameField);

        panel.add(descLabel);
        panel.add(new JScrollPane(descArea)); // Wrap text area in scrollpane

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
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // Close only this window


    }

    public static void main(String[] args) {
        AddWorkOut.add();
    }
}
