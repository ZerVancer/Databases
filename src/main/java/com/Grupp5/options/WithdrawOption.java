package com.Grupp5.options;

import com.Grupp5.Application;

import java.io.IOException;

public class WithdrawOption extends Option {
  public WithdrawOption(Application application) {
    super("Withdraw", 2, application);
  }

  @Override
  public void run(String[] parsedInput) throws IOException {
    String amount = parsedInput[1];

    application.getUser().getWallet().setSignifier(-1);
    DepositWithdrawHelper.run(amount, application);
  }
}
