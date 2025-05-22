package com.gruppe24;

import com.gruppe24.boardgames.DashboardGui;
import com.gruppe24.observerpattern.GameLogger;
import java.util.logging.Level;

/**
 * Main class to start the application.
 * This class is the entry point of the application.
 * It launches the DashBoardGUI and logs when the application is closed.
 */
public class Main {

  /**
   * Main method to start the application.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    DashboardGui.launch(DashboardGui.class, args);
    GameLogger.getLogger().log(Level.INFO, "Application closed");
  }
}




