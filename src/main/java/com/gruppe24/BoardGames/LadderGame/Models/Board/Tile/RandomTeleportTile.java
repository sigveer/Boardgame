package com.gruppe24.BoardGames.LadderGame.Models.Board.Tile;

import com.gruppe24.BoardGames.LadderGame.Models.Player;
import java.util.Random;

/**
 * A special tile that teleports the player to a random position on the board.
 *
 * @Author GitHub Copilot
 * @Date: 07.09.2023
 * @Version: 1.0
 */
public class RandomTeleportTile extends Tile {

  private final Random random = new Random();
  private final int maxPosition = 89;
  private final int minPosition = 1;
  private int destinationPosition;

  public RandomTeleportTile(int position) {
    super(position);
    this.checkTileType = 3;
  }

  @Override
  public void perform(Player player) {
    destinationPosition = random.nextInt(maxPosition - minPosition + 1) + minPosition;

    player.setPosition(destinationPosition);

    this.position = destinationPosition;
  }

  @Override
  public int getPosition() {
    return position;
  }
}