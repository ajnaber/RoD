package views.cards;

import controllers.MenuController;
import models.MenuModel;

import javax.swing.*;

public class MenuCard {
    MenuModel menuModel;

    public MenuCard(MenuModel menuModel) {
        this.menuModel = menuModel;
    }

    public JPanel createLayout() {
        JLabel title = menuModel.getTitle();
        title.setHorizontalAlignment(JLabel.CENTER);

        JPanel menuPanel = menuModel.getMenuPanel();

        menuPanel.add(title);
        menuPanel.add(menuModel.getLoginButton());
        menuPanel.add(menuModel.getRegisterButton());
        menuPanel.add(menuModel.getRecoverButton());

        MenuController menuController = new MenuController(menuModel);

        menuModel.getLoginButton().addActionListener(menuController);
        menuModel.getRegisterButton().addActionListener(menuController);

        return menuPanel;
    }
}
