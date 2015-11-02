package controllers;

import models.LoginModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {

    private LoginModel loginModel;

    public LoginController(LoginModel loginModel) { this.loginModel = loginModel; }

    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals(loginModel.getLoginButton().getText())) {
            loginModel.login();
        } else if (event.getActionCommand().equals(loginModel.getCancelButton().getText())) {
            loginModel.showMenuCard();
        }
    }
}
