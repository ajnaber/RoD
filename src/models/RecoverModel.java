package models;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RecoverModel {

    private CardLayout cardLayout;
    private JPanel cardHolder;
    private ArrayList<String> cardList;

    private JPanel recoverPanel = new JPanel(new GridBagLayout());

    private JLabel emailLabel = new JLabel("Enter email: ");
    private JTextField emailField = new JTextField(20);

    private JLabel errorLabel = new JLabel("");

    private JButton cancelButton = new JButton("Cancel");
    private JButton recoverButton = new JButton("Recover");

    private JLabel recoverGuidelines = new JLabel("<html><pre>Enter the email you registered\nwith and a link to reset your\npassword will be sent</pre></html>");

    public RecoverModel(CardLayout cardLayout, JPanel cardHolder, ArrayList<String> cardList) {
        this.cardLayout = cardLayout;
        this.cardHolder = cardHolder;
        this.cardList = cardList;
    }

    public void showMenuCard() {
        cardLayout.show(cardHolder, cardList.get(0));
    }

    public void recover() {

    }

    public JPanel getRecoverPanel() {
        return recoverPanel;
    }

    public JLabel getEmailLabel() {
        return emailLabel;
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

    public JButton getRecoverButton() {
        return recoverButton;
    }

    public JLabel getRecoverGuidelines() {
        return recoverGuidelines;
    }
}
