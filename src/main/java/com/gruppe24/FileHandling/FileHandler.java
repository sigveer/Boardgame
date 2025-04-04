package com.gruppe24.FileHandling;

import com.gruppe24.BoardGames.LadderGame.Models.Board.Board;
import java.io.File;

public class FileHandler {

  private static final String BOARD_DIRECTORY = "src/main/resources/boards/";

  public static boolean saveBoardToJson(Board board, String fileName) {
    // Create directory if it doesn't exist
    createDirectory(BOARD_DIRECTORY);

    // Create file path
    String filePath = BOARD_DIRECTORY + fileName + ".json";

    // Save board to file
    JSONBoardWriter writer = new JSONBoardWriter();
    return writer.writeToFile(board, filePath);
  }


  private static void createDirectory(String directory) {
    File dir = new File(directory);
    if (!dir.exists()) {
      boolean created = dir.mkdirs();
      if (!created) {
        System.err.println("Error: Could not create directory: " + directory);
      }
    }
  }
}
