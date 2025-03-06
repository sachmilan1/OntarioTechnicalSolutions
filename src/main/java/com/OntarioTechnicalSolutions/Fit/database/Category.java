package com.OntarioTechnicalSolutions.Fit.database;

import com.OntarioTechnicalSolutions.Fit.database.catagory.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Category {
    public static void cat(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        JButton chest = new JButton("chest");
        JButton back = new JButton("back");
        JButton core = new JButton("core");
        JButton legs= new JButton("legs");
        JButton arms = new JButton("arms");
        JButton shoulders= new JButton("shoulders");

        chest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Chest.chest();
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Back.back();
            }
        });

        core.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Core.core();
            }
        });

        legs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Legs.legs();
            }
        });

        arms.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Arms.arms();
                frame.setVisible(false);

            }
        });

        shoulders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Shoulders.shoulder();
            }
        });


        panel.add(chest);
        panel.add(back);
        panel.add(core);
        panel.add(legs);
        panel.add(arms);
        panel.add(shoulders);

        frame.add(panel);

        frame.setVisible(true);
        frame.add(panel);
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
