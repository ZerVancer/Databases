package com.Grupp5;

import java.util.UUID;

public class User {

  private final UUID uuid;
  private final String username;
  private final Wallet wallet;

  public User(UUID uuid, String username) {
    this.uuid = uuid;
    this.username = username;
    this.wallet = new Wallet();
  }

  public User(UUID uuid, String username, Wallet wallet) {
    this.uuid = uuid;
    this.username = username;
    this.wallet = wallet;
  }

//  public void addTransaction(int amount, int signifier) {
//    int result = amount*signifier;
//    wallet.addToBalance(result);
//    wallet.addTransaction(new Transaction(result));
//    ufr.addTransaction(this);
//  }
//
  public Wallet getWallet() {
    return wallet;
  }
  public String getUsername() {
    return username;
  }
  public UUID getUUID() {
    return uuid;
  }
}
