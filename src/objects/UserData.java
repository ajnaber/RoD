package objects;


import java.sql.Timestamp;

public class UserData {
    private Timestamp registrationDate;
    private Timestamp lastLoginDate;
    private int numConLogins;
    private int numLogins;

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Timestamp getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Timestamp lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public int getNumConLogins() {
        return numConLogins;
    }

    public void setNumConLogins(int numConLogins) {
        this.numConLogins = numConLogins;
    }

    public int getNumLogins() {
        return numLogins;
    }

    public void setNumLogins(int numLogins) {
        this.numLogins = numLogins;
    }
}
