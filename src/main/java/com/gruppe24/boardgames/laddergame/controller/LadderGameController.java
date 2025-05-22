package com.gruppe24.boardgames.laddergame.controller;

import com.gruppe24.boardgames.commonclasses.CommonGameController;
import com.gruppe24.boardgames.commonclasses.CommonPlayer;
import com.gruppe24.boardgames.laddergame.models.Player;

/**
 * Controller class that manages the players and game rules in the game "ladderGame". It uses these
 * functionalities:
 * <li>player creation</li>
 * <li>Sets game rules</li>
 */
public class LadderGameController extends CommonGameController {

  @Override
  protected CommonPlayer createPlayer(String name, int iconIndex) {
    return new Player(name, iconIndex);
  }

  /**
   * Constructor for LadderGameController.
   */
  public LadderGameController() {
    setWinCondition(90);
    setMaxPlayers(5);
    addPlayer();
  }
}