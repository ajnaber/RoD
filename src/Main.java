import objects.User;
import views.GameGUI;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        //Create default User Object
        System.out.println("User Object created (Main)");
        User user = new User();

        //Create GUI
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Invoking GUI (Main)");
        SwingUtilities.invokeLater(()->new GameGUI(user).createLoginUI());

    }
}