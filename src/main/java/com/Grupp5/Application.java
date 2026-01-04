package com.Grupp5;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.UUID;

public class Application {

  public void run(Connection conSQL) {
    SQLFunctions sqlFunctions = new SQLFunctions();

    sqlFunctions.addUser(conSQL, "test1", "test1");

    User user = sqlFunctions.getUser(conSQL, "name", "password");
    System.out.println(user.getUUID());
    System.out.println(new Timestamp(System.currentTimeMillis()));
    Transaction transaction = new Transaction(UUID.randomUUID(), 20, new Timestamp(System.currentTimeMillis()));
    sqlFunctions.addTransaction(conSQL, user.getUUID(), transaction);
    sqlFunctions.deleteTransaction(conSQL, transaction.getTransactionID());
  }
}
