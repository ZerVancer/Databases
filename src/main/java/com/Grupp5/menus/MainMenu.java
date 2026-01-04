package com.Grupp5.menus;

import com.Grupp5.Application;
import com.Grupp5.options.*;

import java.util.Arrays;

public class MainMenu extends Menu {
  Option[] options = {
      new BalanceOption(application),
      new DepositOption(application),
      new WithdrawOption(application),
      new OverviewOption(application),
      new LogoutOption(application),
      new QuitOption(application)
  };

  public MainMenu(Application application) {
    super(application);
    setOptions(options);
  }

  public void write() {
    System.out.println("--- Menu ---");
    Arrays.stream(this.options)
        .forEach(option ->
        { if (option.getName().equals("Deposit") || option.getName().equals("Withdraw"))
          System.out.println(option.getName() + " <amount>");
          else System.out.println(option.getName());});
  }
}
