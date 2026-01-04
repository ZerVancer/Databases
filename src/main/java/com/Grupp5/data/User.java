package com.Grupp5.data;

import com.Grupp5.SQLFunctions;

import java.util.UUID;

public class User {

  private final UUID uuid;
  private final Wallet wallet;

  public User(UUID uuid) {
    this.uuid = uuid;
    this.wallet = new Wallet();
  }

  public User(UUID uuid, Wallet wallet) {
    this.uuid = uuid;
    this.wallet = wallet;
  }

  public void addTransaction(int amount, int signifier) {
    int result = amount*signifier;
    wallet.addToBalance(result);
    Transaction transaction = new Transaction(result);
    wallet.addTransaction(transaction);
    SQLFunctions sqlFunctions = new SQLFunctions();
    sqlFunctions.addTransaction(uuid, transaction);
  }

  public Wallet getWallet() {
    return wallet;
  }
  public UUID getUUID() {
    return uuid;
  }
}
