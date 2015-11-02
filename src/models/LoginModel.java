package models;

import views.GameGUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LoginModel {

    private CardLayout cardLayout;
    private JPanel cardHolder;
    private ArrayList<String> cardList;
    private GameGUI gameGUI;

    private JLabel usernameLabel = new JLabel("Enter Username: ");
    private JLabel passwordLabel = new JLabel("Enter Password: ");
    private JLabel errorLabel = new JLabel();
    private JTextField usernameField = new JTextField(20);
    private JPasswordField passwordField = new JPasswordField(20);
    private JButton loginButton = new JButton("Login");
    private JButton cancelButton = new JButton("Cancel");
    private JLabel loginNotice = new JLabel("<html><pre>Login using your credentials</pre></html>");

    private JPanel loginPanel = new JPanel(new GridBagLayout());

    public LoginModel(CardLayout cardLayout, JPanel cardHolder, ArrayList<String> cardList, GameGUI gameGUI) {
        this.cardLayout = cardLayout;
        this.cardHolder = cardHolder;
        this.cardList = cardList;
        this.gameGUI = gameGUI;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JPanel getLoginPanel() {
        return loginPanel;
    }

    public JLabel getErrorLabel() {
        return errorLabel;
    }

    public JLabel getUsernameLabel() {
        return usernameLabel;
    }

    public JLabel getPasswordLabel() {
        return passwordLabel;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JLabel getLoginNotice() {
        return loginNotice;
    }

    public void showMenuCard() {
        cardLayout.show(cardHolder, cardList.get(0));
    }

    public void login() {

    }

}
