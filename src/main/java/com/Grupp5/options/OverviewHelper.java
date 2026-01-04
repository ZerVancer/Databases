package com.Grupp5.options;

import com.Grupp5.Application;
import com.Grupp5.Period;
import com.Grupp5.PeriodicExpensesAndIncome;

import java.io.IOException;
import java.util.ArrayList;

public class OverviewHelper {

  public static void run(String input, Application application) throws IOException {
    Period period = switch (input.toLowerCase()) {
      case "yearly" -> Period.YEAR;
      case "monthly" -> Period.MONTH;
      case "weekly" -> Period.WEEK;
      case "daily" -> Period.DAY;
      default -> null;
    };
    if (period != null) {
      ArrayList<PeriodicExpensesAndIncome> list = application.getUser().getWallet().getIncomeOrExpenses(period);
      printIncomeExpenses(list, period);
    } else System.out.println("Invalid input, try again");
  }

  private static void printIncomeExpenses(ArrayList<PeriodicExpensesAndIncome> list, Period period) {
    if (period == Period.YEAR) {
      System.out.println("Year | Amount");
      list.forEach(pei -> System.out.println(pei.getYear() + " | " + pei.getAmount()));
    } else {
      System.out.println("Year | " + period.toString() + " | Amount");
      list.forEach(pei -> System.out.println(pei.getYear() + " | " + pei.getPeriod() + " | " + pei.getAmount()));
    }
  }
}
