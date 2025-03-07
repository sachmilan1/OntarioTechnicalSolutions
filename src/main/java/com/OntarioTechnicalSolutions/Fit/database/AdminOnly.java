package com.OntarioTechnicalSolutions.Fit.database;

import com.OntarioTechnicalSolutions.Fit.ConnectionProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminOnly {

    public static void main(String[] args){
        AdminOnly.view();
    }

    public static void view(){
        JFrame frame = new JFrame("Admin Only");
        JPanel panel = new JPanel(new GridLayout(0,1,100,15));
        JLabel label = new JLabel("What would you like to do?");
        JButton addWorkout = new JButton(" Add a Workout");
        addWorkout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddAWorkout.add();
                frame.setVisible(false);
            }
        });
        JButton deleteWorkout = new JButton("Delete a Workout");
        deleteWorkout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(frame,"Enter the workout you want to delete");
                Delete.del(name);
                frame.setVisible(false);
            }
        });
        JButton editWorkout = new JButton("Edit a Workout");
        editWorkout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(frame,"Enter the name of workout you want to edit");

                EditWorkout.edit(input);
                frame.setVisible(false);
            }
        });
        JButton seeAll = new JButton("See all Workouts");
        seeAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AllWorkouts.workouts();
            }
        });

        panel.add(label);
        panel.add(addWorkout);
        panel.add(deleteWorkout);
        panel.add(editWorkout);
        panel.add(seeAll);

        frame.add(panel);
        frame.setSize(500,500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

}
