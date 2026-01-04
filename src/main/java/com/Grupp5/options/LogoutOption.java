package com.Grupp5.options;

import com.Grupp5.Application;
import com.Grupp5.menus.LoginRegisterMenu;

import java.io.IOException;

public class LogoutOption extends Option{
  public LogoutOption(Application application) {
    super("Logout", application);
  }

  @Override
  public void run(String[] parsedInput) throws IOException {
    application.setUser(null);
    application.setMenu(new LoginRegisterMenu(application));
  }
}
