package com.Grupp5.menus;

import com.Grupp5.Application;
import com.Grupp5.options.LoginRegisterOption;
import com.Grupp5.options.Option;

import java.io.IOException;

public class LoginRegisterMenu extends Menu {
  Option[] options = {
      new LoginRegisterOption(application)
  };

  public LoginRegisterMenu(Application application) {
    super(application);
    setOptions(options);
  }

  public void write() throws IOException {
    System.out.print("Type login or register followed by username and password: ");

    runOption("Login/Register");
  }
}
