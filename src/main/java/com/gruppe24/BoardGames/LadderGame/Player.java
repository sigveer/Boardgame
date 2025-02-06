package com.gruppe24.BoardGames.LadderGame;

public class Player {

  //attributes
  private String name;
  private int ID;
  private int position;

  public Player(String name, int position){
    this.name = name;
    this.position = position;
    this.ID++;
  }

  //methods
  public String getName(){
    return name;
  }

  public int getID(){
    return ID;
  }

  public int getPosition(){
    return position;
  }

  public void setPosition(int position){
    this.position = position;
  }


  public void move(int steps) {
    this.position += steps;
  }
}
