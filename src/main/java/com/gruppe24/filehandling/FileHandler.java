package com.gruppe24.filehandling;

import com.gruppe24.boardgames.laddergame.models.board.Board;
import java.io.File;
import javafx.stage.FileChooser;

/**
 * FileHandler is a utility class for handling file operations related to the game board.
 * It provides methods to save the board to a JSON file and create a file chooser for
 * loading/saving files.
 */
public class FileHandler {

  private static final String BOARD_DIRECTORY = "src/main/resources/boards/";

  /**
   * Saves the given board to a JSON file with the specified file name.
   *
   * @param board    The board to save.
   * @param fileName The name of the file (without extension).
   * @return true if the file was saved successfully, false otherwise.
   */
  public static boolean saveBoardToJson(Board board, String fileName) {
    createDirectory();

    String filePath = BOARD_DIRECTORY + fileName + ".json";
    JsonBoardWriter writer = new JsonBoardWriter();
    return writer.writeToFile(board, filePath);
  }

  /**
   * Creates a FileChooser for loading or saving files.
   *
   * @param title  The title of the file chooser.
   * @param isSave True if the file chooser is for saving, false for loading.
   * @return A configured FileChooser instance.
   */
  public static FileChooser createFileChooser(String title, boolean isSave) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle(title);
    fileChooser.getExtensionFilters().add(
        new FileChooser.ExtensionFilter("CSV Files", "*.csv")
    );

    if (isSave) {
      fileChooser.setInitialFileName("players.csv");
    }

    fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    return fileChooser;
  }


  private static void createDirectory() {
    File directory = new File(FileHandler.BOARD_DIRECTORY);
    if (!directory.exists()) {
      directory.mkdirs();
    }
  }
}