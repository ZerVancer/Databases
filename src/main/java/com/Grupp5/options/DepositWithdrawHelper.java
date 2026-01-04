package com.Grupp5.options;

import com.Grupp5.Application;
import com.Grupp5.menus.MainMenu;

import java.io.IOException;

public class DepositWithdrawHelper {
  public static void run(String input, Application application) throws IOException {
    int signifier = application.getUser().getWallet().getSignifier();
    try {
      int amount = Integer.parseInt(input);
      if (amount > 0) {
        application.getUser().addTransaction(amount, signifier);
        if (signifier == 1) {
          System.out.println("Deposited: " + amount);
        }
        else {
          System.out.println("Withdrawn " + amount);
        }
      } else {
        System.out.println("Must be more than zero, try again");
      }
    } catch (NumberFormatException e) {
      System.out.println("Invalid input, try again.");
    }
    application.setMenu(new MainMenu(application));
  }
}
