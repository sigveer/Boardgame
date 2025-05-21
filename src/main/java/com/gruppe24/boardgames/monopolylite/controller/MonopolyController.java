package com.gruppe24.boardgames.monopolylite.controller;

import com.gruppe24.boardgames.commonclasses.CommonGameController;
import com.gruppe24.boardgames.commonclasses.CommonPlayer;
import com.gruppe24.observerpattern.GameSubject;

/**
 * MonopolyController is a class that manages the game logic for Monopoly. It extends the
 * CommonGameController and provides specific implementations for creating players and managing game
 * rules.
 */
public class MonopolyController extends CommonGameController {

  @Override
  protected CommonPlayer createPlayer(String name, int iconIndex) {
    return null;
  }

  @Override
  protected int getMaxPlayers() {
    return 4;
  }

  /**
   * Constructor for MonopolyController.
   *
   * @param numDice     the number of dice used in the game
   * @param gameSubject the game subject for observer pattern
   */
  protected MonopolyController(int numDice, GameSubject gameSubject) {
    super(numDice, gameSubject);
  }
}
