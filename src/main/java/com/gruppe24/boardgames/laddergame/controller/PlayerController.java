package com.gruppe24.boardgames.laddergame.controller;

import com.gruppe24.boardgames.laddergame.models.Dice;
import com.gruppe24.boardgames.laddergame.models.Player;
import com.gruppe24.observerpattern.EventType;
import com.gruppe24.observerpattern.GameSubject;
import java.util.ArrayList;
import java.util.List;

/**
 * PlayerController is a class that manages the players in the game.
 * It handles player creation, removal, icon cycling, and player movement.
 */
public class PlayerController {
  private final List<Player> players;
  private final Dice dice;
  private final BoardController boardController;
  private final GameSubject gameSubject;

  /**
   * Constructor for PlayerController.
   *
   * @param boardController the board controller
   */
  public PlayerController(BoardController boardController, GameSubject gameSubject) {
    this.players = new ArrayList<>();
    this.dice = new Dice(2);
    this.boardController = boardController;
    this.gameSubject = gameSubject;

    addDefaultPlayer();
  }

  /**
   * Method for adding a player to playermenu.
   */
  public void addPlayer() {
    if (players.size() >= 5) {
      return;
    }
    Player newPlayer = new Player("Player " + (players.size() + 1), getNextIconIndex());
    players.add(newPlayer);

    gameSubject.notifyObservers(EventType.PLAYER_ADDED, newPlayer);
  }

  /**
   * Method that removes the last player in the playermenu.
   */
  public void removePlayer() {
    if (players.size() > 1) {
      Player removedPlayer = players.removeLast();

      gameSubject.notifyObservers(EventType.PLAYER_REMOVED, removedPlayer);
    }
  }

  /**
   * Method that gets the players.
   *
   * @return the players
   */
  public List<Player> getPlayers() {
    return players;
  }

  /**
   * Method for cycling the player icon.
   *
   * @param index the index of the player in the list
   */
  public void cyclePlayerIcon(int index) {
    if (index >= 0 && index < players.size()) {
      Player player = players.get(index);
      int oldIconIndex = player.getIconIndex();
      player.cycleToNextIcon();

      gameSubject.notifyObservers(EventType.PLAYER_ICON_CHANGED, player, player.getIconIndex());
    }
  }

  /**
   * Method that handles the player's turn.
   */
  public void handlePlayerTurn(Player player, int diceValue) {
    movePlayer(player, diceValue);

    gameSubject.notifyObservers(EventType.DICE_ROLLED, player, diceValue);
  }

  /**
   * Method that gets the next icon for the player.
   *
   * @return the next icon.
   */
  private int getNextIconIndex() {
    int iconIndex = 0;
    for (Player player : players) {
      if (player.getIconIndex() == iconIndex) {
        iconIndex++;
      }
    }
    return iconIndex;
  }

  /**
   * Method thats adds a default player.
   */
  private void addDefaultPlayer() {
    addPlayer();
  }

  /**
   * Method that moves the player.
   *
   * @param sumDice the sum of the dice
   */
  public void movePlayer(Player player, int sumDice) {
    int newPosition = player.getPosition() + sumDice;
    newPosition = boardController.handleOvershoot(newPosition);
    player.setPosition(newPosition);
    boardController.handleTileAction(player, newPosition);
  }


}