package com.gruppe24.utils;

import java.util.Scanner;
import java.util.logging.Logger;
import javafx.scene.paint.Color;

/**
 * Class containing utility methods for validating user input.
 */
public class Validators {

  private static Scanner scanner;

  /**
   * Method for getting the scanner object.
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
   * Scanner for string.
   *
   * @return string
   */
  public static String scannerString() {
    return scanner.nextLine();
  }

  /**
   * Method for ensuring that value is an int.
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

  /**
   * Method for choosing a color.
   *
   * @param prompt the color choice
   */
  public static Color colorChoice(String prompt) {
    while (true) {
      switch (prompt) {
        case "R" -> {
          return Color.RED;
        }
        case "B" -> {
          return Color.BLUE;
        }
        case "G" -> {
          return Color.GREEN;
        }
      }

    }
  }

  /**
   * Logger method for logging messages.
   *
   * @return the logger object
   */
  public static Logger getLogger() {
    return Logger.getLogger(Validators.class.getName());
  }
}



