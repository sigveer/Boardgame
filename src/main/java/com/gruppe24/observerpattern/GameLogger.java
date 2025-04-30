package com.gruppe24.observerpattern;

import com.gruppe24.boardgames.laddergame.models.Player;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public class GameLogger implements GameObserver {
  private static final Logger LOGGER = Logger.getLogger(GameLogger.class.getName());

  /**
   * Constructor for GameLogger.
   *
   * @AI_Assisted The Object args[] idea with the LOGGER was created by AI.
   */
  @Override
  public void update(GameEventType eventType, Object... args) {
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
}
