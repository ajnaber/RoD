package models;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MenuModel {
    private JButton loginButton = new JButton("Login");
    private JButton registerButton = new JButton("Register");
    private JButton recoverButton = new JButton("Recover Credentials");
    private JPanel menuPanel = new JPanel(new GridLayout(4,1,0,30));
    private JLabel title = new JLabel("Login or Register to Proceed");

    private CardLayout cardLayout;
    private JPanel cardHolder;
    private ArrayList<String> cardList;

    public MenuModel(CardLayout cardLayout, JPanel cardHolder, ArrayList<String> cardList) {
        this.cardLayout = cardLayout;
        this.cardHolder = cardHolder;
        this.cardList = cardList;
    }

    public void showLoginCard() {
        cardLayout.show(cardHolder, cardList.get(1));
    }

    public void showRegisterCard() {
        cardLayout.show(cardHolder, cardList.get(2));
    }

    public void showRecoverCard() {
        cardLayout.show(cardHolder, cardList.get(3));
    }

    //GETTERS


    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public JButton getRecoverButton() {
        return recoverButton;
    }

    public JPanel getMenuPanel() {
        return menuPanel;
    }

    public JLabel getTitle() {
        return title;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public JPanel getCardHolder() {
        return cardHolder;
    }

    public ArrayList<String> getCardList() {
        return cardList;
    }
}
