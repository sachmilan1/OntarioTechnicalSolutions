package com.OntarioTechnicalSolutions.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginBackEnd {
    private static final Logger log = LoggerFactory.getLogger(LoginBackEnd.class);

    public static void main(String []args){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,2));
        JFrame frame = new JFrame();

        JLabel username,password;

        username = new JLabel("User Name: ");
        password = new JLabel("Password: ");

        JTextField user = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JButton login = new JButton("Login");
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String getName = user.getText();
                char[] passwordChar = passwordField.getPassword();
                String getPassword = new String(passwordChar);

                if(getName!=null&&!getPassword.isEmpty()){
                    try{
                        Connection con = ConnectionProvider.getCon();
                        PreparedStatement st = con.prepareStatement("Select * FROM Users where username=?");
                        st.setString(1,getName);
                        ResultSet rs = st.executeQuery();

                        if(rs.next()){
                            String pass = rs.getString("password");
                            if(pass.equals(getPassword)){
                                JOptionPane.showMessageDialog(panel, "Welcome", "Information", JOptionPane.INFORMATION_MESSAGE);
                            }else{
                                JOptionPane.showMessageDialog(panel,"Try entering correct username or password","Warning",JOptionPane.WARNING_MESSAGE);
                        }
                        }
                    }
                    catch (SQLException f){
                        System.err.println("Error"+f.getMessage());
                    }
                }
            }
        });

        panel.add(username);
        panel.add(user);
        panel.add(password);
        panel.add(passwordField);
        panel.add(login);

        frame.add(panel);
        frame.setSize(500,500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
