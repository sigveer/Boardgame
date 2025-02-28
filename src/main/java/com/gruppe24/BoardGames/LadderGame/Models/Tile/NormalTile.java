package com.gruppe24.BoardGames.LadderGame.Models.Tile;

import com.gruppe24.BoardGames.LadderGame.Models.Player;

/**
 * Class representing a normal tile.
 */
public class NormalTile extends AbstractTile {

  /**
   * Constructor that initializes the snake tile.
   *
   * @param position The position of the tile.
   *
   * @Author Sigveer
   * @Date: 06.02.2025
   * @Version: 1.0
   */
  public NormalTile(int position) {
    super(position);
  }

  @Override
  public void perform(Player player) {
  }
}