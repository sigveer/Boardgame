package com.gruppe24.filehandling;

import com.gruppe24.boardgames.laddergame.models.Player;
import com.gruppe24.boardgames.laddergame.models.board.Board;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
    File directory = new File(FileHandler.BOARD_DIRECTORY);
    if (!directory.exists()) {
      directory.mkdirs();
    }

    String filePath = BOARD_DIRECTORY + fileName + ".json";
    JsonBoardWriter writer = new JsonBoardWriter();
    return writer.writeToFile(board, filePath);
  }

  /**
   * Saves a list of players to a CSV file.
   *
   * @param players The list of players to save
   * @param stage   The stage to display the file chooser
   * @return true if saving was successful, false otherwise
   */
  public static boolean savePlayers(List<Player> players, Stage stage) {
    FileChooser fileChooser = FileHandler.createFileChooser("Save Players", true);
    File file = fileChooser.showSaveDialog(stage);

    if (file == null) {
      return false;
    }

    try (FileWriter writer = new FileWriter(file)) {

      writer.write("Name,IconPath\n");

      for (Player player : players) {
        int iconIndex = player.getIconIndex();
        writer.write(player.getName() + "," + iconIndex + "\n");
      }

      return true;
    } catch (IOException e) {
      e.printStackTrace();
      //implementer seinere
      return false;
    }
  }

  /**
   * Loads player data from a CSV file.
   *
   * @param stage The stage to use for the file chooser dialog.
   * @return A list of players loaded from the CSV file, or null if an error occurs.
   * @AI_Based "While" loop is based on AI logic and structure.
   */
  public static List<Player> loadPlayers(Stage stage) {
    FileChooser fileChooser = FileHandler.createFileChooser("Load Players", false);
    File file = fileChooser.showOpenDialog(stage);

    if (file == null) {
      return null;
    }

    List<Player> players = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

      reader.readLine();

      String line;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split(",", 2);
        if (parts.length >= 2) {
          String name = parts[0];
          int iconIndex = Integer.parseInt(parts[1]);

          Player player = new Player(name, iconIndex);
          players.add(player);
        }
      }

      return players;
    } catch (IOException e) {
      e.printStackTrace();
      //implementer seinere
      return null;
    }
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

}