package database;

import constants.DBConstants;
import models.RegisterModel;

import java.sql.*;

public class UserLoginDB {

    private static final String DB_URL = DBConstants.DB_URL;
    private static final String USER = DBConstants.USER;
    private static final String PASS = DBConstants.PASS;

    private RegisterModel registerModel;

    public UserLoginDB(RegisterModel registerModel) {
        this.registerModel = registerModel;
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


}
