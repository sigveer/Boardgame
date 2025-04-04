package com.gruppe24.FileHandling;

import com.gruppe24.BoardGames.LadderGame.Models.Board.Board;
import com.gruppe24.BoardGames.LadderGame.Models.Player;
import java.io.File;
import java.util.List;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileHandler {

  private static final String BOARD_DIRECTORY = "src/main/resources/boards/";

  public static boolean saveBoardToJson(Board board, String fileName) {
    createDirectory(BOARD_DIRECTORY);

    String filePath = BOARD_DIRECTORY + fileName + ".json";
    JSONBoardWriter writer = new JSONBoardWriter();
    return writer.writeToFile(board, filePath);
  }


  public static boolean savePlayers(List<Player> players, Stage stage) {
    if (players == null || players.isEmpty() || stage == null) {
      return false;
    }

    FileChooser fileChooser = createFileChooser("Save Players", true);
    File file = fileChooser.showSaveDialog(stage);

    if (file != null) {
      String filePath = file.getAbsolutePath();
      if (!filePath.toLowerCase().endsWith(".csv")) {
        filePath += ".csv";
      }

      CSVPlayerWriter writer = new CSVPlayerWriter();
      return writer.writeToFile(players, filePath);
    }
    return false;
  }


  public static List<Player> loadPlayers(Stage stage) {
    if (stage == null) {
      return null;
    }

    FileChooser fileChooser = createFileChooser("Load Players", false);
    File file = fileChooser.showOpenDialog(stage);

    if (file != null) {
      CSVPlayerReader reader = new CSVPlayerReader();
      Object result = reader.readFromFile(file.getAbsolutePath());

      if (result instanceof List<?>) {
        @SuppressWarnings("unchecked")
        List<Player> loadedPlayers = (List<Player>) result;
        return loadedPlayers;
      }
    }
    return null;
  }


  private static FileChooser createFileChooser(String title, boolean isSave) {
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


  private static void createDirectory(String directoryPath) {
    File directory = new File(directoryPath);
    if (!directory.exists()) {
      directory.mkdirs();
    }
  }
}