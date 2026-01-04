package com.Grupp5;

import java.sql.Connection;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Application {

  public void run(Connection conSQL) {
    SQLFunctions sqlFunctions = new SQLFunctions();

    sqlFunctions.addUser(conSQL, "test1", "test1");

    User user = sqlFunctions.getUser(conSQL, "name", "password");
    Transaction transaction = new Transaction(20, new Timestamp(System.currentTimeMillis()));
    sqlFunctions.addTransaction(conSQL, user.getUUID(), transaction);
  }
}
