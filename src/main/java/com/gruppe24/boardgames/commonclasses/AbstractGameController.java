package com.gruppe24.boardgames.commonclasses;

import com.gruppe24.boardgames.laddergame.models.Dice;
import com.gruppe24.boardgames.laddergame.models.Player;
import com.gruppe24.observerpattern.EventType;
import com.gruppe24.observerpattern.GameSubject;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGameController implements GameLogic {

  protected List<AbstractPlayer> players;
  protected Dice dice;
  protected GameSubject gameSubject;

  protected AbstractGameController(int numDice, GameSubject gameSubject) {
    this.players = new ArrayList<>();
    this.dice = new Dice(numDice);
    this.gameSubject = gameSubject;
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
  public List<AbstractPlayer> getPlayers() {
    return players;
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

}
