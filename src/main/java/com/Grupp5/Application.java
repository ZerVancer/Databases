package com.Grupp5;

import java.sql.Connection;
import java.time.LocalDateTime;

public class Application {

  public void run(Connection conSQL) {
    SQLFunctions sqlFunctions = new SQLFunctions();

    sqlFunctions.addUser(conSQL, "test1");

    User user = new User("test1");
    Transaction transaction = new Transaction(20, LocalDateTime.now());
    sqlFunctions.addTransaction(conSQL, user, transaction);
  }
}
