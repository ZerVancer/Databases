package com.Grupp5.data;

public class PasswordHasher {
  public static String hash(String s) {
    StringBuilder sb = new StringBuilder();
    for (int c = 0; c < s.length(); c++) {
      int ascii = s.charAt(c);
      ascii = (((ascii+3)*2-20)/5+1)*7;
      sb.append(ascii).append("-");
    }
    return sb.toString();
  }
}
