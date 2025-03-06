package com.OntarioTechnicalSolutions.Fit.database;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class viewWorkouts {

    public static void backend() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JButton category = new JButton("Category");
        JButton specificMuscle = new JButton("specific Muscle");

        category.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Category.cat();
                frame.setVisible(false);
            }
        });
        panel.add(category);
        panel.add(specificMuscle);

        frame.add(panel);

        frame.setSize(500,500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public static void main(String[] args){
        viewWorkouts.backend();
    }
}
