package com.gruppe24.boardgames.laddergame.models;

import com.gruppe24.boardgames.commonclasses.CommonPlayer;

/**
 * Class that represents players.
 */
public class Player extends CommonPlayer {

  private boolean frozen = false;

  /**
   * Constructor for Player.
   *
   * @param name      the name of the player
   * @param iconIndex the index of the player's icon
   */
  public Player(String name, int iconIndex) {
    super(name, iconIndex);
  }

  /**
   * If the player is frozen, they cannot move.
   *
   * @return true if the player is frozen, false otherwise.
   */
  public boolean isFrozen() {
    return frozen;
  }

  /**
   * Sets the frozen state of the player.
   *
   * @param frozen true if the player should be frozen, false otherwise.
   */
  public void setFrozen(boolean frozen) {
    this.frozen = frozen;
  }
}
