package com.Grupp5;

public class PeriodicExpensesAndIncome {
  private final int year;
  private int period;
  private final int amount;

  public PeriodicExpensesAndIncome(int year, int period, int amount) {
    this.year = year;
    this.period = period;
    this.amount = amount;
  }

  public PeriodicExpensesAndIncome(int year, int amount) {
    this.year = year;
    this.amount = amount;
  }

  public void setPeriod(int period) {
    this.period = period;
  }

  public int getYear() { return year; }
  public int getPeriod() { return period; }
  public int getAmount() { return amount; }
}
