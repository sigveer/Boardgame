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


  private static void createClassicBoard() {
    HashMap<Integer, Integer> ladderUp = new HashMap<>();
    HashMap<Integer, Integer> ladderDown = new HashMap<>();
    HashMap<Integer, Boolean> frozenTiles = new HashMap<>();
    HashMap<Integer, Boolean> randomTeleportTiles = new HashMap<>();

    initializeStandardLadders(ladderUp, ladderDown);

    Board classicBoard = new Board(ladderUp, ladderDown, frozenTiles, randomTeleportTiles,
        "Classic Board", "The classic Ladder board");

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
    HashMap<Integer, Boolean> frozenTiles = new HashMap<>();
    HashMap<Integer, Boolean> randomTeleportTiles = new HashMap<>();

    initializeStandardLadders(ladderUp, ladderDown);

    initializeStandardSpecialTiles(frozenTiles, randomTeleportTiles);

    Board specialBoard = new Board(ladderUp, ladderDown, frozenTiles, randomTeleportTiles,
        "Special Board", "Board with special tiles like frozen and random teleport");

    boolean success = FileHandler.saveBoardToJson(specialBoard, "special_board");

    if (success) {
      System.out.println("Special board saved to resources/boards/special_board.json");
    } else {
      System.out.println("Failed to save special board");
    }
  }
}