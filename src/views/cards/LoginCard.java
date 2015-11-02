package views.cards;

import controllers.LoginController;
import models.LoginModel;

import javax.swing.*;
import java.awt.*;

public class LoginCard {

    LoginModel loginModel;

    public LoginCard(LoginModel loginModel) {
        this.loginModel = loginModel;
    }

    public JPanel createLayout() {
        JPanel loginPanel = loginModel.getLoginPanel();

        GridBagConstraints constraints = new GridBagConstraints();

        //Anchor to left side
        constraints.anchor = GridBagConstraints.WEST;

        //Pad the cells
        constraints.insets = new Insets(10, 10, 10, 10);

        loginModel.getErrorLabel().setForeground(Color.red);

        //Create + Add components to panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        loginPanel.add(loginModel.getErrorLabel(), constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;
        loginPanel.add(loginModel.getLoginNotice(), constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        loginPanel.add(loginModel.getUsernameLabel(), constraints);

        constraints.gridx = 1;
        loginPanel.add(loginModel.getUsernameField(), constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        loginPanel.add(loginModel.getPasswordLabel(), constraints);

        constraints.gridx = 1;
        loginPanel.add(loginModel.getPasswordField(), constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        loginPanel.add(loginModel.getLoginButton(), constraints);

        constraints.gridx = 1;
        loginPanel.add(loginModel.getCancelButton(), constraints);

        LoginController loginController = new LoginController(loginModel);

        loginModel.getLoginButton().addActionListener(loginController);
        loginModel.getCancelButton().addActionListener(loginController);


        return loginPanel;
    }
}
