package com.Grupp5.data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
  private UUID transactionID;
  private final int amount;
  private final Timestamp timeStamp;

  public Transaction(int amount) {
    this.transactionID = UUID.randomUUID();
    this.amount = amount;
    this.timeStamp = new Timestamp(System.currentTimeMillis());
  }

  public Transaction(UUID transactionID, int amount, Timestamp timestamp) {
    this.transactionID = transactionID;
    this.amount = amount;
    this.timeStamp = timestamp;
  }

  public UUID getTransactionID() { return transactionID; }
  public int getAmount() { return amount; }
  public Timestamp getTimeStamp() { return timeStamp; }
  public LocalDateTime getLocalTimeDate() { return timeStamp.toLocalDateTime(); }
}
