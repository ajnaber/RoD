package objects;

public class UserCharm {
    private int userCharmId;
    private int charmId;
    private int charmAmount;
    private String charmName;
    private int charmAttack;
    private int charmDefense;
    private int charmStamina;

    public UserCharm(int userCharmId, int charmId, int charmAmount, String charmName, int charmAttack, int charmDefense, int charmStamina) {
        this.userCharmId = userCharmId;
        this.charmId = charmId;
        this.charmAmount = charmAmount;
        this.charmName = charmName;
        this.charmAttack = charmAttack;
        this.charmDefense = charmDefense;
        this.charmStamina = charmStamina;
    }

    public int getUserCharmId() {
        return userCharmId;
    }

    public void setUserCharmId(int userCharmId) {
        this.userCharmId = userCharmId;
    }

    public int getCharmId() {
        return charmId;
    }

    public void setCharmId(int charmId) {
        this.charmId = charmId;
    }

    public int getCharmAmount() {
        return charmAmount;
    }

    public void setCharmAmount(int charmAmount) {
        this.charmAmount = charmAmount;
    }

    public String getCharmName() {
        return charmName;
    }

    public void setCharmName(String charmName) {
        this.charmName = charmName;
    }

    public int getCharmAttack() {
        return charmAttack;
    }

    public void setCharmAttack(int charmAttack) {
        this.charmAttack = charmAttack;
    }

    public int getCharmDefense() {
        return charmDefense;
    }

    public void setCharmDefense(int charmDefense) {
        this.charmDefense = charmDefense;
    }

    public int getCharmStamina() {
        return charmStamina;
    }

    public void setCharmStamina(int charmStamina) {
        this.charmStamina = charmStamina;
    }
}
