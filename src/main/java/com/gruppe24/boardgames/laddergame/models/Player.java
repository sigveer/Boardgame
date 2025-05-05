package com.gruppe24.boardgames.laddergame.models;

import com.gruppe24.boardgames.commonclasses.CommonPlayer;

/**
 * Class that represents players.
 */
public class Player extends CommonPlayer {

  private boolean frozen = false;

  public Player(String name, int iconIndex) {
    super(name, iconIndex);
  }

  public boolean isFrozen() {
    return frozen;
  }

  public void setFrozen(boolean frozen) {
    this.frozen = frozen;
  }
}
