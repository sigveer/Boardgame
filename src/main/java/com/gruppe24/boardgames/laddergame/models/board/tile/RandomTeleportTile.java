package com.gruppe24.boardgames.laddergame.models.board.tile;

import com.gruppe24.boardgames.laddergame.models.Player;
import java.util.Random;

/**
 * A special tile that teleports the player to a random position on the board.
 *
 * @Author GitHub Copilot
 * @Date: 07.09.2023
 * @Version: 1.0
 */
public class RandomTeleportTile extends SpecialTile {

  private final Random random = new Random();

  public RandomTeleportTile(int position) {
    super(position);
  }

  @Override
  public void perform(Player player) {
    this.checkTileType = 3;

    int maxPosition = 89;
    int minPosition = 1;
    int destinationPosition = random.nextInt(maxPosition - minPosition + 1) + minPosition;

    player.setPosition(destinationPosition);

    this.position = destinationPosition;
  }

  @Override
  public int getPosition() {
    return position;
  }
}