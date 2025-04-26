//package com.gruppe24.boardgames.laddergame.controller;
//
//import com.gruppe24.boardgames.laddergame.models.Dice;
//import com.gruppe24.boardgames.laddergame.models.Player;
//import com.gruppe24.utils.Steps;
//
///**
// * Class that handles the game logic and player turns in a text-based format.
// */
//public class TextBasedController extends GameController {
//
//  public static int WinCondition = 90;
//  private final Dice dice;
//
//  /**
//   * Constructor that initializes the game controller with a board type.
//   */
//  public TextBasedController() {
//    this.dice = new Dice(2);
//  }
//
//  /**
//   * Method that checks if a player has won the game.
//   *
//   * @param p the player
//   * @param newPosition the new position of the player
//   * @return true if the player has won, false otherwise
//   */
//  public boolean textBasedCheckAndHandleWin(Player p, int newPosition) {
//    if (newPosition == WinCondition) {
//      System.out.println(p.getColoredName() + " won the game!");
//      return true;
//    }
//    return false;
//  }
//
//  /**
//   * Method that handles the player's turn.
//   */
//  public void textBasedHandlePlayerTurn(Player player) {
//    Steps.pressEnterToContinue();
//    int sumDice = dice.rollSum();
//
//    movePlayer(player, sumDice);
//
//    System.out.println(player.getColoredName() + " rolled " + sumDice);
//    if (getCheckTileType() == 1) {
//      System.out.println("Climbing up!");
//    } else if (getCheckTileType() == 2) {
//      System.out.println("Sliding down...");
//    }
//    System.out.println(player.getColoredName() + " is now on tile " + player.getPosition());
//  }
//}
