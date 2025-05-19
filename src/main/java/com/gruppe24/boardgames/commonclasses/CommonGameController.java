package com.gruppe24.boardgames.commonclasses;

import com.gruppe24.boardgames.laddergame.controller.BoardController;
import com.gruppe24.boardgames.laddergame.models.Player;
import com.gruppe24.boardgames.laddergame.models.board.Board;
import com.gruppe24.observerpattern.EventType;
import com.gruppe24.observerpattern.GameSubject;
import java.util.ArrayList;
import java.util.List;

/**
 * CommonGameController is an abstract class that manages the game state, including players, dice,
 * and the game board. It provides methods for adding and removing players, moving players, and
 * handling tile actions.
 */
public abstract class CommonGameController {

  protected List<CommonPlayer> players;
  protected CommonDice dice;
  protected GameSubject gameSubject;
  protected int winCondition;
  private Board currentBoard;
  private int checkTileType = 0;
  private int specialTilePosition;

  /**
   * Constructor for CommonGameController.
   *
   * @param numDice     the number of dice to use in the game
   * @param gameSubject the game subject for observer pattern
   */
  protected CommonGameController(int numDice, GameSubject gameSubject) {
    this.players = new ArrayList<>();
    this.dice = new CommonDice(numDice);
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

  /**
   * Abstract method for creating a player. This method must be implemented by subclasses to create
   * players.
   *
   * @param name      the name of the player.
   * @param iconIndex the icon index of the player
   * @return the created player
   */
  protected abstract CommonPlayer createPlayer(String name, int iconIndex);

  /**
   * Abstract method for getting the maximum number of players allowed in the game. This method must
   * be implemented by subclasses to specify the maximum number of players.
   *
   * @return the maximum number of players
   */
  protected abstract int getMaxPlayers();

  /**
   * Method for adding a player to playermenu.
   */
  public void addPlayer() {
    if (players.size() >= getMaxPlayers()) {
      return;
    }
    CommonPlayer newPlayer = createPlayer("Player " + (players.size() + 1), getNextIconIndex());
    players.add(newPlayer);

    gameSubject.notifyListener(EventType.PLAYER_ADDED, newPlayer);
  }

  /**
   * Method that removes the last player in the playermenu.
   */
  public void removePlayer() {
    if (players.size() > 1) {
      CommonPlayer removedPlayer = players.removeLast();

      gameSubject.notifyListener(EventType.PLAYER_REMOVED, removedPlayer);
    }
  }

  /**
   * Method that gets the players.
   *
   * @return the players
   */
  public List<Player> getPlayers() {
    List<Player> playerList = new ArrayList<>();
    for (CommonPlayer commonPlayer : players) {
      if (commonPlayer instanceof Player) {
        playerList.add((Player) commonPlayer);
      }
    }
    return playerList;
  }

  /**
   * Sets the player list and returns the updated list.
   *
   * @param players the new list of players
   */
  public void setPlayers(List<Player> players) {
    this.players.clear();

    this.players.addAll(players);

    getPlayers();
  }

  /**
   * Method for cycling the player icon.
   *
   * @param index the index of the player in the list
   */
  public void cyclePlayerIcon(int index) {
    if (index >= 0 && index < players.size()) {
      CommonPlayer player = players.get(index);
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
    for (CommonPlayer player : players) {
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
    if (newPosition > winCondition) {
      int overshoot = newPosition - winCondition;
      newPosition = winCondition - overshoot;
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
    CommonTile commonTile = currentBoard.getTile(newPosition);
    commonTile.perform(player);
    checkTileType = commonTile.tileTypeNumber;
    specialTilePosition = commonTile.getPosition();
  }
}
