package objects;

import java.util.ArrayList;

public class User {

    private UserLogin userLogin = new UserLogin();
    private UserData userData = new UserData();
    private UserInfo userInfo = new UserInfo();

    private ArrayList<UserItem> userItems = new ArrayList<>();
    private ArrayList<UserDeck> userDecks = new ArrayList<>();
    private ArrayList<UserCard> userCards = new ArrayList<>();
    private ArrayList<UserCharm> userCharms = new ArrayList<>();

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public ArrayList<UserItem> getUserItems() {
        return userItems;
    }

    public void setUserItems(ArrayList<UserItem> userItems) {
        this.userItems = userItems;
    }

    public ArrayList<UserDeck> getUserDecks() {
        return userDecks;
    }

    public void setUserDecks(ArrayList<UserDeck> userDecks) {
        this.userDecks = userDecks;
    }

    public ArrayList<UserCard> getUserCards() {
        return userCards;
    }

    public void setUserCards(ArrayList<UserCard> userCards) {
        this.userCards = userCards;
    }

    public ArrayList<UserCharm> getUserCharms() {
        return userCharms;
    }

    public void setUserCharms(ArrayList<UserCharm> userCharms) {
        this.userCharms = userCharms;
    }
}
