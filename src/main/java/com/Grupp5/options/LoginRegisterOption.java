package com.Grupp5.options;

import com.Grupp5.Application;
import com.Grupp5.data.Input;
import com.Grupp5.SQLFunctions;
import com.Grupp5.data.User;
import com.Grupp5.menus.LoginRegisterMenu;
import com.Grupp5.menus.MainMenu;

import java.io.IOException;

public class LoginRegisterOption extends Option{
  public LoginRegisterOption(Application application) {
    super("Login/Register", application);
  }

  @Override
  public void run(String[] parsedInput) throws IOException {
    String[] inputs = Input.getString().split("\\s+");
    if (inputs.length != 3) {
      System.out.println("Follow this structure please: <login/register> <username> <password>");
      application.setMenu(new LoginRegisterMenu(application));
      return;
    }
    String choice = inputs[0];
    String username = inputs[1];
    String password = inputs[2];
    if (!validUser(username)) {
      application.setMenu(new LoginRegisterMenu(application));
      return;
    }
    SQLFunctions sqlFunctions = new SQLFunctions();
    User user;
    switch (choice.toLowerCase()) {
      case "login":
        user = sqlFunctions.getUser(username, password);
        break;
      case "register":
        if (!sqlFunctions.existsUser(username)) {
          sqlFunctions.addUser(username, password);
          System.out.println("User added");
          application.setMenu(new LoginRegisterMenu(application));
        } else {
          System.out.println("User already exists");
          application.setMenu(new LoginRegisterMenu(application));
        }
        return;
      default:
        System.out.println("Incorrect option, try again");
        application.setMenu(new LoginRegisterMenu(application));
        return;
    }
    if (user == null) {
      System.out.println("No such user, try again");
      application.setMenu(new LoginRegisterMenu(application));
      return;
    }
    application.setUser(user);

    application.setMenu(new MainMenu(application));
  }

  boolean validUser(String username) {
    char[] invalidChars = {'\\', '/', ':', '*', '?', '"', '<', '>', '|', ' '};
    for (int i = 0; i < username.length(); i++)
      for (char c : invalidChars)
        if (username.charAt(i) == c) {
          System.out.println("Invalid character used: '" + c + "'");
          return false;
        }
    return true;
  }
}
