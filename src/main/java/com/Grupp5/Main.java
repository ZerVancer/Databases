package com.Grupp5;

import java.sql.Connection;

public class Main {
  public static void main(String[] args) {
    new Initialisation().run();

    Application application = new Application();
    application.run();

    System.out.println("Program ran to completion");
  }
}