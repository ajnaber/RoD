package objects;

public class UserItem {
    private int userItemId;
    private int itemId;
    private int itemAmount;
    private String itemName;
    private String itemDesc;

    public UserItem(int userItemId, int itemId, int itemAmount, String itemName, String itemDesc) {
        this.userItemId = userItemId;
        this.itemId = itemId;
        this.itemAmount = itemAmount;
        this.itemName = itemName;
        this.itemDesc = itemDesc;
    }

    public int getUserItemId() {
        return userItemId;
    }

    public void setUserItemId(int userItemId) {
        this.userItemId = userItemId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(int itemAmount) {
        this.itemAmount = itemAmount;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }
}
