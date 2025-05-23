package com.gruppe24.boardgames.laddergame.models.board.tiles;

import com.gruppe24.boardgames.laddergame.models.Player;
import java.util.Random;

/**
 * A special tile that teleports the player to a random position on the board.
 */
public class RandomTeleportTile extends SpecialTile {

  private final Random random = new Random();

  @Override
  public void perform(Player player) {
    int maxPosition = 89;
    int minPosition = 1;
    int destinationPosition = random.nextInt(maxPosition - minPosition + 1) + minPosition;

    player.setPosition(destinationPosition);
  }

  @Override
  public int getTileType() {
    return 3;
  }

  /**
   * Constructor that initializes the random teleport tile.
   *
   * @param position The position of the tile.
   */
  public RandomTeleportTile(int position) {
    super(position);
  }
}
