package controllers;

import models.MenuModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController implements ActionListener {

    private MenuModel menuModel;

    public MenuController(MenuModel menuModel) {
        this.menuModel = menuModel;
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals(menuModel.getLoginButton().getText())) { menuModel.showLoginCard(); }
        if (event.getActionCommand().equals(menuModel.getRegisterButton().getText())) { menuModel.showRegisterCard(); }
        if (event.getActionCommand().equals(menuModel.getRecoverButton().getText())) { menuModel.showRecoverCard(); }
    }
}
