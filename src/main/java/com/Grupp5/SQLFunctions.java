package com.Grupp5;

import java.sql.*;
import java.util.UUID;

public class SQLFunctions {

  public void addTransaction(Connection conSQL, User username, Transaction transaction) {
    try {
      PreparedStatement transactionStatement = conSQL.prepareStatement("INSERT INTO transactions (amount, timestamp, userID) VALUES (?, ?, ?)");
      PreparedStatement userIDStataement = conSQL.prepareStatement("SELECT userID FROM users WHERE username = ?");
      userIDStataement.setString(1, username.getUsername());
      ResultSet resultSet = userIDStataement.executeQuery();
      if (resultSet.next()) {
        UUID uuid = (UUID) resultSet.getObject("userID");
        resultSet.close();
        transactionStatement.setInt(1, transaction.getAmount());
        transactionStatement.setObject(2, transaction.getTimeStamp());
        transactionStatement.setObject(3, uuid);
        transactionStatement.execute();
        transactionStatement.close();
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void addUser(Connection conSQL, String username) {
    try {
      PreparedStatement preparedStatement = conSQL.prepareStatement("INSERT INTO users (username) VALUES (?)");
      preparedStatement.setString(1, username);
      preparedStatement.execute();
      preparedStatement.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean existsUser(Connection conSQL, String username) {
    try {
      DatabaseMetaData dbm = conSQL.getMetaData();
      ResultSet tables = dbm.getTables(null, null, username, null);
      if (tables.next()) {
        tables.close();
        return true;
      } else {
        tables.close();
        return false;
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
