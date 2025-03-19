package com.OntarioTechnicalSolutions.Fit.database;

import com.OntarioTechnicalSolutions.Fit.ConnectionProvider;
import com.OntarioTechnicalSolutions.Fit.Tables;
import com.mysql.cj.xdevapi.Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


//CONTAINS THE FAVOURITE TERMINOLOGY


public class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private boolean clicked = false;
    private JTable table;
    private int clickedRow = -1;

    public ButtonEditor() {
        super(new JCheckBox()); // Required but not used
        button = new JButton("Add to favourites");

        // Handle button click
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(clickedRow>=0) {

                    String workoutName = (String)table.getValueAt(clickedRow,0);

                    //MAKING THE FAVOURITE TABLE

                    try {
                        //System.out.println("in try block");
                        String sql = "Select * from Workouts where name =?";

                        //MAKING TABLE
                        String tableName = "favourites";
                        Tables.createFavTable(tableName);

                        Connection con = ConnectionProvider.getCon();
                        PreparedStatement st = con.prepareStatement(sql);
                        //System.out.println("After prepared statement");
                        st.setString(1,workoutName);
                        ResultSet rs = st.executeQuery();
                        rs.next();
                        System.out.println(rs.getString(2));

                        //INSERTING INTO TABLE
                        String sql1 = "insert into favourites(Name,Description,Photo,Video,Category,Muscle)" +
                                "values(?,?,?,?,?,?)";
                        PreparedStatement st1 = con.prepareStatement(sql1);
                        st1.setString(1,rs.getString(2));
                        st1.setString(2,rs.getString(3));
                        st1.setString(3,rs.getString(4));
                        st1.setString(4,rs.getString(5));
                        st1.setString(5,rs.getString(6));
                        st1.setString(6,rs.getString(7));
                        st1.executeUpdate();
                        System.out.println("values inserted into favourites table");


                        try{

                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }

                        //System.out.println("The category is "+rs.getString(1));
                        clicked = !clicked; // Toggle state manually
                        button.setText(clicked ? "Added to favourites" : "Add to favourites");
                        fireEditingStopped(); // Notify JTable that editing is complete

                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (value instanceof JButton) {
            JButton existingButton = (JButton) value;
            button.setText(existingButton.getText());
            this.table=table;
            this.clickedRow=row;
        }
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return button;
    }
}
