package com.Grupp5;

import java.io.IOException;

public class User {

  private final String username;
  //private final Wallet wallet;

  public User(String username) {
    this.username = username;
    //this.wallet = ufr.getUser(username).getWallet();
  }
//
//  public User(String username, Wallet wallet) {
//    this.username = username;
//    this.wallet = wallet;
//  }
//
//  public void addTransaction(int amount, int signifier) {
//    int result = amount*signifier;
//    wallet.addToBalance(result);
//    wallet.addTransaction(new Transaction(result));
//    ufr.addTransaction(this);
//  }
//
//  public Wallet getWallet() {
//    return wallet;
//  }
  public String getUsername() {
    return username;
  }
}
