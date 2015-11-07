package objects;

public class UserDeck {

    private int userDeckId;
    private int deckPriority;
    private String deckName;
    private int cardOneId;
    private int cardTwoId;
    private int cardThreeId;
    private int cardFourId;
    private int cardFiveId;
    private String deckType;

    public UserDeck(int userDeckId, int deckPriority, String deckName, int cardOneId, int cardTwoId, int cardThreeId, int cardFourId, int cardFiveId, String deckType) {
        this.userDeckId = userDeckId;
        this.deckPriority = deckPriority;
        this.deckName = deckName;
        this.cardOneId = cardOneId;
        this.cardTwoId = cardTwoId;
        this.cardThreeId = cardThreeId;
        this.cardFourId = cardFourId;
        this.cardFiveId = cardFiveId;
        this.deckType = deckType;
    }

    public int getUserDeckId() {
        return userDeckId;
    }

    public void setUserDeckId(int userDeckId) {
        this.userDeckId = userDeckId;
    }

    public int getDeckPriority() {
        return deckPriority;
    }

    public void setDeckPriority(int deckPriority) {
        this.deckPriority = deckPriority;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public int getCardOneId() {
        return cardOneId;
    }

    public void setCardOneId(int cardOneId) {
        this.cardOneId = cardOneId;
    }

    public int getCardTwoId() {
        return cardTwoId;
    }

    public void setCardTwoId(int cardTwoId) {
        this.cardTwoId = cardTwoId;
    }

    public int getCardThreeId() {
        return cardThreeId;
    }

    public void setCardThreeId(int cardThreeId) {
        this.cardThreeId = cardThreeId;
    }

    public int getCardFourId() {
        return cardFourId;
    }

    public void setCardFourId(int cardFourId) {
        this.cardFourId = cardFourId;
    }

    public int getCardFiveId() {
        return cardFiveId;
    }

    public void setCardFiveId(int cardFiveId) {
        this.cardFiveId = cardFiveId;
    }

    public String getDeckType() {
        return deckType;
    }

    public void setDeckType(String deckType) {
        this.deckType = deckType;
    }
}
