package com.gruppe24.FileHandling;

import com.gruppe24.BoardGames.LadderGame.Models.Player;
import com.gruppe24.Utils.ColorUtil;
import java.io.IOException;
import java.io.FileWriter;
import java.util.List;

public class CSVPlayerWriter implements com.gruppe24.FileHandling.FileWriter {

  @Override
  public boolean writeToFile(Object object, String filePath) {
    if (!(object instanceof List<?>)) {
      System.err.println("Error: Object is not a List");
      return false;
    }

    List<?> list = (List<?>) object;
    if (list.isEmpty() || !(list.getFirst() instanceof Player)) {
      System.err.println("Error: List does not contain Player objects");
      return false;
    }

    @SuppressWarnings("unchecked")
    List<Player> players = (List<Player>) list;

    try (FileWriter writer = new FileWriter(filePath)) {
      for (Player player : players) {
        String colorName = ColorUtil.getColorName(player.getPlayerPiece().getFill());
        writer.write(player.getName() + "," + colorName + "\n");
      }
      return true;
    } catch (IOException e) {
      System.err.println("Error writing CSV file: " + e.getMessage());
      return false;
    }
  }
}