package com.Grupp5;

import java.sql.*;

public class Initialisation {
  public void run(){
    String dbName = "postgres";
    String username = "postgres";
    String password = "password";
    String conString = "jdbc:postgresql://localhost/" + dbName;

    {
      try {
        SQLFunctions.conSQL = DriverManager.getConnection(conString, username, password);
        Connection conSQL = SQLFunctions.conSQL;
        Statement statement = conSQL.createStatement();

        createUsersTable(conSQL);
        createTransactionsTable(conSQL);

        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
        System.out.println("All users:");
        while (resultSet.next()) {
          String col1 = resultSet.getString("username");
          System.out.println("- " + col1);
        }
        resultSet.close();
      } catch (SQLException e) {
        System.out.println("Init run");
      }
    }
  }

  private void createTransactionsTable(Connection conSQL) {
    try {
      conSQL.createStatement().execute("CREATE TABLE IF NOT EXISTS transactions(transactionID UUID PRIMARY KEY DEFAULT uuid_generate_v4(), amount int NOT NULL, timestamp TIMESTAMP DEFAULT NOW() NOT NULL, userID UUID, FOREIGN KEY (userID) REFERENCES users(userID))");
    } catch (SQLException e) {
      System.out.println("createTransactionsTable");
    }
  }

  private void createUsersTable(Connection conSQL) {
    try {
      Statement statement = conSQL.createStatement();
      statement.execute("CREATE TABLE IF NOT EXISTS users(userID UUID PRIMARY KEY DEFAULT uuid_generate_v4(), username TEXT NOT NULL UNIQUE, password TEXT NOT NULL)");
      statement.close();
    } catch (SQLException e) {
      System.out.println("createUsersTable");
    }
  }

  private void clearTables(Connection conSQL) throws SQLException {
    try {
      conSQL.createStatement().execute("DROP TABLE transactions; DROP TABLE users");
    } catch (SQLException e) {
      System.out.println("clearTables");
    }
  }
}
