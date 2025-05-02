package com.gruppe24.boardgames.commonclasses;

import java.util.Objects;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Abstract class that represents players.
 */
public abstract class AbstractPlayer {

  protected String name;
  protected int position;
  protected ImageView playerPiece;
  protected Image icon;
  protected String iconPath;
  protected int currentIconIndex;

  /**
   * Constructor for the player.
   *
   * @param name      name of the player
   * @param iconIndex index of the icon
   */
  protected AbstractPlayer(String name, int iconIndex) {

    this.name = name;
    this.position = 0;
    this.currentIconIndex = iconIndex;

    initializePlayerIcon(iconIndex);
  }

  /**
   * Getter-method for the color of the player.
   */
  protected void initializePlayerIcon(int iconIndex) {
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
   * Getter-method for the player piece.
   *
   * @return playerPiece-variable
   */
  public ImageView getPlayerPiece() {
    return playerPiece;
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

  /**
   * Method to get the current icon index.
   *
   * @return currentIconIndex
   */
  public int getIconIndex() {
    return currentIconIndex;
  }

}
