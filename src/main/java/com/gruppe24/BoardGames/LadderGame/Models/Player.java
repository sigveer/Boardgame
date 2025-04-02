package com.gruppe24.BoardGames.LadderGame.Models;

import com.gruppe24.Utils.StyleUtils;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Class that represents players
 */
public class Player {

  //attributes
  private final String name;
  public int position;
  private Circle playerPiece;
  private Color color;
  private boolean frozen;

  //constructor
  public Player(String name, Color color){
    this.name = name;
    this.position = 0;
    this.color = color;
    this.playerPiece = new Circle(25);
    this.playerPiece.setFill(color);
    this.frozen = false;
  }


  /**
   * Check if the player is currently frozen
   *
   * @return true if player is frozen, false otherwise
   */
  public boolean isFrozen() {
    return frozen;
  }


  /**
   * Set the frozen status of the player
   *
   * @param frozen true to freeze the player, false to unfreeze
   */
  public void setFrozen(boolean frozen) {
    this.frozen = frozen;
  }


  public Circle getPlayerPiece(){
    return playerPiece;
  }

  /**
   * Getter-method for the coloured version of name
   *
   * @return name-variable
   *
   * @Author Ingve
   * @Date: 06.02.2025
   * @Version: 1.0
   */
  public String getColoredName(){
    if(color == Color.RED){
      return StyleUtils.textRED() + name + StyleUtils.textRESET();
    }
    else if(color == Color.BLUE){
      return StyleUtils.textBLUE() + name + StyleUtils.textRESET();
    }
    else{
      return StyleUtils.textGREEN() + name + StyleUtils.textRESET();
    }
  }

  /**
   * Accessor-method for name
   * @return name as string
   */
  public String getName(){
    return name;
  }

  /**
   * Getter-method for Position
   *
   * @return position-variable
   *
   * @Author Ingve
   * @Date: 06.02.2025
   * @Version: 1.0
   */
  public int getPosition(){
    return this.position;
  }

  /**
   * Setter-method for Position
   *
   * @param position-variable
   *
   * @Author Ingve
   * @Date: 06.02.2025
   * @Version: 1.0
   */
  public void setPosition(int position){
    this.position = position;
  }
}
