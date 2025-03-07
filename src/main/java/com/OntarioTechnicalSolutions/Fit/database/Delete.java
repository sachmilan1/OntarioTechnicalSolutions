package com.OntarioTechnicalSolutions.Fit.database;

import com.OntarioTechnicalSolutions.Fit.ConnectionProvider;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Delete {
    public static void del(String name){
        try{
            int confirm = JOptionPane.showConfirmDialog(null,"Do you want to delete "+name,"Confirm",JOptionPane.YES_NO_OPTION);
            if (confirm==0) {
                Connection con = ConnectionProvider.getCon();
                String sql = "Delete from Workouts where name =?";
                PreparedStatement st = con.prepareStatement(sql);
                if (name != null) {
                    st.setString(1, name.trim());

                    int rowsEffected = st.executeUpdate();
                    if (rowsEffected > 0) {
                        JOptionPane.showMessageDialog(null, "Workout deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null,"Error in deleting"+name,"Error",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }else{
                AdminOnly.view();
            }

        }catch(Exception f){
            JOptionPane.showMessageDialog(null,f.getMessage(),"Error",JOptionPane.WARNING_MESSAGE);
        }
    }
}
