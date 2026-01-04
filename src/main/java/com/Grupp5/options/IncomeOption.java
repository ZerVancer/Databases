package com.Grupp5.options;

import com.Grupp5.Application;

import java.io.IOException;

public class IncomeOption extends Option {
  public IncomeOption(Application application) {
    super("Income", 2, application);
  }

  @Override
  public void run(String[] parsedInput) throws IOException {
    String period = parsedInput[1];
    application.getUser().getWallet().setSignifier(1);
    OverviewHelper.run(period, application);
  }
}
