package com.Grupp5.options;

import com.Grupp5.Application;

import java.io.IOException;

public class DepositOption extends Option {
  public DepositOption(Application application) { super("Deposit" , application); }

  @Override
  public void run(String[] parsedInput) throws IOException {
    String amount = parsedInput[1];

    application.getUser().getWallet().setSignifier(1);
    DepositWithdrawHelper.run(amount, application);
  }
}
