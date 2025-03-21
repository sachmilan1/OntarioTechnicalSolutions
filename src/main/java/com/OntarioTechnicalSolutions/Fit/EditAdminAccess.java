package com.OntarioTechnicalSolutions.Fit;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EditAdminAccess {
    public static void editAdminAccess(String username, boolean accessLevel) throws SQLException {
        Connection con = ConnectionProvider.getCon();
        PreparedStatement ps = con.prepareStatement("UPDATE users SET isAdmin = ?");

        ps.setBoolean(1, accessLevel);
        ps.executeUpdate();
        con.close();
        ps.close();

        JOptionPane.showMessageDialog(null, "Admin access updated");
    }

    public static ArrayList<String> retrieveUsers() throws SQLException {
        ArrayList<String> names = new ArrayList<>();
        Connection con = ConnectionProvider.getCon();
        PreparedStatement ps = con.prepareStatement("SELECT name FROM users");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            names.add(rs.getString("name"));
        }
        return names;
    }
}
