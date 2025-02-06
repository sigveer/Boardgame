package com.gruppe24.Utils;

import java.util.Scanner;

/**
 * Class containing utility methods for validating user input.
 */
public class Validators {

  private static Scanner scanner;

  /**
   * Edited by: Sigveer
   * Version: 1.0
   * Date: 31.01.2025
   * Method for getting the scanner object
   *
   * @return the scanner object
   */
  public static Scanner getScanner() {
    if (scanner == null) {
      scanner = new Scanner(System.in);
    }
    return scanner;
  }


  /**
   * Edited by: Sigveer
   * Version: 1.0
   * Date: 31.01.2025
   * Method for ensuring that value is a double
   *
   * @param prompt display the message to user for what to input
   * @return the entered double value
   */
  public static double promptDouble(String prompt) {
    double value;
    while (true) {
      System.out.print(prompt);
      String input = getScanner().nextLine().trim();
      try {
        value = Double.parseDouble(input);
        break;
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a valid number.");
      }
    }
    return value;
  }


  /**
   * Edited by: Sigveer
   * Version: 1.0
   * Date: 31.01.2025
   * Method for ensuring that value is an int
   *
   * @param prompt display the message to user for what to input
   * @return the entered int value
   */
  public static int promptInt(String prompt) {
    int value;
    while (true) {
      System.out.print(prompt);
      String input = getScanner().nextLine().trim();
      try {
        value = Integer.parseInt(input);
        break;
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a valid number.");
      }
    }
    return value;
  }
}



