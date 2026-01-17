package com.Grupp5;

import com.Grupp5.data.*;

import java.sql.*;
import java.util.UUID;

public class SQLFunctions {
  public static Connection conSQL;

  public void addTransaction(UUID userID, Transaction transaction) {
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

  public void deleteTransaction(UUID transactionID) {
    try {
      PreparedStatement preparedStatement = conSQL.prepareStatement("DELETE FROM transactions WHERE transactionID = ?");
      preparedStatement.setObject(1, transactionID);
      preparedStatement.execute();
      preparedStatement.close();
    } catch (SQLException e) {
      System.out.println("deleteTransaction");
    }
  }

  public void addUser(String username, String password) {
    try {
      PreparedStatement preparedStatement = conSQL.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
      preparedStatement.setString(1, username);
      preparedStatement.setString(2, PasswordHasher.hash(password));
      preparedStatement.execute();
      preparedStatement.close();
    } catch (SQLException e) {
      System.out.println("addUser");
    }
  }

  public User getUser(String username, String password) {
    UUID userID = null;
    Wallet wallet = new Wallet();
    try {
      PreparedStatement preparedStatement = conSQL.prepareStatement("SELECT users.userid, transactions.* FROM users LEFT JOIN transactions ON users.userID = transactions.userID WHERE users.username = ? AND users.password = ?");
      preparedStatement.setString(1, username);
      preparedStatement.setString(2, PasswordHasher.hash(password));
      ResultSet resultSet = preparedStatement.executeQuery();
      if (!resultSet.next()) return null;
      userID = (UUID) resultSet.getObject("userID");

      if (resultSet.getObject("transactionID") != null) {
        do {
          UUID transactionID = (UUID) resultSet.getObject("transactionID");
          int amount = resultSet.getInt("amount");
          wallet.addToBalance(amount);
          Timestamp timestamp = (Timestamp) resultSet.getObject("timestamp");
          wallet.addTransaction(new Transaction(transactionID, amount, timestamp));
        } while (resultSet.next());
      }
      System.out.println(wallet.getTransactions().size());
      preparedStatement.close();
      resultSet.close();
    } catch (SQLException e) {
      System.out.println("getUser");
    }
    return new User(userID, wallet);
  }

  public boolean existsUser(String username) {
    try {
      PreparedStatement preparedStatement = conSQL.prepareStatement("SELECT username FROM users WHERE username = ?");
      preparedStatement.setString(1, username);
      ResultSet resultSet = preparedStatement.executeQuery();
      return resultSet.next();
    } catch (SQLException e) {
      throw new RuntimeException();
    }
  }
}
