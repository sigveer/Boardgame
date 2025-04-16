package com.gruppe24.filehandling;

import com.gruppe24.boardgames.laddergame.models.board.Board;
import java.io.File;
import javafx.stage.FileChooser;

public class FileHandler {

  private static final String BOARD_DIRECTORY = "src/main/resources/boards/";


  public static boolean saveBoardToJson(Board board, String fileName) {
    createDirectory();

    String filePath = BOARD_DIRECTORY + fileName + ".json";
    JSONBoardWriter writer = new JSONBoardWriter();
    return writer.writeToFile(board, filePath);
  }


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