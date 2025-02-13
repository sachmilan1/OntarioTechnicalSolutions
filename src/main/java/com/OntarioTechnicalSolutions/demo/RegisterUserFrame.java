package com.OntarioTechnicalSolutions.demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterUserFrame {
    public static void main(String []args){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,2));
        JFrame frame = new JFrame();

        JLabel name, email, password, username;
        name = new JLabel("Name: ");
        email = new JLabel("Email: ");
        password = new JLabel("Password: ");
        username = new JLabel("User-Name: ");

        JPasswordField passwordField = new JPasswordField();
        JTextField nameTxt = new JTextField();
        JTextField emailTxt = new JTextField();
        JTextField userNameTxt = new JTextField();
        JButton register = new JButton("Register");

        panel.add(name);
        panel.add(nameTxt);
        panel.add(email);
        panel.add(emailTxt);
        panel.add(username);
        panel.add(userNameTxt);
        panel.add(password);
        panel.add(passwordField);
        panel.add(register);

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String getName = nameTxt.getText();
                String getEmail = emailTxt.getText();
                String getUserName = userNameTxt.getText();
                char[] passwordChar = passwordField.getPassword();
                String getPassword = new String(passwordChar);


                if (RepetitionChecker.emailChecker(getEmail)) {
                    JOptionPane.showMessageDialog(panel,"A user with this email already exists", "Error", JOptionPane.WARNING_MESSAGE);
                } else if(RepetitionChecker.nameChecker(getUserName)) {
                    JOptionPane.showMessageDialog(panel,"User Name already exists", "Error", JOptionPane.WARNING_MESSAGE);
                }else{
                    try {
                        Connection con = ConnectionProvider.getCon();
                        PreparedStatement st = con.prepareStatement("insert into users(name,email,username,password)values(?,?,?,?)");
                        st.setString(1, getName);
                        st.setString(2, getEmail);
                        st.setString(3, getUserName);
                        st.setString(4, getPassword);
                        st.executeUpdate();

                        JOptionPane.showMessageDialog(null, "User data successfully added into the database");
                    } catch (SQLException f) {
                        JOptionPane.showMessageDialog(null, "Error in uploading the data into user table" + f.getMessage());
                    }
                }
            }
        });




        frame.add(panel);
        frame.setVisible(true);
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
