package com.Grupp5.menus;

import com.Grupp5.Application;
import com.Grupp5.options.*;

import java.io.IOException;

public class OverviewMenu extends Menu {
  Option[] options = {
      new IncomeOption(application),
      new ExpensesOption(application),
      new ReturnOption(application),
      new QuitOption(application)
  };

  public OverviewMenu(Application application) {
    super(application);
    setOptions(options);
  }

  @Override
  public void write() throws IOException {
    System.out.println("--- Overview ---");
    System.out.println("Choose between income or expenses");
    System.out.println("Periods:");
    System.out.println("Yearly");
    System.out.println("Monthly");
    System.out.println("Weekly");
    System.out.println("Daily");
    System.out.println("Please write your choice like this <income/expenses> <yearly/weekly/monthly/daily>");
    System.out.println("Type return to return to main menu");
  }
}
