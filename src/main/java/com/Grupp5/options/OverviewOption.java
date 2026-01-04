package com.Grupp5.options;

import com.Grupp5.Application;
import com.Grupp5.menus.OverviewMenu;

import java.io.IOException;

public class OverviewOption extends Option {
  public OverviewOption(Application application) {
    super("Overview", 1, application);
  }

  @Override
  public void run(String[] parsedInput) throws IOException {
    application.setMenu(new OverviewMenu(application));
  }
}
