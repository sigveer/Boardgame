package com.gruppe24.FileHandling;

import com.gruppe24.BoardGames.LadderGame.Models.Board.Board;
import com.gruppe24.BoardGames.LadderGame.Models.Board.Tile.FrozenTile;
import com.gruppe24.BoardGames.LadderGame.Models.Board.Tile.RandomTeleportTile;
import java.util.HashMap;

public class BoardGenerator {
  public static void main(String[] args) {
    createClassicBoard();
    createSpecialBoard();
  }

  private static void createClassicBoard() {
    HashMap<Integer, Integer> ladderUp = new HashMap<>();
    HashMap<Integer, Integer> ladderDown = new HashMap<>();

    ladderUp.put(2, 40);
    ladderUp.put(8, 10);
    ladderUp.put(36, 52);
    ladderUp.put(43, 62);
    ladderUp.put(49, 79);
    ladderUp.put(65, 82);
    ladderUp.put(68, 85);

    ladderDown.put(24, 5);
    ladderDown.put(33, 3);
    ladderDown.put(42, 30);
    ladderDown.put(56, 37);
    ladderDown.put(64, 27);
    ladderDown.put(74, 12);
    ladderDown.put(87, 70);

    // Create the board
    Board classicBoard = new Board(ladderUp, ladderDown,
        "Classic Board", "The classic Ladder board");

    // Save the board
    boolean success = FileHandler.saveBoardToJson(classicBoard, "classic_board");

    if (success) {
      System.out.println("Classic board saved to resources/boards/classic_board.json");
    } else {
      System.out.println("Failed to save classic board");
    }
  }

  private static void createSpecialBoard() {
    HashMap<Integer, Integer> ladderUp = new HashMap<>();
    HashMap<Integer, Integer> ladderDown = new HashMap<>();

    ladderUp.put(2, 40);
    ladderUp.put(8, 10);
    ladderUp.put(36, 52);
    ladderUp.put(43, 62);
    ladderUp.put(49, 79);
    ladderUp.put(65, 82);
    ladderUp.put(68, 85);

    ladderDown.put(24, 5);
    ladderDown.put(33, 3);
    ladderDown.put(42, 30);
    ladderDown.put(56, 37);
    ladderDown.put(64, 27);
    ladderDown.put(74, 12);
    ladderDown.put(87, 70);

    // Create a custom special board
    SpecialBoardGenerator specialBoard = new SpecialBoardGenerator(
        ladderUp, ladderDown,
        "Special Board", "Board with special tiles like frozen and random teleport");

    // Save the board
    boolean success = FileHandler.saveBoardToJson(specialBoard, "special_board");

    if (success) {
      System.out.println("Special board saved to resources/boards/special_board.json");
    } else {
      System.out.println("Failed to save special board");
    }
  }

  // Custom board class that extends Board to add special tiles
  private static class SpecialBoardGenerator extends Board {

    public SpecialBoardGenerator(
        HashMap<Integer, Integer> ladderUp,
        HashMap<Integer, Integer> ladderDown,
        String name,
        String description
    ) {
      super(ladderUp, ladderDown, name, description);
    }

    @Override
    protected void initializeTiles() {
      super.initializeTiles();

      tiles[50] = new RandomTeleportTile(50);
      tiles[34] = new FrozenTile(34);
      tiles[78] = new FrozenTile(78);
    }
  }
}