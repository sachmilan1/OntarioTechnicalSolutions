package com.OntarioTechnicalSolutions.Fit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepetitionChecker {
    public static boolean nameChecker(String userName){
        boolean userExists = false;
        String query = "SELECT Count(*) FROM users WHERE username =?";
        try{
            Connection con = ConnectionProvider.getCon();
            PreparedStatement st = con.prepareStatement(query);

            st.setString(1,userName);
            ResultSet rs = st.executeQuery();
            if(rs.next()&&rs.getInt(1)>0){
                userExists = true;
                return userExists;
            }
        }
        catch (SQLException e){
            System.err.println("The error is"+e.getMessage());
        }
        return userExists;
    }
    public static boolean emailChecker(String email){
        boolean userExists = false;
        try{
            Connection con = ConnectionProvider.getCon();
            PreparedStatement st = con.prepareStatement("SELECT Count(*) FROM users WHERE email =?");

            st.setString(1,email);
            ResultSet rs= st.executeQuery();

            if(rs.next()&&rs.getInt(1)>0){
                userExists= true;
                return userExists;
            }
        }
        catch(SQLException e){
            System.err.println("Error"+ e.getMessage());
        }
        return userExists;
    }
}
