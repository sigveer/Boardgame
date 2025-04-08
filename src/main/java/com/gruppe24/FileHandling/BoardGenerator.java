package com.gruppe24.FileHandling;

import static com.gruppe24.BoardGames.LadderGame.Models.Board.Board.initializeStandardLadders;
import static com.gruppe24.BoardGames.LadderGame.Models.Board.Board.initializeStandardSpecialTiles;

import com.gruppe24.BoardGames.LadderGame.Models.Board.Board;
import java.util.HashMap;


public class BoardGenerator {


  public static void main(String[] args) {
    createClassicBoard();
    createSpecialBoard();
  }


  private static Board createBoard(boolean includeSpecialTiles, String name, String description) {
    HashMap<Integer, Integer> ladderUp = new HashMap<>();
    HashMap<Integer, Integer> ladderDown = new HashMap<>();
    HashMap<Integer, Boolean> frozenTiles = new HashMap<>();
    HashMap<Integer, Boolean> randomTeleportTiles = new HashMap<>();

    initializeStandardLadders(ladderUp, ladderDown);

    if (includeSpecialTiles) {
      initializeStandardSpecialTiles(frozenTiles, randomTeleportTiles);
    }

    return new Board(ladderUp, ladderDown, frozenTiles, randomTeleportTiles, name, description);
  }


  private static void createClassicBoard() {
    Board classicBoard = createBoard(false,
        "Classic Board", "The classic Ladder board");
    saveBoard(classicBoard, "classic_board");
  }


  private static void createSpecialBoard() {
    Board specialBoard = createBoard(true,
        "Special Board", "Board with special tiles like frozen and random teleport");
    saveBoard(specialBoard, "special_board");
  }


  private static void saveBoard(Board board, String filename) {
    boolean success = FileHandler.saveBoardToJson(board, filename);

    if (success) {
      System.out.println("Board saved to resources/boards/" + filename + ".json");
    } else {
      System.out.println("Failed to save " + filename);
    }
  }
}