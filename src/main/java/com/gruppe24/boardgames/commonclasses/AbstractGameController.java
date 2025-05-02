package com.gruppe24.boardgames.commonclasses;

import com.gruppe24.boardgames.laddergame.controller.BoardController;
import com.gruppe24.boardgames.laddergame.models.Dice;
import com.gruppe24.boardgames.laddergame.models.Player;
import com.gruppe24.boardgames.laddergame.models.board.Board;
import com.gruppe24.boardgames.laddergame.models.board.tiles.Tile;
import com.gruppe24.observerpattern.EventType;
import com.gruppe24.observerpattern.GameSubject;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGameController {

  protected List<AbstractPlayer> players;
  protected Dice dice;
  protected GameSubject gameSubject;
  protected int WinCondition;
  private Board currentBoard;
  private int checkTileType = 0;
  private int specialTilePosition;

  protected AbstractGameController(int numDice, GameSubject gameSubject) {
    this.players = new ArrayList<>();
    this.dice = new Dice(numDice);
    this.gameSubject = gameSubject;

  }

  /**
   * Initializes the game with the board controller. This must be called before starting the game.
   *
   * @param boardController the board controller to use for this game
   */
  public void initializeGame(BoardController boardController) {
    if (boardController == null) {
      throw new IllegalArgumentException("BoardController cannot be null");
    }
    this.currentBoard = boardController.getBoard();
  }

  protected abstract AbstractPlayer createPlayer(String name, int iconIndex);

  protected abstract int getMaxPlayers();

  /**
   * Method for adding a player to playermenu.
   */
  public void addPlayer() {
    if (players.size() >= getMaxPlayers()) {
      return;
    }
    AbstractPlayer newPlayer = createPlayer("Player " + (players.size() + 1), getNextIconIndex());
    players.add(newPlayer);

    gameSubject.notifyListener(EventType.PLAYER_ADDED, newPlayer);
  }

  /**
   * Method that removes the last player in the playermenu.
   */
  public void removePlayer() {
    if (players.size() > 1) {
      AbstractPlayer removedPlayer = players.removeLast();

      gameSubject.notifyListener(EventType.PLAYER_REMOVED, removedPlayer);
    }
  }

  /**
   * Method that gets the players.
   *
   * @return the players
   */
  public List<Player> getPlayers() {
    // Create a new list with the correct type
    List<Player> playerList = new ArrayList<>();
    for (AbstractPlayer abstractPlayer : players) {
      if (abstractPlayer instanceof Player) {
        playerList.add((Player) abstractPlayer);
      }
    }
    return playerList;
  }


  /**
   * Sets the player list and returns the updated list.
   *
   * @param players the new list of players
   * @return the updated list of players
   */
  public List<Player> setPlayers(List<Player> players) {
    this.players.clear();

    this.players.addAll(players);

    return getPlayers();
  }

  /**
   * Method for cycling the player icon.
   *
   * @param index the index of the player in the list
   */
  public void cyclePlayerIcon(int index) {
    if (index >= 0 && index < players.size()) {
      AbstractPlayer player = players.get(index);
      int oldIconIndex = player.getIconIndex();
      player.cycleToNextIcon();

      gameSubject.notifyListener(EventType.PLAYER_ICON_CHANGED, player, player.getIconIndex());
    }
  }

  /**
   * Method that gets the next icon for the player.
   *
   * @return the next icon.
   */
  private int getNextIconIndex() {
    int iconIndex = 0;
    for (AbstractPlayer player : players) {
      if (player.getIconIndex() == iconIndex) {
        iconIndex++;
      }
    }
    return iconIndex;
  }

  /**
   * Method that moves the player.
   *
   * @param sumDice the sum of the dice
   */
  public void movePlayer(Player player, int sumDice) {
    int newPosition = player.getPosition() + sumDice;
    newPosition = handleOvershoot(newPosition);
    player.setPosition(newPosition);
    handleTileAction(player, newPosition);
  }

  /**
   * Method that handles the player's turn.
   */
  public void handlePlayerTurn(Player player, int diceValue) {
    movePlayer(player, diceValue);

    gameSubject.notifyListener(EventType.DICE_ROLLED, player, diceValue);
  }

  /**
   * Method that handles overshoot of the player.
   *
   * @param newPosition the new position of the player
   * @return the new position of the player
   */
  public int handleOvershoot(int newPosition) {
    if (newPosition > WinCondition) {
      int overshoot = newPosition - WinCondition;
      newPosition = WinCondition - overshoot;
    }
    return newPosition;
  }

  /**
   * Method that handles the action of a tile. Indirectly takes value checkTileType. from abstract
   * class Tile.
   *
   * @param player      the player
   * @param newPosition the new position of the player
   */
  public void handleTileAction(Player player, int newPosition) {
    Tile tile = currentBoard.getTile(newPosition);
    tile.perform(player);
    checkTileType = tile.tileTypeNumber;
    specialTilePosition = tile.getPosition();
  }

  /**
   * Method that checks the type of tile the player is on.
   *
   * @return the type tile number.
   */
  public int getCheckTileType() {
    return checkTileType;
  }

  /**
   * Method that checks the position of the special tile.
   *
   * @return the position of the special tile
   */
  public int getSpecialTilePosition() {
    return specialTilePosition;
  }

}
