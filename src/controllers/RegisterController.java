package controllers;

import models.RegisterModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterController implements ActionListener {

    private RegisterModel registerModel;

    public RegisterController(RegisterModel registerModel) { this.registerModel = registerModel; }

    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals(registerModel.getCancelButton().getText())) {
            registerModel.showMenuCard();
        } else if (event.getActionCommand().equals(registerModel.getRegisterButton().getText())) {
            registerModel.register();
        }
    }
}
