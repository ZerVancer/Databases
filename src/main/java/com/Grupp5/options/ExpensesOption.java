package com.Grupp5.options;

import com.Grupp5.Application;

import java.io.IOException;

public class ExpensesOption extends Option {
  public ExpensesOption(Application application) {
    super("Expenses", 2, application);
  }

  @Override
  public void run(String[] parsedInput) throws IOException {
    application.getUser().getWallet().setSignifier(-1);
    OverviewHelper.run(parsedInput[1], application);
  }
}
