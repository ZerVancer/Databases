package com.Grupp5;

import java.sql.*;
import java.util.UUID;

public class SQLFunctions {

  public void addTransaction(Connection conSQL, UUID userID, Transaction transaction) {
    try {
      PreparedStatement transactionStatement = conSQL.prepareStatement("INSERT INTO transactions VALUES (?, ?, ?, ?)");
      transactionStatement.setObject(1, transaction.getTransactionID());
      transactionStatement.setInt(2, transaction.getAmount());
      transactionStatement.setObject(3, transaction.getTimeStamp());
      transactionStatement.setObject(4, userID);
      transactionStatement.execute();
      transactionStatement.close();
    } catch (SQLException e) {
      System.out.println("addTransaction");
    }
  }

  public void deleteTransaction(Connection conSQL, UUID transactionID) {
    try {
      PreparedStatement preparedStatement = conSQL.prepareStatement("DELETE FROM transactions WHERE transactionID = ?");
      preparedStatement.setObject(1, transactionID);
      preparedStatement.execute();
      preparedStatement.close();
    } catch (SQLException e) {
      System.out.println("deleteTransaction");
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
      System.out.println("addUser");
    }
  }

  public User getUser(Connection conSQL, String username, String password) {
    UUID userID = null;
    Wallet wallet = new Wallet();
    try {
      PreparedStatement userStatement = conSQL.prepareStatement("SELECT userID FROM users WHERE username = ? AND password = ? ");
      PreparedStatement transactionStatement = conSQL.prepareStatement("SELECT * FROM transactions WHERE userid = ?");
      userStatement.setString(1, username);
      userStatement.setString(2, password);
      ResultSet resultSetUser = userStatement.executeQuery();
      if (resultSetUser.next()) {
        userID = (UUID) resultSetUser.getObject("userID");
        resultSetUser.close();
        transactionStatement.setObject(1, userID);
        ResultSet resultSetTransaction = transactionStatement.executeQuery();
        while (resultSetTransaction.next()) {
          UUID transactionID = (UUID) resultSetTransaction.getObject("transactionID");
          int amount = resultSetTransaction.getInt("amount");
          wallet.addToBalance(amount);
          Timestamp timestamp = (Timestamp) resultSetTransaction.getObject("timestamp");
          wallet.addTransaction(new Transaction(transactionID, amount, timestamp));
        }
      } else return null;
    } catch (SQLException e) {
      System.out.println("getUser");
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
      }
      tables.close();
    } catch (SQLException e) {
      System.out.println("existsUser");
    }
    return false;
  }
}
