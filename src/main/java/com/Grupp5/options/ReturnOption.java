package com.Grupp5.options;

import com.Grupp5.Application;
import com.Grupp5.menus.MainMenu;

import java.io.IOException;

public class ReturnOption extends Option {
  public ReturnOption(Application application) {
    super("Return", 1, application);
  }

  @Override
  public void run(String[] parsedInput) throws IOException {
    application.setMenu(new MainMenu(application));
  }
}
