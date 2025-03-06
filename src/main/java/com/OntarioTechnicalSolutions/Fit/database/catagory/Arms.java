package com.OntarioTechnicalSolutions.Fit.database.catagory;

import com.OntarioTechnicalSolutions.Fit.ConnectionProvider;
import com.OntarioTechnicalSolutions.Fit.database.WorkoutDetail;
import com.OntarioTechnicalSolutions.Fit.database.viewWorkouts;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Arms {
    public static void arms(){
        JFrame frame = new JFrame("Arms WorkOuts");
        JPanel panel = new JPanel();
        JButton button = new JButton("Go Back");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewWorkouts.backend();
                frame.setVisible(false);

            }
        });
        String[] column = {"Workout"};

        DefaultTableModel model = new DefaultTableModel(column,0);
        JTable table = new JTable(model);//no data yet

        try {
            DefaultTableModel mod = (DefaultTableModel) table.getModel();
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();

            // ILIKE make it case-insensitive
            ResultSet rs =  st.executeQuery("select * from Workouts where Category ilike 'arms'");

            while(rs.next()){
                mod.addRow(new Object[]{
                        rs.getString("Name"),
                });
            }
        }
        catch (Exception e){
            System.err.println("ERROR"+e.getMessage());
        }

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if(row>=0){
                    String workoutName = (String)table.getValueAt(row,0);
                    WorkoutDetail.detail(workoutName);
                    frame.setVisible(false);
                }
            }
        });

        JScrollPane sp = new JScrollPane(table);
        panel.add(sp);
        panel.add(button);

        frame.add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(750,750);
        frame.setVisible(true);
    }

}
