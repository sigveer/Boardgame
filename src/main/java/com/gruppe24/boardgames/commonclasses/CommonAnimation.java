package com.gruppe24.boardgames.commonclasses;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public abstract class CommonAnimation {

  protected static final double defaultAnimationSpeed = 0.3;


  /**
   * Adds a player piece to a specific position on the grid.
   *
   * @param gridPane The grid to add the player piece to
   * @param player   The player whose piece should be added
   * @param col      The column position
   * @param row      The row position
   */
  protected void addPlayerPieceToGrid(GridPane gridPane, CommonPlayer player, int col, int row) {
    gridPane.getChildren().remove(player.getPlayerPiece());

    StackPane cellContainer = new StackPane();
    cellContainer.getChildren().add(player.getPlayerPiece());
    StackPane.setAlignment(player.getPlayerPiece(), javafx.geometry.Pos.CENTER);

    gridPane.add(cellContainer, col, row);
  }
}
