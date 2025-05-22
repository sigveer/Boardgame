package com.gruppe24.boardgames.commonclasses;

import com.gruppe24.exeptions.InvalidPlayerException;
import java.util.Objects;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Abstract model class that represents players.
 */
public abstract class CommonPlayer {

  protected String name;
  protected int position;
  protected ImageView playerPiece;
  protected Image icon;
  protected String iconPath;
  protected int currentIconIndex;

  /**
   * Constructor for the common player.
   *
   * @param name      name of the player
   * @param iconIndex index of the icon
   * @throws InvalidPlayerException if name is null or empty
   */
  protected CommonPlayer(String name, int iconIndex) {
    if (name == null || name.trim().isEmpty()) {
      throw new InvalidPlayerException();
    }
    this.name = name;
    this.position = 0;
    this.currentIconIndex = iconIndex;

    initializePlayerIcon(iconIndex);
  }

  /**
   * Initializes players image by putting an icon on the players piece.
   *
   * @param iconIndex the index of the icon to use
   */
  public void initializePlayerIcon(int iconIndex) {
    String[] iconPaths = getIconPaths();
    if (iconIndex >= iconPaths.length) {
      iconIndex = 0; // Default image index
    }

    this.iconPath = getIconPaths()[iconIndex];
    this.icon = new Image(Objects.requireNonNull(getClass()
        .getClassLoader()
        .getResourceAsStream(this.iconPath)));
    this.playerPiece = new ImageView(icon);
    this.playerPiece.setFitWidth(40);
    this.playerPiece.setFitHeight(40);
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
   * Getter-method for name.
   *
   * @return name as string.
   */
  public String getName() {
    return name;
  }

  /**
   * Setter-method for name.
   *
   * @param name new name
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
    if (position < 0) {
      throw new IllegalArgumentException("Position can not be sub zero");
    }
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
   * @throws InvalidPlayerException if image is null
   */
  public void setIcon(Image image) {
    if (image == null) {
      throw new InvalidPlayerException();
    }
    this.icon = image;
  }

  /**
   * Getter-method for the player piece.
   *
   * @return playerPiece-variable
   */
  public ImageView getPlayerPiece() {
    return playerPiece;
  }

  /**
   * Method to get the current icon path.
   *
   * @return the path of the current icon as a String
   */
  public String getIconPath() {
    return this.iconPath;
  }

  /**
   * Setter-method for iconPath.
   *
   * @param iconPath icon path.
   */
  public void setIconPath(String iconPath) {
    this.iconPath = iconPath;
  }

  /**
   * Method to get the current icon index.
   *
   * @return currentIconIndex
   */
  public int getIconIndex() {
    return currentIconIndex;
  }

  /**
   * Setter-method for current icon index.
   *
   * @param iconIndex icon index.
   */
  public void setCurrentIconIndex(int iconIndex) {
    this.currentIconIndex = iconIndex;
  }
}
