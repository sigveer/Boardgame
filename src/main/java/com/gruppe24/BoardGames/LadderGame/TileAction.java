package com.gruppe24.BoardGames.LadderGame;

import com.gruppe24.BoardGames.LadderGame.Models.Player;

public interface TileAction {

  /**
   * Interface-method that performs an action.
   * @param player the relevant player
   *
   * @Author Sigveer
   * @Date: 06.02.2025
   * @Version: 1.0
   */
  void perform(Player player);
}
