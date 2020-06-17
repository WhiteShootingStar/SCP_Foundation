package com.MASFinalProject.FinalProject.gui.view;

import javax.swing.*;

public class LoginWindowView {
    private JPanel loginWindow;
    private JButton loginButton;
    private JTextField loginText;
    private JPasswordField passwordText;
    private JLabel errorLabel;


    public JPanel getLoginWindow() {
        return loginWindow;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JTextField getLoginText() {
        return loginText;
    }

    public JPasswordField getPasswordText() {
        return passwordText;
    }

    public JLabel getErrorLabel() {
        return errorLabel;
    }
}
