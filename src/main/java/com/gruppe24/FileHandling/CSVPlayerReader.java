package com.gruppe24.FileHandling;

import com.gruppe24.BoardGames.LadderGame.Models.Player;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class CSVPlayerReader implements FileReader {

  @Override
  public Object readFromFile(String filePath) {
    List<Player> players = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] values = line.split(",");
        if (values.length >= 2) {
          String name = values[0];
          Color color = Color.valueOf(FileHandler.getColorName(Paint.valueOf(values[1])));
          Player player = new Player(name, color);

          players.add(player);
        }
      }

      return players;

    } catch (IOException e) {
      System.err.println("Error reading CSV file: " + e.getMessage());
      return null;
    }
  }
}