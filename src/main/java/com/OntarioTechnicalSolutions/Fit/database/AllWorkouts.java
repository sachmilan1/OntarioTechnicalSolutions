package com.OntarioTechnicalSolutions.Fit.database;

import com.OntarioTechnicalSolutions.Fit.ConnectionProvider;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AllWorkouts {
    public static void workouts() {
        JFrame frame = new JFrame("All Workouts...");
        JPanel panel = new JPanel();

        String[] columns = {"Name","Add to favourites"};
        DefaultTableModel model = new DefaultTableModel(columns, 0){

            @Override
            public boolean isCellEditable(int row, int column){
                return column ==1;
            }

            @Override
            //<?> means we are expecting any value
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 1) {
                    return JToggleButton.class;
                }
                return String.class;
                //If the column index is 1, then it will return a JToggle button class,
                // else it will return the usual output of JTable column,which is String
            }
        };

        JTable table = new JTable(model);

        try {
            DefaultTableModel mod = (DefaultTableModel) table.getModel();
            Connection con = ConnectionProvider.getCon();

            // Using prepared statement to prevent SQL injection.
            String sql = "SELECT Name FROM Workouts"; // Only select required columns
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            table.getColumnModel().getColumn(1).setCellRenderer(new ButtonRenderer());
            table.getColumnModel().getColumn(1).setCellEditor(new ButtonEditor());

            while (rs.next()) {
                JButton Button = new JButton("Add to favourites");
                Button.setSelected(false); // Ensure initial state

                // Print what is being added to the table
                System.out.println("Adding workout: " + rs.getString("Name") + " with button: " + Button);

                mod.addRow(new Object[]{ rs.getString("Name"), Button });


            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }


        //Make table rows clickable
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                Point point = e.getPoint();
                int row = table.rowAtPoint(point);
                int column = table.columnAtPoint(point);

                if (column == 1) { // Clicked the toggle button column
                    table.editCellAt(row, column); // Force editing
                    Component editor = table.getEditorComponent();
                    if (editor instanceof JToggleButton) {
                        JToggleButton toggleButton = (JToggleButton) editor;
                        toggleButton.doClick(); // Simulate button click
                    }
                } else if (row >= 0) { // Clicked anywhere else (row selection)
                    String name = table.getValueAt(row, 0).toString(); // Get workout name

                    String[] options = {"Edit", "Delete", "See in detail", "Cancel"};
                    int option = JOptionPane.showOptionDialog(table, "Choose an option:",
                            "Admin Actions",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            options,
                            options[3]);

                    switch (option) {
                        case 0:
                            EditWorkout.edit(name); // Call EditWorkout with selected name
                            break;
                        case 1:
                            deleteWorkout(name); // Delete function
                            break;
                        case 2:
                            JOptionPane.showMessageDialog(null, "See in detail for: " + name);
                            break;
                        case 3:
                            JOptionPane.showMessageDialog(null, "Cancel");
                            break;
                    }
                }
            }
        });


        JScrollPane sp = new JScrollPane(table);
        panel.add(sp);
        frame.add(panel);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    // New function to delete a workout
    private static void deleteWorkout(String workoutName) {
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + workoutName + "?",
                "Confirm Deletion", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Connection con = ConnectionProvider.getCon();
                String sql = "DELETE FROM Workouts WHERE Name = ?";
                PreparedStatement st = con.prepareStatement(sql);
                st.setString(1, workoutName);
                int rowsAffected = st.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Workout '" + workoutName + "' deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error: Workout not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
