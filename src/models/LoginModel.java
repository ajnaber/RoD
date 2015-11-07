package models;

import database.UserLoginDB;
import objects.User;
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
    private User user;

    private JPanel loginPanel = new JPanel(new GridBagLayout());

    public LoginModel(CardLayout cardLayout, JPanel cardHolder, ArrayList<String> cardList, GameGUI gameGUI, User user) {
        this.cardLayout = cardLayout;
        this.cardHolder = cardHolder;
        this.cardList = cardList;
        this.gameGUI = gameGUI;
        this.user = user;

    }

    public void showMenuCard() {
        cardLayout.show(cardHolder, cardList.get(0));
    }

    public void login() {
        loginButton.setEnabled(false);
        cancelButton.setEnabled(false);
        UserLoginDB userLoginDB = new UserLoginDB(this);

        String errors = "";
        if(!checkUsernameChars()) {
            errors = "Username contained invalid characters.\n";
        }

        if(!checkUsernameLength()) {
            errors += "Username length invalid.\n";
        }

        if (!checkPasswordLength()) {
            errors += "Password length invalid.\n";
        }

        if(errors.equals("")) {

            if (!userLoginDB.subLogin()) {
                passwordField.setText("");
                usernameField.setText("");
                errorLabel.setText("Username or password incorrect.\n");
                loginButton.setEnabled(true);
                cancelButton.setEnabled(true);
            } else {
                user.getUserLogin().setUsername(usernameField.getText());
                passwordField.setText("");
                usernameField.setText("");
                errorLabel.setText("Authenticated. Logging in...");

                System.out.println("User successfully logged in.");
                postLoginOperations(userLoginDB);
            }

        } else {
            errors = "<html><pre>" + errors + "</html></pre>";
            errorLabel.setText(errors);
            System.out.println(errors);
            passwordField.setText("");
            usernameField.setText("");
            loginButton.setEnabled(true);
            cancelButton.setEnabled(true);
        }


    }

    private void postLoginOperations(UserLoginDB userLoginDB) {
        System.out.println("Creating user object and fetching user data and session info.");
        //Fetch UserLogin info
        userLoginDB.fetchAndCreateUser(user);
        System.out.println("Post-login operations completed, initializing GUI.");
        gameGUI.clearFrame();


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

    //Registration Validation
    private boolean checkUsernameChars() {
        char[] uName = usernameField.getText().toCharArray();
        boolean valid = true;

        for (char c:uName) {
            valid = ((c >= 'a') && (c <= 'z')) ||
                    ((c >= 'A') && (c <= 'Z')) ||
                    ((c >= '0') && (c <= 'z'));

            if(!valid) break;
        }

        return valid;
    }

    //Check if username is < 1 or > 12 (should be)
    private boolean checkUsernameLength() {
        return (usernameField.getText().length() > 0 && usernameField.getText().length() < 13);
    }

    private boolean checkPasswordLength() {
        return (passwordField.getPassword().length > 5);
    }



}
