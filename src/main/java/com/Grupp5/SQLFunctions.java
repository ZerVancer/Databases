package com.Grupp5;

import java.sql.*;
import java.util.UUID;

public class SQLFunctions {

  public void addTransaction(Connection conSQL, UUID userID, Transaction transaction) {
    try {
      PreparedStatement transactionStatement = conSQL.prepareStatement("INSERT INTO transactions (amount, timestamp, userID) VALUES (?, ?, ?)");
      transactionStatement.setInt(1, transaction.getAmount());
      transactionStatement.setObject(2, transaction.getTimeStamp());
      transactionStatement.setObject(3, userID);
      transactionStatement.execute();
      transactionStatement.close();
    } catch (SQLException e) {
      System.out.println("E");
    }
  }

  public void addUser(Connection conSQL, String username, String password) {
    try {
      PreparedStatement preparedStatement = conSQL.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
      preparedStatement.setString(1, username);
      preparedStatement.setString(2, password);
      preparedStatement.execute();
      preparedStatement.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public User getUser(Connection conSQL, String username, String password) {
    UUID userID;
    Wallet wallet = new Wallet();
    try {
      PreparedStatement userStatement = conSQL.prepareStatement("SELECT userID FROM users WHERE username = ? AND password = ? ");
      PreparedStatement transactionStatement = conSQL.prepareStatement("SELECT amount, timestamp FROM transactions WHERE userid = ?");
      userStatement.setString(1, username);
      userStatement.setString(2, password);
      ResultSet resultSetUser = userStatement.executeQuery();
      if (resultSetUser.next()) {
        userID = (UUID) resultSetUser.getObject("userID");
        resultSetUser.close();
        transactionStatement.setObject(1, userID);
        ResultSet resultSetTransaction = transactionStatement.executeQuery();
        while (resultSetTransaction.next()) {
          int amount = resultSetTransaction.getInt("amount");
          wallet.addToBalance(amount);
          Timestamp timestamp = (Timestamp) resultSetTransaction.getObject("timestamp");
          wallet.addTransaction(new Transaction(amount, timestamp));
        }
      } else return null;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return new User(userID, username, wallet);
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
