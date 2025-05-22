package com.gruppe24.boardgames.laddergame.controller;

import com.gruppe24.boardgames.commonclasses.CommonGameController;
import com.gruppe24.boardgames.commonclasses.CommonPlayer;
import com.gruppe24.boardgames.laddergame.models.Player;
import com.gruppe24.observerpattern.GameSubject;

/**
 * Controller class that manages the players in the game "ladderGame". It uses these
 * functionalities:
 * <li>player creation</li>
 * <li>player removal</li>
 * <li>icon cycling</li>,
 * <li>player movement</li>
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
   */
  public PlayerController() {
    setWinCondition(90);

    addDefaultPlayer();
  }

  /**
   * Method thats adds a default player.
   */
  private void addDefaultPlayer() {
    addPlayer();
  }
}