package database;

import constants.DBConstants;
import models.LoginModel;
import models.RegisterModel;
import models.SecurityModel;
import objects.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class UserLoginDB {

    private static final String DB_URL = DBConstants.DB_URL;
    private static final String USER = DBConstants.USER;
    private static final String PASS = DBConstants.PASS;

    private RegisterModel registerModel;
    private LoginModel loginModel;

    public UserLoginDB(RegisterModel registerModel) {
        this.registerModel = registerModel;
    }

    public UserLoginDB(LoginModel loginModel) {
        this.loginModel = loginModel;
    }

    public boolean checkDuplicateUsername() {
        Connection conn = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        boolean valid = true;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String query = "SELECT username FROM userlogin WHERE username = ?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, registerModel.getUsernameField().getText());
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) { valid=false; }

        } catch (Exception e) {
            System.out.println("Exception checking username");
        }finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {e.printStackTrace();}
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {e.printStackTrace();}
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {e.printStackTrace();}
            }

        }

        return valid;
    }

    public boolean checkDuplicateEmail() {
        Connection conn = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        boolean valid = true;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String query = "SELECT email FROM userlogin WHERE email = ?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, registerModel.getEmailField().getText());
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) { valid=false; }

        } catch (Exception e) {
            System.out.println("Exception checking email");
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {e.printStackTrace();}
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {e.printStackTrace();}
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {e.printStackTrace();}
            }

        }

        return valid;

    }

    public boolean subRegistration(User user) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        boolean valid = false;

        try {

            System.out.println("Connecting to database");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Connected to database");
            String query = "insert into userlogin (username, encryptedPassword, salt, email) values (?, ?, ?, ?)";

            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setString(1, registerModel.getUsernameField().getText());
            preparedStatement.setBytes(2, registerModel.getEncryptedPassword());
            preparedStatement.setBytes(3, registerModel.getSalt());
            preparedStatement.setString(4, registerModel.getEmailField().getText());

            preparedStatement.execute();
            valid = true;

        } catch (Exception se) {
            System.err.println("Got and Exception in submit Registration!");
            System.err.println(se.getMessage());
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {e.printStackTrace();}
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {e.printStackTrace();}
            }

        }
        user.getUserLogin().setUsername(registerModel.getUsernameField().getText());
        return valid;
    }

    public boolean subLogin() {
        Connection conn = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            System.out.println("Connecting to database");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String query = "SELECT * FROM userlogin WHERE username = ?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, loginModel.getUsernameField().getText());
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                SecurityModel securityModel = new SecurityModel();
                byte[] encryptedPassword = resultSet.getBytes("encryptedPassword");
                byte[] salt = resultSet.getBytes("salt");

                try {
                    if(securityModel.authenticate(loginModel.getPasswordField().getPassword(), encryptedPassword, salt)) {
                        System.out.println("User Authenticated");
                        return true;
                    } else {
                        System.out.println("Username correct but password incorrect");
                    }
                } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                    System.out.println("exception in checkLogin");
                    e.printStackTrace();

                }
            } else {
                System.out.println("No username found");
                return false;
            }

        } catch (Exception e) {
            System.out.println("Exception in subLogin");
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {e.printStackTrace();}
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {e.printStackTrace();}
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {e.printStackTrace();}
            }

        }
        return false;
    }

    public void fetchAndCreateUser(User user) {
        Connection conn = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //Fetch UserLogin
            System.out.println("Fetching UserLogin...");
            String query = "SELECT * FROM userlogin WHERE username = ?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, user.getUserLogin().getUsername());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user.getUserLogin().setEmail(resultSet.getString("email"));
                user.getUserLogin().setUserId(resultSet.getInt("id"));
            }

            //Fetch UserData
            System.out.println("Fetching UserData...");
            query = "SELECT * FROM userdata WHERE userId = ?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, user.getUserLogin().getUserId());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user.getUserData().setLastLoginDate(resultSet.getTimestamp("registrationDate"));
                user.getUserData().setRegistrationDate(resultSet.getTimestamp("lastLoginDate"));
                user.getUserData().setNumConLogins(resultSet.getInt("numConLogins"));
                user.getUserData().setNumLogins(resultSet.getInt("numLogins"));
            }

            //Fetch UserInfo
            System.out.println("Fetching UserInfo...");
            query = "SELECT * FROM userinfo WHERE userId = ?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, user.getUserLogin().getUserId());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user.getUserInfo().setUserLevel(resultSet.getInt("userLevel"));
                user.getUserInfo().setUserExp(resultSet.getInt("userExp"));
                user.getUserInfo().setUserCardLimit(resultSet.getInt("userCardLimit"));
                user.getUserInfo().setUserAttackPower(resultSet.getInt("userAttackPower"));
                user.getUserInfo().setUserDefensePower(resultSet.getInt("userDefensePower"));
                user.getUserInfo().setUserStamina(resultSet.getInt("userStamina"));
                user.getUserInfo().setUserCurAttackPower(resultSet.getInt("userCurAttackPower"));
                user.getUserInfo().setUserCurDefensePower(resultSet.getInt("userCurDefensePower"));
                user.getUserInfo().setUserCurStamina(resultSet.getInt("userCurStamina"));
                user.getUserInfo().setUserFriendLimit(resultSet.getInt("userFriendLimit"));
                user.getUserInfo().setUserCharmId(resultSet.getInt("userCharmId"));
                user.getUserInfo().setUserUnusedStats(resultSet.getInt("userUnusedStats"));
                user.getUserInfo().setUserProfileDescription(resultSet.getString("userProfileDescription"));
                user.getUserInfo().setUserOrderId(resultSet.getInt("userOrderId"));
            }

            //Fetch Cards
            System.out.println("Fetching Cards...");
            query = "SELECT cardinfo.cardName, cardinfo.cardDesc, cardinfo.cardRarity, cardinfo.cardType, cardinfo.cardStyle, cardinfo.cardPower, cardinfo.cardSkill_id, usercards.*, cardskills.* FROM usercards, cardinfo, cardskills WHERE (usercards.userId = ? AND cardinfo.cardId = usercards.cardId AND cardskills.skill_id = cardinfo.cardSkill_id) LIMIT 0, 1000";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, user.getUserLogin().getUserId());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int userCardId = resultSet.getInt("userCardId");
                int cardId = resultSet.getInt("cardId");
                int cardSkillLevel = resultSet.getInt("cardSkillLevel");
                int cardEvo = resultSet.getInt("cardEvo");
                int cardLevel = resultSet.getInt("cardLevel");
                int cardExp = resultSet.getInt("cardExp");
                int cardAttack = resultSet.getInt("cardAttack");
                int cardDefense = resultSet.getInt("cardDefense");
                int cardLuck = resultSet.getInt("cardLuck");
                String cardName = resultSet.getString("cardName");
                String cardDesc = resultSet.getString("cardDesc");
                String cardRarity = resultSet.getString("cardRarity");
                String cardType = resultSet.getString("cardType");
                String cardStyle = resultSet.getString("cardStyle");
                int cardPower = resultSet.getInt("cardPower");
                int cardSkill_id = resultSet.getInt("cardSkill_id");
                String skillName = resultSet.getString("skillName");
                String skillDesc = resultSet.getString("skillDesc");
                String skillMagnitude = resultSet.getString("skillMagnitude");
                String skillType = resultSet.getString("skillType");
                String skillTarget = resultSet.getString("skillTarget");
                String skillRange = resultSet.getString("skillRange");
                String skillScope = resultSet.getString("skillScope");

                UserCard userCard = new UserCard(userCardId, cardId,
                        cardSkillLevel, cardEvo, cardLevel, cardExp,
                        cardAttack, cardDefense, cardLuck, cardName,
                        cardDesc, cardRarity, cardType, cardStyle,
                        cardPower, cardSkill_id, skillName, skillDesc,
                        skillMagnitude, skillType, skillTarget,
                        skillRange, skillScope);

                user.getUserCards().add(userCard);
            }

            //Fetch Charms
            System.out.println("Fetching Charms...");
            query = "SELECT usercharms.*, charminfo.charmName, charminfo.charmAttack, charminfo.charmDefense, charminfo.charmStamina from usercharms, charminfo WHERE (usercharms.userId = ? AND charminfo.charmId = usercharms.charmId)";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, user.getUserLogin().getUserId());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String charmName = resultSet.getString("charmName");
                int charmAttack = resultSet.getInt("charmAttack");
                int charmDefense = resultSet.getInt("charmDefense");
                int charmStamina = resultSet.getInt("charmStamina");
                int charmId = resultSet.getInt("charmId");
                int charmAmount = resultSet.getInt("charmAmount");
                int userCharmId = resultSet.getInt("userCharmId");

                UserCharm userCharm = new UserCharm(userCharmId, charmId, charmAmount, charmName, charmAttack, charmDefense, charmStamina);
                user.getUserCharms().add(userCharm);
            }

            //Fetch Items
            System.out.println("Fetching Items...");
            query = "SELECT useritems.userItemId, useritems.itemId, useritems.itemAmount, iteminfo.itemName, iteminfo.itemDesc FROM useritems, iteminfo WHERE (useritems.userId = ? AND iteminfo.itemId = useritems.itemId)";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, user.getUserLogin().getUserId());
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int userItemId = resultSet.getInt("userItemId");
                int itemId = resultSet.getInt("itemId");
                int itemAmount = resultSet.getInt("itemAmount");
                String itemName = resultSet.getString("itemName");
                String itemDesc = resultSet.getString("itemDesc");

                UserItem userItem = new UserItem(userItemId, itemId, itemAmount, itemName, itemDesc);
                user.getUserItems().add(userItem);
            }

            //Fetch Decks
            System.out.println("Fetching Decks...");
            query = "SELECT userdecks.* FROM userdecks WHERE userId = ?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, user.getUserLogin().getUserId());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int userDeckId = resultSet.getInt("userDeckId");
                int deckPriority = resultSet.getInt("deckPriority");
                String deckName = resultSet.getString("deckName");
                int cardOneId = resultSet.getInt("cardOneId");
                int cardTwoId = resultSet.getInt("cardTwoId");
                int cardThreeId = resultSet.getInt("cardThreeId");
                int cardFourId = resultSet.getInt("cardFourId");
                int cardFiveId = resultSet.getInt("cardFiveId");
                String deckType = resultSet.getString("deckType");

                UserDeck userDeck = new UserDeck(userDeckId, deckPriority, deckName, cardOneId, cardTwoId, cardThreeId, cardFourId, cardFiveId, deckType);
                user.getUserDecks().add(userDeck);

            }

            //Check for consequetive logins (last login within 24 hours);
            Instant instant = Instant.now().minus(24, ChronoUnit.HOURS);

            Timestamp timestamp = Timestamp.from(instant);

            int numConLogins;

            if (user.getUserData().getLastLoginDate().after(timestamp)) {
                numConLogins = user.getUserData().getNumConLogins() +1;
            } else {
                numConLogins = 0;
            }

            //Update lastLoginDate, numLogins, numConLogins
            System.out.println("Updating info");
            query = "UPDATE userdata SET lastLoginDate = ?, numConLogins = ?, numLogins = ? WHERE userId = ?";
            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setTimestamp(1, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
            preparedStatement.setInt(2, numConLogins);
            preparedStatement.setInt(3, (user.getUserData().getNumLogins()+1));
            preparedStatement.setInt(4, user.getUserLogin().getUserId());

            preparedStatement.executeUpdate();


        } catch (Exception e) {
            System.out.println("Exception fetching user data!");
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void createNewUser(User user) {
        Connection conn = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //Get userId
            String query = "SELECT userlogin.id FROM userlogin WHERE username = ?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, user.getUserLogin().getUsername());
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                user.getUserLogin().setUserId(resultSet.getInt("id"));
            } else {
                System.out.println("Error: Could not find username.");
            }

            //Set userinfo
            query = "INSERT INTO userinfo (userId, userLevel, userExp, userCardLimit, userAttackPower," +
                    "userDefensePower, userStamina, userCurAttackPower, userCurDefensePower, userCurStamina," +
                    "userFriendLimit, userCharmId, userUnusedStats, userProfileDescription, userOrderId) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1, user.getUserLogin().getUserId());
            preparedStatement.setInt(2, 1);
            preparedStatement.setInt(3, 0);
            preparedStatement.setInt(4, 50);
            preparedStatement.setInt(5, 5);
            preparedStatement.setInt(6, 5);
            preparedStatement.setInt(7, 5);
            preparedStatement.setInt(8, 5);
            preparedStatement.setInt(9, 5);
            preparedStatement.setInt(10, 5);
            preparedStatement.setInt(11, 20);
            preparedStatement.setInt(12, 0);
            preparedStatement.setInt(13, 20);
            preparedStatement.setString(14, "Take it easy, I'm new!");
            preparedStatement.setInt(15, 0);
            preparedStatement.execute();

            //Set userdata
            query = "INSERT INTO userdata (userId, registrationDate, lastLoginDate, numConLogins, numLogins) " +
                    "VALUES (?, ?, ?, ?, ?)";
            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1, user.getUserLogin().getUserId());
            preparedStatement.setTimestamp(2, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
            preparedStatement.setTimestamp(3, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
            preparedStatement.setInt(4, 1);
            preparedStatement.setInt(5, 1);

            preparedStatement.execute();



        } catch (Exception e) {
            System.out.println("Exception creating user data!");
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        //Create userlogin

        //Create userinfo

        //Create userdecks

        //Create userdata

    }


}
