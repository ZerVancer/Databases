package com.Grupp5;

import java.sql.Connection;

public class Main {
  public static void main(String[] args) {
    Initialisation init = new Initialisation();
    Connection conSQL = init.run();

    Application application = new Application();
    application.run(conSQL);

    System.out.println("Program ran to completion");
  }
}