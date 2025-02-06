package com.gruppe24.BoardGames.LadderGame;

import java.util.List;

public class Game implements TileAction{

  //attributes
  private final Board board;
  private final Dice dice;
  private final List<Player> players;


  public Game(Board board, Dice dice, List<Player> players){
    this.board = board;
    this.dice = dice;
    this.players = players;
  }

  //methods
  


  @Override
  public void perform(Player player){

  }
}
