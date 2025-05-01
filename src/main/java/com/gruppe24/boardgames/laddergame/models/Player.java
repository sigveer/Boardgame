package com.gruppe24.boardgames.laddergame.models;

import java.util.Objects;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class that represents players.
 */
public class Player {

  private String name;
  public int position;
  private ImageView playerPiece;
  private Image icon;
  private String iconPath;
  private int currentIconIndex = 0;
  private boolean frozen = false;


  /**
   * Constructor for Player.
   *
   * @param name      name of the player
   * @param iconIndex icon of the player
   */
  public Player(String name, int iconIndex) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Parameter name cannot be empty");
    }
    if (iconIndex < 0) {
      throw new IllegalArgumentException("Parameter iconIndex cannot be negative");
    }
    String[] iconPaths = getIconPaths();
    if (iconIndex >= iconPaths.length) {
      iconIndex = 0; // Default image index (Mario)
    }

    this.name = name;
    this.position = 0;
    this.currentIconIndex = iconIndex;
    this.iconPath = getIconPaths()[iconIndex];
    this.icon = new Image(Objects.requireNonNull(getClass()
        .getClassLoader()
        .getResourceAsStream(this.iconPath)));
    this.playerPiece = new ImageView(icon);
    this.playerPiece.setFitWidth(40);
    this.playerPiece.setFitHeight(40);
    this.frozen = false;
  }

  /**
   * Getter-method for the color of the player.
   *
   * @return color-variable
   */
  public ImageView getPlayerPiece() {
    return playerPiece;
  }

  public void initializePlayerPiece(Image image) {
    ImageView playerPiece = new ImageView(image);
    playerPiece.setFitWidth(50);
    playerPiece.setFitHeight(50);
    playerPiece.setPreserveRatio(true);
    this.playerPiece = playerPiece;
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
  public Image getIcon() {
    return this.icon;
  }

  /**
   * Setter-method for Image.
   *
   * @param image new image
   */
  public void setIcon(Image image) {
    if (image == null) {
      throw new IllegalArgumentException("Parameter image cannot be empty");
    }
    this.icon = image;
  }

  /**
   * Method to get the icon paths.
   *
   * @return array of icon paths
   */
  public static String[] getIconPaths() {
    return new String[]{
        "pictures/pngIcons/mario.png",
        "pictures/pngIcons/luigi.png",
        "pictures/pngIcons/wario.png",
        "pictures/pngIcons/waluigi.png",
        "pictures/pngIcons/donkeykong.png"
    };
  }

  /**
   * Method to cycle to the next icon.
   */
  public void cycleToNextIcon() {
    String[] paths = getIconPaths();
    currentIconIndex = (currentIconIndex + 1) % paths.length;
    String nextPath = paths[currentIconIndex];
    this.iconPath = nextPath;

    Image newIcon = new Image(
        Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(nextPath)));
    this.setIcon(newIcon);
    this.playerPiece.setImage(newIcon);
  }

//  /**
//   * Method for getting the current icon path.
//   *
//   * @return iconPath
//   */
//  public String getIconPath() {
//    return iconPath;
//  }
//
//  /**
//   * Method for setting the icon path.
//   *
//   * @param iconPath the new icon path
//   */
//  public void setIconPath(String iconPath) {
//    this.iconPath = iconPath;
//  }

  /**
   * Method to get the current icon index.
   *
   * @return currentIconIndex
   */
  public int getIconIndex() {
    return currentIconIndex;
  }

  public boolean isFrozen() {
    return frozen;
  }

  public void setFrozen(boolean frozen) {
    this.frozen = frozen;
  }
}
