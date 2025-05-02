package com.gruppe24.boardgames.commonclasses;

public interface GameLogic {

  void handlePlayerTurn(AbstractPlayer player, AbstractGameController gameController,
      int diceValue);

  void movePlayer(AbstractPlayer player, int steps);

  boolean checkWinCondition(AbstractPlayer player);

  void handleTileAction(AbstractPlayer player, int position);

  void handleOvershoot(int position);
}
