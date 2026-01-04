package com.Grupp5;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Transaction {
  private final int amount;
  private final Timestamp timeStamp;

  public Transaction(int amount) {
    this.amount = amount;
    this.timeStamp = new Timestamp(System.currentTimeMillis());
  }

  public Transaction(int amount, Timestamp timestamp) {
    this.amount = amount;
    this.timeStamp = timestamp;
  }

  public int getAmount() { return amount; }
  public Timestamp getTimeStamp() { return timeStamp; }
  public LocalDateTime getLocalTimeDate() { return timeStamp.toLocalDateTime(); }
}
