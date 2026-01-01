package com.Grupp5;

import java.sql.*;

public class Initialisation {
  public Connection run(){
    String dbName = "postgres";
    String username = "postgres";
    String password = "password";
    String conString = "jdbc:postgresql://localhost/" + dbName;
    Connection conSQL;

    {
      try {
        System.out.println(1);
        conSQL = DriverManager.getConnection(conString, username, password);
        Statement statement = conSQL.createStatement();

        clearTables(conSQL);

        System.out.println(2);
        createUserTable(conSQL);
        createTransactionTable(conSQL);
        System.out.println(3);

        PreparedStatement preparedStatement = conSQL.prepareStatement("INSERT INTO users (username) VALUES (?)");
        preparedStatement.setString(1, "name");
        System.out.println(4);

        preparedStatement.execute();
        preparedStatement.close();
        System.out.println(5);

        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
        while (resultSet.next()) {
          String col1 = resultSet.getString("username");
          System.out.println(col1);
        }
        resultSet.close();
        System.out.println(6);

        return conSQL;
      } catch (SQLException e) {
        System.out.println("No name");
      }
    }
    return null;
  }

  private void createTransactionTable(Connection conSQL) {
    try {
      System.out.println(11);
      conSQL.createStatement().execute("CREATE TABLE IF NOT EXISTS transactions(transactionID UUID PRIMARY KEY DEFAULT uuid_generate_v4(), amount int, timestamp TIMESTAMP DEFAULT NOW(), userID UUID REFERENCES users(userID))");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private void createUserTable(Connection conSQL) {
    try {
      System.out.println(12);
      Statement statement = conSQL.createStatement();
      statement.execute("CREATE TABLE IF NOT EXISTS users(userID UUID PRIMARY KEY DEFAULT uuid_generate_v4(), username TEXT, balance int DEFAULT 0)");
      statement.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private void clearTables(Connection conSQL) throws SQLException {
    conSQL.createStatement().execute("DROP TABLE transactions; DROP TABLE users");
  }
}
