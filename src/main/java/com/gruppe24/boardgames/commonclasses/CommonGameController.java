package com.gruppe24.boardgames.commonclasses;

import com.gruppe24.boardgames.laddergame.controller.BoardController;
import com.gruppe24.boardgames.laddergame.models.Player;
import com.gruppe24.boardgames.laddergame.models.board.Board;
import com.gruppe24.exeptions.InvalidBoardException;
import com.gruppe24.exeptions.InvalidPlayerException;
import com.gruppe24.observerpattern.EventType;
import com.gruppe24.observerpattern.GameSubject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.scene.image.Image;

/**
 * An abstract controller class that manages the game state: players, dice and the game board.
 * It provides:
 * <li>Adding players</li>
 * <li>Removing players</li>
 * <li>Moving players</li>
 * <li>Handling tile actions</li>
 */
public abstract class CommonGameController {

  protected List<CommonPlayer> players;
  private int winCondition;
  private int maxPlayers;
  private Board currentBoard;
  private int checkTileType = 0;
  private int specialTilePosition;

  /**
   * Constructor for CommonGameController.
   */
  protected CommonGameController() {
    this.players = new ArrayList<>();
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
   * Initializes the game with the board controller. This must be called before starting the game.
   *
   * @param boardController the board controller to use for this game
   */
  public void initializeGame(BoardController boardController) {
    if (boardController == null) {
      throw new InvalidBoardException("BoardController cannot be null");
    }
    this.currentBoard = boardController.getBoard();
  }

  /**
   * Method for adding a player to playermenu.
   */
  public void addPlayer() {
    if (players.size() >= getMaxPlayers()) {
      throw new InvalidPlayerException("Too many players");
    }
    CommonPlayer newPlayer = createPlayer("Player " + (players.size() + 1), getNextIconIndex());
    players.add(newPlayer);

    GameSubject.gameSubjectInstance().notifyListener(EventType.PLAYER_ADDED, newPlayer);
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
   * Method that removes the last player in the playermenu.
   */
  public void removePlayer() {
    if (players.size() <= 1) {
      throw new InvalidPlayerException("Cannot remove last player");
    }

    CommonPlayer removedPlayer = players.removeLast();
    GameSubject.gameSubjectInstance().notifyListener(EventType.PLAYER_REMOVED, removedPlayer);
  }

  /**
   * Method that gets the players.
   *
   * @return the players
   */
  public List<Player> getPlayerList() {
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
  public void setPlayersList(List<Player> players) {
    this.players.clear();

    this.players.addAll(players);

    getPlayerList();
  }

  /**
   * Method for cycling the player icon.
   *
   * @param index the index of the player in the list
   */
  public void cyclePlayerIcon(int index) {
    if (index >= 0 && index < players.size()) {
      CommonPlayer player = players.get(index);

      String[] paths = CommonPlayer.getIconPaths();
      int newIconIndex = (player.getIconIndex() + 1) % paths.length;
      String nextPath = paths[newIconIndex];

      Image newIcon = new Image(
          Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(nextPath)));

      player.setCurrentIconIndex(newIconIndex);
      player.setIconPath(nextPath);
      player.setIcon(newIcon);
      player.getPlayerPiece().setImage(newIcon);

      GameSubject.gameSubjectInstance()
          .notifyListener(EventType.PLAYER_ICON_CHANGED, player, newIconIndex);
    }
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

  /**
   * Method that handles the player's turn.
   */
  public void handlePlayerTurn(Player player, int diceValue) {
    movePlayer(player, diceValue);

    GameSubject.gameSubjectInstance().notifyListener(EventType.DICE_ROLLED, player, diceValue);
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
   * Getter method that returns the winCondition.
   *
   * @return winCondition.
   */
  public int getWinCondition() {
    return this.winCondition;
  }

  /**
   * Setter method that set the win condition.
   */
  public void setWinCondition(int winCondition) {
    this.winCondition = winCondition;
  }

  /**
   * Getter method for maxPlayers.
   *
   * @return maxPlayers.
   */
  public int getMaxPlayers() {
    return this.maxPlayers;
  }

  /**
   * Setter method for maxPlayers.
   *
   * @param maxPlayers the maximum number of players.
   */
  public void setMaxPlayers(int maxPlayers) {
    this.maxPlayers = maxPlayers;
  }
}
