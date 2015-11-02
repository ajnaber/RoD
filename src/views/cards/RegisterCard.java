package views.cards;

import controllers.LoginController;
import controllers.RegisterController;
import models.RegisterModel;

import javax.swing.*;
import java.awt.*;

public class RegisterCard {
    RegisterModel registerModel;

    public RegisterCard(RegisterModel registerModel) { this.registerModel = registerModel; }

    public JPanel createLayout() {
        JPanel registerPanel = registerModel.getRegisterPanel();

        registerModel.getErrorLabel().setForeground(Color.RED);

        GridBagConstraints constraints = new GridBagConstraints();

        //Anchor to left side
        constraints.anchor = GridBagConstraints.WEST;
        //Pad the cells
        constraints.insets = new Insets(10,10,10,10);

        //add comp to panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        registerPanel.add(registerModel.getErrorLabel(), constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        registerPanel.add(registerModel.getUsernameGuidelines(), constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        registerPanel.add(registerModel.getUsernameLabel(), constraints);

        constraints.gridx = 1;
        registerPanel.add(registerModel.getUsernameField(), constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        registerPanel.add(registerModel.getEmailGuidelines(), constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        registerPanel.add(registerModel.getEmailLabel(), constraints);

        constraints.gridx = 1;
        registerPanel.add(registerModel.getEmailField(), constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        registerPanel.add(registerModel.getPasswordGuidelines(), constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        registerPanel.add(registerModel.getPasswordLabel(), constraints);

        constraints.gridx = 1;
        registerPanel.add(registerModel.getPasswordField(), constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        registerPanel.add(registerModel.getReEnterPLabel(), constraints);

        constraints.gridx = 1;
        registerPanel.add(registerModel.getReEnterPField(), constraints);

        constraints.gridx = 0;
        constraints.gridy = 8;
        registerPanel.add(registerModel.getRegisterButton(), constraints);

        constraints.gridx = 1;
        registerPanel.add(registerModel.getCancelButton(), constraints);

        RegisterController registerController = new RegisterController(registerModel);

        registerModel.getRegisterButton().addActionListener(registerController);
        registerModel.getCancelButton().addActionListener(registerController);

        return registerPanel;
    }
}
