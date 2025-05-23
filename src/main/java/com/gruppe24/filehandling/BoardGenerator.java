package com.gruppe24.filehandling;

import com.gruppe24.boardgames.laddergame.models.board.Board;
import java.util.HashMap;

/**
 * This class generates different types of boards for the Ladder game. It creates a classic board
 * and a special board with additional features.
 */
public class BoardGenerator {

  /**
   * Main method to create and save boards.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    createClassicBoard();
    createSpecialBoard();
  }

  /**
   * Creates a classic board and saves it to a JSON file.
   */
  private static void createClassicBoard() {
    Board classicBoard = createBoard(false,
        "Classic Board", "The classic Ladder board");
    saveBoard(classicBoard, "classic_board");
  }

  /**
   * Creates a special board with additional features and saves it to a JSON file.
   */
  private static void createSpecialBoard() {
    Board specialBoard = createBoard(true,
        "Special Board", "Board with special tiles like frozen and random teleport");
    saveBoard(specialBoard, "special_board");
  }

  /**
   * Creates a board with the specified parameters.
   *
   * @param includeSpecialTiles whether to include special tiles
   * @param name                the name of the board
   * @param description         the description of the board
   * @return a new Board object
   */
  private static Board createBoard(boolean includeSpecialTiles, String name, String description) {
    HashMap<Integer, Integer> ladderUp = new HashMap<>();
    HashMap<Integer, Integer> ladderDown = new HashMap<>();
    HashMap<Integer, Boolean> winningTile = new HashMap<>();
    HashMap<Integer, Boolean> frozenTiles = new HashMap<>();
    HashMap<Integer, Boolean> randomTeleportTiles = new HashMap<>();

    Board.initializeStandardLadders(ladderUp, ladderDown);
    Board.initializeWinningTile(winningTile);

    if (includeSpecialTiles) {
      Board.initializeStandardSpecialTiles(frozenTiles, randomTeleportTiles);
    } else {
      frozenTiles.put(0, false);
      randomTeleportTiles.put(0, false);
    }

    return new Board(ladderUp, ladderDown, winningTile, frozenTiles, randomTeleportTiles, name,
            description);
  }

  /**
   * Saves the given board to a JSON file.
   *
   * @param board    the board to save
   * @param filename the name of the file (without extension)
   */
  private static void saveBoard(Board board, String filename) {
    boolean success = FileHandler.saveBoardToJson(board, filename);

    if (success) {
      System.out.println("Board saved to resources/boards/" + filename + ".json");
    } else {
      System.out.println("Failed to save " + filename + ".json");
    }
  }
}