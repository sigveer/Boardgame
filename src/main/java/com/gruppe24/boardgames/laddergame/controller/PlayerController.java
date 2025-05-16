package com.gruppe24.boardgames.laddergame.controller;

import com.gruppe24.boardgames.commonclasses.CommonGameController;
import com.gruppe24.boardgames.commonclasses.CommonPlayer;
import com.gruppe24.boardgames.laddergame.models.Player;
import com.gruppe24.observerpattern.GameSubject;

/**
 * PlayerController is a class that manages the players in the game. It handles player creation,
 * removal, icon cycling, and player movement.
 */
public class PlayerController extends CommonGameController {

  @Override
  protected CommonPlayer createPlayer(String name, int iconIndex) {
    return new Player(name, iconIndex);
  }

  @Override
  protected int getMaxPlayers() {
    return 5;
  }

  /**
   * Constructor for PlayerController.
   *
   * @param gameSubject the game subject for observer pattern
   */
  public PlayerController(GameSubject gameSubject) {
    super(2, gameSubject);
    this.winCondition = 90;

    addDefaultPlayer();
  }

  /**
   * Method thats adds a default player.
   */
  private void addDefaultPlayer() {
    addPlayer();
  }
}