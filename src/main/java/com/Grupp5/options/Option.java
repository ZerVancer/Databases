package com.Grupp5.options;


import com.Grupp5.Application;

import java.io.IOException;

public abstract class Option {
  private final String name;
  protected Application application;
  protected int numberOfCommands;

  protected Option(String name, int numberOfCommands, Application application) {
    this.name = name;
    this.numberOfCommands = numberOfCommands;
    this.application = application;
  }

  public abstract void run(String[] parsedInput) throws IOException;

  public String getName() {
    return name;
  }

  public int getNumberOfCommands() {
    return numberOfCommands;
  }
}
