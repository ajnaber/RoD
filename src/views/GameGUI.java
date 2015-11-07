package views;

import models.LoginModel;
import models.MenuModel;
import models.RecoverModel;
import models.RegisterModel;
import objects.User;
import views.cards.LoginCard;
import views.cards.MenuCard;
import views.cards.RecoverCard;
import views.cards.RegisterCard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameGUI extends JFrame {

    private User user;
    private ArrayList<String> cardList;

    public GameGUI(User user) {
        cardList = new ArrayList<>(16);

        cardList.add("Menu Card");
        cardList.add("Login Card");
        cardList.add("Register Card");
        cardList.add("Recover Card");

        cardList.add("Home Card");
        cardList.add("Profile Card");
        cardList.add("Quest Card");
        cardList.add("Battle Card");
        cardList.add("Cards Card");
        cardList.add("Decks Card");
        cardList.add("Items Card");
        cardList.add("Shop Card");
        cardList.add("Notes Card");
        cardList.add("Trades Card");
        cardList.add("Messages Card");
        cardList.add("Order Card");

        this.user = user;
        this.setTitle("Realm of Demons");
        this.setPreferredSize(new Dimension(1920, 1080));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

    }


    //Sets up the Login User Interface
    public void createLoginUI() {

        //Create new CardLayout
        CardLayout cardLayout = new CardLayout();

        //Create top level JPanels to be added to Frame
        JPanel cardWrapper = new JPanel(new GridBagLayout());
        JPanel cardHolder = new JPanel(cardLayout);

        //Set cardWrapper preferred size
        cardWrapper.setPreferredSize(new Dimension(400, 600));

        MenuModel menuModel = new MenuModel(cardLayout, cardHolder, cardList);
        LoginModel loginModel = new LoginModel(cardLayout, cardHolder, cardList, this, user);
        RegisterModel registerModel = new RegisterModel(cardLayout, cardHolder, cardList, user);
        RecoverModel recoverModel = new RecoverModel(cardLayout, cardHolder, cardList);

        //Create panels for each card
        MenuCard menuCard = new MenuCard(menuModel);
        LoginCard loginCard = new LoginCard(loginModel);
        RegisterCard registerCard = new RegisterCard(registerModel);
        RecoverCard recoverCard = new RecoverCard(recoverModel);

        //Add card panels to the card holder
        cardHolder.add(menuCard.createLayout(), cardList.get(0));
        cardHolder.add(loginCard.createLayout(), cardList.get(1));
        cardHolder.add(registerCard.createLayout(), cardList.get(2));
        cardHolder.add(recoverCard.createLayout(), cardList.get(3));

        //Wrap cardHolder
        cardWrapper.add(cardHolder, new GridBagConstraints());

        //Set up Frame
        this.add(cardWrapper);
        this.pack();
        this.setVisible(true);
    }

    public void clearFrame() {
        this.getContentPane().removeAll();
    }

    public void createInGameUI() {
        CardLayout cardLayout = new CardLayout();

        //Wrapper and Holder for the cards
        JPanel cardWrapper = new JPanel(new GridBagLayout());
        cardWrapper.setPreferredSize(new Dimension(1920, 1080));

        JPanel cardHolder = new JPanel(cardLayout);

        GridBagConstraints constraints = new GridBagConstraints();


    }
}
