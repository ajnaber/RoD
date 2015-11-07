package models;

import database.UserLoginDB;
import objects.User;
import views.GameGUI;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.*;
import java.awt.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;


public class RegisterModel {
    private byte[] salt;
    private byte[] encryptedPassword;

    private CardLayout cardLayout;
    private JPanel cardHolder;
    private ArrayList<String> cardList;
    private User user;

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

    public RegisterModel(CardLayout cardLayout, JPanel cardHolder, ArrayList<String> cardList, User user) {
        this.cardLayout = cardLayout;
        this.cardHolder = cardHolder;
        this.cardList = cardList;
        this.user = user;

    }

    public void showMenuCard() {
        cardLayout.show(cardHolder, cardList.get(0));
    }

    public void register() {
        UserLoginDB userLoginDB = new UserLoginDB(this);

        String errors = "";
        if(!checkUsernameChars()) {
            errors = "Username contained invalid characters.\n";
        }

        if(!checkPasswordsMatch()) {
            errors += "Passwords did not match.\n";
        }

        if(!checkUsernameLength()) {
            errors += "Username length invalid.\n";
        }

        if (!checkPasswordLength()) {
            errors += "Password length invalid.\n";
        }

        if (!checkValidEmail()) {
            errors += "Email failed validation, check it.\n";
        }
        //If no errors by this point all non-database checks have passed

        if(errors.equals("")) {
            if(!userLoginDB.checkDuplicateUsername()) {
                errors += "Username already in use.\n";
            }
            if(!userLoginDB.checkDuplicateEmail()) {
                errors+= "Email already in use.\n";
            }
        }

        //Final check for errors, if none then submit the registration
        if (!errors.equals("")) {

            errors = "<html><pre>" + errors + "</html></pre>";
            errorLabel.setText(errors);
            System.out.println(errors);
            //Clear out password information
            passwordField.setText("");
            reEnterPField.setText("");
            usernameField.setText("");
            emailField.setText("");

        } else {
            if (validateRegistration()) {
                if (userLoginDB.subRegistration(user)) {
                    errorLabel.setForeground(Color.BLACK);
                    errorLabel.setText("Account Created: Check Email");

                    //Clear out password information
                    passwordField.setText("");
                    reEnterPField.setText("");
                    usernameField.setText("");
                    emailField.setText("");

                    //Run post login operations
                    postRegisterOperations(userLoginDB);



                } else {
                    System.out.println("Something went wrong submitting registration...");
                }
            } else {
                System.out.println("Something went wrong validating registration...");
            }

        }

    }

    public void postRegisterOperations(UserLoginDB userLoginDB) {
        System.out.println("Creating user object and registering user data and session info.");
        userLoginDB.createNewUser(user);
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

    public byte[] getSalt() {
        return salt;
    }

    public byte[] getEncryptedPassword() {
        return encryptedPassword;
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

    private boolean checkPasswordsMatch() {
        return Arrays.equals(passwordField.getPassword(), reEnterPField.getPassword());
    }

    //Check if username is < 1 or > 12 (should be)
    private boolean checkUsernameLength() {
        return (usernameField.getText().length() > 0 && usernameField.getText().length() < 13);
    }

    private boolean checkPasswordLength() {
        return (passwordField.getPassword().length > 5);
    }

    private boolean checkValidEmail() {
        boolean valid = true;

        try {
            InternetAddress emailAddr = new InternetAddress(emailField.getText());
            emailAddr.validate();
        } catch(AddressException e) {
            valid = false;
        }

        return valid;
    }

    private boolean validateRegistration() {
        SecurityModel securityModel = new SecurityModel();

        try {
            //Generate Salt
            salt = securityModel.generateSalt();

            //Encrypt password with salt
            encryptedPassword = securityModel.getEncryptedPassword(passwordField.getPassword(), salt);

            //Test authentication
            return securityModel.authenticate(passwordField.getPassword(), encryptedPassword, salt);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return false;
    }


}
