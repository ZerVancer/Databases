package com.Grupp5.options;

import com.Grupp5.Application;
import com.Grupp5.menus.MainMenu;

import java.io.IOException;

public class BalanceOption extends Option {
  public BalanceOption(Application application) {
    super("Balance", 1, application);
  }

  @Override
  public void run(String[] parsedInput) throws IOException {
    System.out.println("Balance: " + application.getUser().getWallet().getBalance());

    application.setMenu(new MainMenu(application));
  }
}
