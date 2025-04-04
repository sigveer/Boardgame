package com.gruppe24.FileHandling;

import com.gruppe24.BoardGames.LadderGame.Models.Board.Board;
import com.gruppe24.BoardGames.LadderGame.Models.Player;
import java.io.File;
import java.util.List;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class FileHandler {

  private static final String BOARD_DIRECTORY = "src/main/resources/boards/";
  private static final String PLAYER_DIRECTORY = "src/main/resources/players/";


  public static boolean saveBoardToJson(Board board, String fileName) {
    // Create directory if it doesn't exist
    createDirectory(BOARD_DIRECTORY);

    // Create file path
    String filePath = BOARD_DIRECTORY + fileName + ".json";

    // Save board to file
    JSONBoardWriter writer = new JSONBoardWriter();
    return writer.writeToFile(board, filePath);
  }


  public static Board loadBoardFromJson(String fileName) {
    // Create file path
    String filePath = BOARD_DIRECTORY + fileName + ".json";

    // Check if file exists
    File file = new File(filePath);
    if (!file.exists()) {
      System.err.println("Error: File does not exist: " + filePath);
      return null;
    }

    // Load board from file
    JSONBoardReader reader = new JSONBoardReader();
    Object result = reader.readFromFile(filePath);

    if (result instanceof Board) {
      return (Board) result;
    } else {
      System.err.println("Error: File does not contain a valid board.");
      return null;
    }
  }


  public static boolean savePlayersToCSV(List<Player> players, String fileName) {
    // Create directory if it doesn't exist
    createDirectory(PLAYER_DIRECTORY);

    // Create file path
    String filePath = PLAYER_DIRECTORY + fileName + ".csv";

    // Save players to file
    CSVPlayerWriter writer = new CSVPlayerWriter();
    return writer.writeToFile(players, filePath);
  }


  @SuppressWarnings("unchecked")
  public static List<Player> loadPlayersFromCSV(String fileName) {
    // Create file path
    String filePath = PLAYER_DIRECTORY + fileName + ".csv";

    // Check if file exists
    File file = new File(filePath);
    if (!file.exists()) {
      System.err.println("Error: File does not exist: " + filePath);
      return null;
    }

    // Load players from file
    CSVPlayerReader reader = new CSVPlayerReader();
    Object result = reader.readFromFile(filePath);

    if (result instanceof List<?>) {
      List<?> list = (List<?>) result;
      if (list.isEmpty() || list.get(0) instanceof Player) {
        return (List<Player>) list;
      }
    }

    System.err.println("Error: File does not contain a valid list of players.");
    return null;
  }


  public static String[] listBoardFiles() {
    return listFiles(BOARD_DIRECTORY, ".json");
  }


  public static String[] listPlayerFiles() {
    return listFiles(PLAYER_DIRECTORY, ".csv");
  }


  private static String[] listFiles(String directory, String extension) {
    File dir = new File(directory);
    if (!dir.exists() || !dir.isDirectory()) {
      return new String[0];
    }

    File[] files = dir.listFiles((dir1, name) -> name.endsWith(extension));
    if (files == null || files.length == 0) {
      return new String[0];
    }

    String[] fileNames = new String[files.length];
    for (int i = 0; i < files.length; i++) {
      String name = files[i].getName();
      fileNames[i] = name.substring(0, name.length() - extension.length());
    }

    return fileNames;
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

  public static String getColorName(Paint paint) {
    if (paint.equals(Color.RED)) return "red";
    if (paint.equals(Color.BLUE)) return "blue";
    if (paint.equals(Color.GREEN)) return "green";
    if (paint.equals(Color.YELLOW)) return "yellow";
    if (paint.equals(Color.PURPLE)) return "purple";
    return "unknown";
  }
}
