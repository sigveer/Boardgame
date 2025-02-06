package com.gruppe24.Utils;

import java.util.Scanner;

/**
 * Class containing utility methods for steps in the game.
 */
public class Steps {

  /**
   * @Author Sigveer
   * Version: 1.0
   * Date: 06.02.2025
   *
   * Method for pausing the game and waiting for the user to press Enter.
   */
  public static void pressEnterToContinue() {
    System.out.println("Press Enter to continue...");
    Scanner scanner = new Scanner(System.in);
    scanner.nextLine();
  }
}
