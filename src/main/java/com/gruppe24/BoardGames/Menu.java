package com.gruppe24.BoardGames;

import com.gruppe24.BoardGames.LadderGame.LadderGame;
import com.gruppe24.Utils.Validators;

/**
 * Main menu for the game
 */
public class Menu {


  /**
   * Starts the main menu
   */
  public void start() {
    boolean isRunning = true;
    while (isRunning) {
      System.out.println("BoardGames!");
      System.out.println("[1] Ladder Game");
      System.out.println("[2] Example Game");
      System.out.println("[3] Exit");

      int choice = Validators.promptInt("");

      switch (choice) {
        case 1 -> {
          System.out.println("Starting Ladder Game...");
          LadderGame ladderGame = new LadderGame();
          ladderGame.setUpPlayers();
          ladderGame.play();
        }

        case 2 -> System.out.println("Starting Example Game..."); //Example, remove later with display method
        case 3 -> {
          System.out.println("Exiting...");
          isRunning = false;
        }
      }
    }
  }
}
