package com.gruppe24.boardgames.laddergame.models;

import com.gruppe24.utils.StyleUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class that represents players.
 */
public class Player {

  private String name;
  public int position;
  private final ImageView playerPiece;
  private Image image;
  private boolean frozen;

  /**
   * Constructor for Player.
   *
   * @param name  name of the player
   * @param image image of the player
   */
  public Player(String name, Image image) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Parameter name cannot be empty");
    }
    if (image == null) {
      throw new IllegalArgumentException("Parameter colour cannot be empty");
    }
    this.name = name;
    this.position = 0;
    this.image = image;
    this.playerPiece = new ImageView(image);
    this.playerPiece.setFitWidth(40);
    this.playerPiece.setFitHeight(40);
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
  public ImageView getPlayerPiece() {
    return playerPiece;
  }

//  /**
//   * Getter-method for the coloured text version of name.
//   *
//   * @return name-variable
//   */
//  public String getColoredName() {
//    if (color == Color.RED) {
//      return StyleUtils.textRed() + name + StyleUtils.textReset();
//    } else if (color == Color.BLUE) {
//      return StyleUtils.textBlue() + name + StyleUtils.textReset();
//    } else {
//      return StyleUtils.textGreen() + name + StyleUtils.textReset();
//    }
//  }

  /**
   * Accessor-method for name.
   *
   * @return name as string.
   */
  public String getName() {
    return name;
  }

  /**
   * Setter-method for name.
   *
   * @param name
   */
  public void setName(String name) {
    this.name = name;
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

  /**
   * Getter-method for Image.
   *
   * @return image-variable
   */
  public Image getImage() {
    return this.image;
  }

  /**
   * Setter-method for Image.
   *
   * @param image new image
   */
  public void setImage(Image image) {
    this.image = image;
  }
}
