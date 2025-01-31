package com.gruppe24.BoardGame;

import com.gruppe24.Utils.Validators;

public class MainMenu {

  public void start() {
    boolean isRunning = true;
    while (isRunning) {
      System.out.println("BoardGames!");
      System.out.println("[1] Ladder Game");
      System.out.println("[2] Example Game");
      System.out.println("[3] Exit");

      int choice = Validators.promptInt("");

      switch (choice) {
        case 1 -> System.out.println("Starting Ladder Game..."); //Example, remove later with display method
        case 2 -> System.out.println("Starting Example Game..."); //Example, remove later with display method
        case 3 -> {
          System.out.println("Exiting...");
          isRunning = false;
        }
      }
    }
  }
}
