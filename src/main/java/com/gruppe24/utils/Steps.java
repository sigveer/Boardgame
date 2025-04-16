package com.gruppe24.utils;

import java.util.Scanner;

/**
 * Class containing utility methods for steps in the text based game.
 */
public class Steps {

  /**
   * Method for pausing the game and waiting for the user to press Enter.
   */
  public static void pressEnterToContinue() {
    System.out.println("Press Enter to continue...");
    Scanner scanner = new Scanner(System.in);
    scanner.nextLine();
  }
}
