package com.gruppe24.utils;

import com.gruppe24.boardgames.laddergame.models.Player;
import com.gruppe24.observerpattern.EventType;
import com.gruppe24.observerpattern.GameObserver;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * GameLogger is an implementation of GameObserver interface that logs game events to the console.
 */
public class GameLogger implements GameObserver {

  private static final Logger LOGGER = Logger.getLogger(GameLogger.class.getName());

  /**
   * Handles game events and logs them to the console.
   *
   * @AI_Assisted The Object args[] idea with the LOGGER was created by AI.
   */
  @Override
  public void update(EventType eventType, Object... args) {
    switch (eventType) {
      case DICE_ROLLED -> {
        Player player = (Player) args[0];
        int diceValue = (int) args[1];
        LOGGER.log(Level.INFO, "{0} rolled {1}", new Object[]{player.getName(), diceValue});
      }
      case PLAYER_MOVED -> {
        Player player = (Player) args[0];
        int fromPos = (int) args[1];
        int toPos = (int) args[2];
        LOGGER.log(Level.INFO, "{0} moved from {1} to {2}",
            new Object[]{player.getName(), fromPos, toPos});
      }
      case PLAYER_ADDED -> {
        Player player = (Player) args[0];
        LOGGER.log(Level.INFO, "Player added: {0}", player.getName());
      }
      case PLAYER_REMOVED -> {
        Player player = (Player) args[0];
        LOGGER.log(Level.INFO, "Player removed: {0}", player.getName());
      }
      case PLAYER_ICON_CHANGED -> {
        Player player = (Player) args[0];
        int newIconIndex = (int) args[1];
        LOGGER.log(Level.INFO, "{0} changed icon to {1}", new Object[]{player.getName(),
            newIconIndex});
      }
      case GAME_STARTED -> {
        List<Player> players = (List<Player>) args[0];
        LOGGER.log(Level.INFO, "Game started with {0} players", players.size());
      }
      case GAME_ENDED -> {
        Player winner = (Player) args[0];
        LOGGER.log(Level.INFO, "Game ended. Winner: {0}", winner.getName());
      }
      default -> {
        LOGGER.log(Level.WARNING, "Unknown event: {0}", eventType);
      }
    }
  }

  /**
   * Logger method for logging messages.
   *
   * @return the logger object
   */
  public static Logger getLogger() {
    return Logger.getLogger(GameLogger.class.getName());
  }
}
