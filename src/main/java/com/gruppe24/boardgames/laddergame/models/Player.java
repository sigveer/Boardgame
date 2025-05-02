package com.gruppe24.boardgames.laddergame.models;

import com.gruppe24.boardgames.commonclasses.AbstractPlayer;
import java.util.Objects;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class that represents players.
 */
public class Player extends AbstractPlayer {

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
