package database;

import constants.DBConstants;
import models.LoginModel;
import models.RegisterModel;
import models.SecurityModel;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;

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

    public boolean subRegistration() {
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
        return false;
    }


}
