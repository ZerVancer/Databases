package com.Grupp5.options;

import com.Grupp5.Application;

import java.io.IOException;

public class QuitOption extends Option {
  public QuitOption(Application application) {
    super("Quit", application);
  }

  @Override
  public void run(String[] parsedInput) throws IOException {
    application.stopRunning();
    System.out.println("System has stopped running");
  }
}
