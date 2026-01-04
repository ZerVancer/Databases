package com.Grupp5.menus;

import com.Grupp5.Application;
import com.Grupp5.options.LoginOption;
import com.Grupp5.options.Option;

import java.io.IOException;

public class LoginMenu extends Menu {
  Option[] options = {
      new LoginOption(application)
  };

  public LoginMenu(Application application) {
    super(application);
    setOptions(options);
  }

  public void write() throws IOException {
    System.out.print("Type username and password: ");

    runOption("Login");
  }
}
