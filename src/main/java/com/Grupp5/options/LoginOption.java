package com.Grupp5.options;

import com.Grupp5.Application;
import com.Grupp5.Input;
import com.Grupp5.SQLFunctions;
import com.Grupp5.User;
import com.Grupp5.menus.LoginMenu;
import com.Grupp5.menus.MainMenu;

import java.io.IOException;

public class LoginOption extends Option{
  public LoginOption(Application application) {
    super("Login", 1, application);
  }

  @Override
  public void run(String[] parsedInput) throws IOException {
    String input = Input.getString();
    if (input.length() - input.replace(" ", "").length() != 1) {
      System.out.println("Only username and password, please: <username> <password>");
      application.setMenu(new LoginMenu(application));
      return;
    }
    String[] inputs = input.split(" ");
    String username = inputs[0];
    String password = inputs[1];
    if (!validUser(username)) {
      application.setMenu(new LoginMenu(application));
      return;
    }
    SQLFunctions sqlFunctions = new SQLFunctions();
    User user = sqlFunctions.getUser(username, password);
    if (user == null) {
      System.out.println("No such user, try again");
      application.setMenu(new LoginMenu(application));
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
