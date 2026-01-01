package com.Grupp5;

import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;

public class Wallet {
  private int balance;
  private ArrayList<Transaction> transactions = new ArrayList<>();
  private int signifier = 1;

  public Wallet() {
    this.balance = 0;
  }

  public Wallet(int balance) {
    this.balance = balance;
  }

  public void addTransaction(Transaction transaction) {
    transactions.add(transaction);
  }

  // Very convoluted
  public ArrayList<PeriodicExpensesAndIncome> getIncomeOrExpenses(Period period) {
    ArrayList<PeriodicExpensesAndIncome> result = new ArrayList<>();
    if (transactions.isEmpty()) return result;
    PeriodicExpensesAndIncome pei;
    int time = getTransactionPeriod(transactions.getFirst(), period);
    int total = 0;
    int year = -1;

    for (Transaction transaction : getTransactions()) {
      int amount = transaction.getAmount();
      year = transaction.getTimeStamp().getYear();
      if (amount*signifier > 0) {
        if (getTransactionPeriod(transaction, period) == time && transaction.getTimeStamp().getYear() == year) {
          total += transaction.getAmount();
        } else {
          pei = new PeriodicExpensesAndIncome(year, total);
          if (period != Period.YEAR) {
            pei.setPeriod(time);
          }
          result.add(pei);
          time = getTransactionPeriod(transaction, period);
          total = amount;
        }
      }
    }
    pei = new PeriodicExpensesAndIncome(year, total);
    if (period != Period.YEAR) {
      pei.setPeriod(time);
    }
    result.add(pei);
    return result;
  }

  private int getTransactionPeriod(Transaction transaction, Period period) {
    return switch (period) {
      case YEAR -> transaction.getTimeStamp().getYear();
      case MONTH -> transaction.getTimeStamp().getMonthValue();
      case WEEK -> {
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        yield transaction.getTimeStamp().get(woy);
      }
      case DAY -> transaction.getTimeStamp().getDayOfYear();
    };
  }

  public void addToBalance(int amount) {
    balance += amount;
  }
  public void setTransactions(ArrayList<Transaction> transactions) {
    this.transactions = transactions;
  }
  public void setSignifier(int signifier) { this.signifier = signifier; }

  public int getBalance() { return balance; }
  public ArrayList<Transaction> getTransactions() { return new ArrayList<>(transactions); }
  public int getSignifier() { return signifier; }
}
