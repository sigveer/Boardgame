package com.gruppe24.Utils;

import java.util.Scanner;

public class Steps {

  public static void pressEnterToContinue() {
    System.out.println("Press Enter to continue...");
    Scanner scanner = new Scanner(System.in);
    scanner.nextLine();
  }
}
