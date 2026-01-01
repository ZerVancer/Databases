package com.Grupp5;

import java.time.LocalDateTime;

public class Transaction {
  private final int amount;
  private final LocalDateTime timeStamp;

  public Transaction(int amount) {
    this.amount = amount;
    this.timeStamp = LocalDateTime.now();
  }

  public Transaction(int amount, LocalDateTime date) {
    this.amount = amount;
    this.timeStamp = date;
  }

  public int getAmount() { return amount; }
  public LocalDateTime getTimeStamp() { return timeStamp; }
}
