package models;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class RegisterModel {

    private CardLayout cardLayout;
    private JPanel cardHolder;
    private ArrayList<String> cardList;

    //Initialize the register panel
    private JPanel registerPanel = new JPanel(new GridBagLayout());

    //Assign values and create components
    private JLabel usernameLabel = new JLabel("Enter username: ");
    private JLabel passwordLabel = new JLabel("Enter password: ");
    private JLabel reEnterPLabel = new JLabel("Confirm Password: ");
    private JLabel emailLabel = new JLabel("Enter email: ");

    private JTextField usernameField = new JTextField(20);
    private JPasswordField passwordField = new JPasswordField(20);
    private JPasswordField reEnterPField = new JPasswordField(20);
    private JTextField emailField = new JTextField(20);

    private JLabel errorLabel = new JLabel("");

    private JButton cancelButton = new JButton("Cancel");
    private JButton registerButton = new JButton("Register");

    private JLabel usernameGuidelines = new JLabel("<html><pre>Username must be 1-12 characters.\nValid Characters: Upper and lower\ncase, A-Z, and 0-9.</pre></html>");
    private JLabel passwordGuidelines = new JLabel("<html><pre>Password needs to be at least 6 \ncharacters. Valid Characters: Any, so\nplease make it secure.</pre></html>");
    private JLabel emailGuidelines = new JLabel("<html><pre>Validation email will be sent to address,\nplease ensure it is correct.</pre></html>");

    public RegisterModel(CardLayout cardLayout, JPanel cardHolder, ArrayList<String> cardList) {
        this.cardLayout = cardLayout;
        this.cardHolder = cardHolder;
        this.cardList = cardList;

    }

    public void showMenuCard() {
        cardLayout.show(cardHolder, cardList.get(0));
    }

    public void register() {

    }

    public JPanel getRegisterPanel() {
        return registerPanel;
    }

    public JLabel getUsernameLabel() {
        return usernameLabel;
    }

    public JLabel getPasswordLabel() {
        return passwordLabel;
    }

    public JLabel getReEnterPLabel() {
        return reEnterPLabel;
    }

    public JLabel getEmailLabel() {
        return emailLabel;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JPasswordField getReEnterPField() {
        return reEnterPField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JLabel getErrorLabel() {
        return errorLabel;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public JLabel getUsernameGuidelines() {
        return usernameGuidelines;
    }

    public JLabel getPasswordGuidelines() {
        return passwordGuidelines;
    }

    public JLabel getEmailGuidelines() {
        return emailGuidelines;
    }
}
