package com.gruppe24.BoardGames.LadderGame;

import com.gruppe24.BoardGames.LadderGame.Models.Player;

public interface TileAction {

  /**
   * Interface-method that performs an action.
   * @param player the relevant player
   */
  void perform(Player player);
}
