package com.gruppe24.boardgames.laddergame.controller;

import com.gruppe24.boardgames.laddergame.models.Dice;
import com.gruppe24.boardgames.laddergame.models.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.scene.image.Image;

/**
 * PlayerController is a class that manages the players in the game.
 * It handles player creation, removal, icon cycling, and player movement.
 */
public class PlayerController {
  private List<Player> players;
  private Dice dice;
  private BoardController boardController;

  /**
   * Constructor for PlayerController.
   *
   * @param boardController the board controller
   */
  public PlayerController(BoardController boardController) {
    this.players = new ArrayList<>();
    this.dice = new Dice(2);
    this.boardController = boardController;
    addDefaultPlayer();
  }

  /**
   * Method for adding a player to playermenu.
   */
  public void addPlayer() {
    if (players.size() >= 5)
      return;
    players.add(new Player("Player " + (players.size() + 1), getNextIcon()));
  }

  /**
   * Method that removes the last player in the playermenu.
   */
  public void removePlayer() {
    if (players.size() > 1) {
      players.removeLast();
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
   * @param player the player to cycle the icon for
   * @param index the index of the player in the list
   */
  public void cyclePlayerIcon(Player player, int index) {
    if (index >= 0 && index < players.size()) {
      players.get(index).cycleToNextIcon();
    }
  }

  /**
   * Method that handles the player's turn.
   */
  public void handlePlayerTurn(Player player, int diceValue) {
    movePlayer(player, diceValue);
  }

  /**
   * Method that gets the next icon for the player.
   *
   * @return the next icon.
   */
  private Image getNextIcon() {
    String[] iconPaths = Player.getIconPaths();
    String selectedPath = iconPaths[players.size() % iconPaths.length];
    return new Image(
        Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(selectedPath)));
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