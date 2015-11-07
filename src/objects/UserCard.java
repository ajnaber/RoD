package objects;

public class UserCard {
    private int userCardId;
    private int cardId;
    private int cardSkillLevel;
    private int cardEvo;
    private int cardLevel;
    private int cardExp;
    private int cardAttack;
    private int cardDefense;
    private int cardLuck;
    private String cardName;
    private String cardDesc;
    private String cardRarity;
    private String cardType;
    private String cardStyle;
    private int cardPower;
    private int cardSkill_id;
    private String skillName;
    private String skillDesc;
    private String skillMagnitude;
    private String skillType;
    private String skillTarget;
    private String skillRange;
    private String skillScope;

    public UserCard(int userCardId, int cardId,
                    int cardSkillLevel, int cardEvo,
                    int cardLevel, int cardExp,
                    int cardAttack, int cardDefense,
                    int cardLuck, String cardName,
                    String cardDesc, String cardRarity,
                    String cardType, String cardStyle,
                    int cardPower, int cardSkill_id,
                    String skillName, String skillDesc,
                    String skillMagnitude, String skillType,
                    String skillTarget, String skillRange,
                    String skillScope) {

        this.userCardId = userCardId;
        this.cardId = cardId;
        this.cardSkillLevel = cardSkillLevel;
        this.cardEvo = cardEvo;
        this.cardLevel = cardLevel;
        this.cardExp = cardExp;
        this.cardAttack = cardAttack;
        this.cardDefense = cardDefense;
        this.cardLuck = cardLuck;
        this.cardName = cardName;
        this.cardDesc = cardDesc;
        this.cardRarity = cardRarity;
        this.cardType = cardType;
        this.cardStyle = cardStyle;
        this.cardPower = cardPower;
        this.cardSkill_id = cardSkill_id;
        this.skillName = skillName;
        this.skillDesc = skillDesc;
        this.skillMagnitude = skillMagnitude;
        this.skillType = skillType;
        this.skillTarget = skillTarget;
        this.skillRange = skillRange;
        this.skillScope = skillScope;

    }

    public int getUserCardId() {
        return userCardId;
    }

    public void setUserCardId(int userCardId) {
        this.userCardId = userCardId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getCardSkillLevel() {
        return cardSkillLevel;
    }

    public void setCardSkillLevel(int cardSkillLevel) {
        this.cardSkillLevel = cardSkillLevel;
    }

    public int getCardEvo() {
        return cardEvo;
    }

    public void setCardEvo(int cardEvo) {
        this.cardEvo = cardEvo;
    }

    public int getCardLevel() {
        return cardLevel;
    }

    public void setCardLevel(int cardLevel) {
        this.cardLevel = cardLevel;
    }

    public int getCardExp() {
        return cardExp;
    }

    public void setCardExp(int cardExp) {
        this.cardExp = cardExp;
    }

    public int getCardAttack() {
        return cardAttack;
    }

    public void setCardAttack(int cardAttack) {
        this.cardAttack = cardAttack;
    }

    public int getCardDefense() {
        return cardDefense;
    }

    public void setCardDefense(int cardDefense) {
        this.cardDefense = cardDefense;
    }

    public int getCardLuck() {
        return cardLuck;
    }

    public void setCardLuck(int cardLuck) {
        this.cardLuck = cardLuck;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardDesc() {
        return cardDesc;
    }

    public void setCardDesc(String cardDesc) {
        this.cardDesc = cardDesc;
    }

    public String getCardRarity() {
        return cardRarity;
    }

    public void setCardRarity(String cardRarity) {
        this.cardRarity = cardRarity;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardStyle() {
        return cardStyle;
    }

    public void setCardStyle(String cardStyle) {
        this.cardStyle = cardStyle;
    }

    public int getCardPower() {
        return cardPower;
    }

    public void setCardPower(int cardPower) {
        this.cardPower = cardPower;
    }

    public int getCardSkill_id() {
        return cardSkill_id;
    }

    public void setCardSkill_id(int cardSkill_id) {
        this.cardSkill_id = cardSkill_id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillDesc() {
        return skillDesc;
    }

    public void setSkillDesc(String skillDesc) {
        this.skillDesc = skillDesc;
    }

    public String getSkillMagnitude() {
        return skillMagnitude;
    }

    public void setSkillMagnitude(String skillMagnitude) {
        this.skillMagnitude = skillMagnitude;
    }

    public String getSkillType() {
        return skillType;
    }

    public void setSkillType(String skillType) {
        this.skillType = skillType;
    }

    public String getSkillTarget() {
        return skillTarget;
    }

    public void setSkillTarget(String skillTarget) {
        this.skillTarget = skillTarget;
    }

    public String getSkillRange() {
        return skillRange;
    }

    public void setSkillRange(String skillRange) {
        this.skillRange = skillRange;
    }

    public String getSkillScope() {
        return skillScope;
    }

    public void setSkillScope(String skillScope) {
        this.skillScope = skillScope;
    }
}
