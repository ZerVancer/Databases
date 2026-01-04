package com.Grupp5;

import com.Grupp5.data.Input;
import com.Grupp5.data.User;
import com.Grupp5.menus.LoginRegisterMenu;
import com.Grupp5.menus.Menu;

import java.io.IOException;

public class Application {
  private boolean running = true;
  private Menu menu;
  private User currentUser;

  public void run() {

    try {
      this.setMenu(new LoginRegisterMenu(this));

      while (this.running) {
        String input = Input.getString();
        this.menu.runOption(input);
      }
    } catch (IOException e) {
      throw new RuntimeException("Menu");
    }
  }

  public void setMenu(Menu menu) throws IOException {
    this.menu = menu;
    this.menu.write();
  }

  public void stopRunning() {
    running = false;
  }

  public void setUser(User user) {
    this.currentUser = user;
  }

  public User getUser() {
    return currentUser;
  }
}
