package com.gruppe24.boardgames.laddergame.models;

import com.gruppe24.utils.StyleUtils;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Class that represents players.
 */
public class Player {

  private final String name;
  public int position;
  private final Circle playerPiece;
  private final Color color;
  private boolean frozen;

  /**
   * Constructor for Player.
   *
   * @param name  name of the player
   * @param color color of the player
   */
  public Player(String name, Color color) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Parameter name cannot be empty");
    }
    if (color == null) {
      throw new IllegalArgumentException("Parameter colour cannot be empty");
    }
    this.name = name;
    this.position = 0;
    this.color = color;
    this.playerPiece = new Circle(25);
    this.playerPiece.setFill(color);
    this.frozen = false;
  }

  /**
   * Check if the player is currently frozen.
   *
   * @return true if player is frozen, false otherwise
   */
  public boolean isFrozen() {
    return frozen;
  }

  /**
   * Set the frozen status of the player.
   *
   * @param frozen true to freeze the player, false to unfreeze
   */
  public void setFrozen(boolean frozen) {
    this.frozen = frozen;
  }

  /**
   * Getter-method for the color of the player.
   *
   * @return color-variable
   */
  public Circle getPlayerPiece() {
    return playerPiece;
  }

  /**
   * Getter-method for the coloured version of name.
   *
   * @return name-variable
   */
  public String getColoredName() {
    if (color == Color.RED) {
      return StyleUtils.textRed() + name + StyleUtils.textReset();
    } else if (color == Color.BLUE) {
      return StyleUtils.textBlue() + name + StyleUtils.textReset();
    } else {
      return StyleUtils.textGreen() + name + StyleUtils.textReset();
    }
  }

  /**
   * Accessor-method for name.
   *
   * @return name as string.
   */
  public String getName() {
    return name;
  }

  /**
   * Getter-method for Position.
   *
   * @return position-variable
   */
  public int getPosition() {
    return this.position;
  }

  /**
   * Setter-method for Position.
   *
   * @param position new position
   */
  public void setPosition(int position) {
    this.position = position;
  }
}
