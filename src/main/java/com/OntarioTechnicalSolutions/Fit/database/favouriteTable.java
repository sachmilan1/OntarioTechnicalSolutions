package com.OntarioTechnicalSolutions.Fit.database;
import java.sql.*;
import com.OntarioTechnicalSolutions.Fit.ConnectionProvider;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class favouriteTable {
    public static void main(String[] args) {


    JFrame frame = new JFrame("Favourites");
    JPanel panel = new JPanel();

    String[] columns = {"Name", "Description", "Photo", "Video", "Category", "Muscle"};
    DefaultTableModel model = new DefaultTableModel(columns, 0);
    JTable table = new JTable(model);

    JScrollPane scrollPane = new JScrollPane(table);

    try{
        Connection con = ConnectionProvider.getCon();
        String sql = "select * from favourites";
        PreparedStatement st = con.prepareStatement(sql);
        ResultSet rs = st.executeQuery();

        while(rs.next()){
            model.addRow(new Object[]{
                    rs.getString("Name"),
                    rs.getString("Description"),
                    rs.getString("Photo"),
                    rs.getString("Video"),
                    rs.getString("Category"),
                    rs.getString("Muscle")
            });
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

        panel.add(scrollPane);
    frame.add(panel);
    frame.setSize(800,800);
    frame.setVisible(true);
    }
}
