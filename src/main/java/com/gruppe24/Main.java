package com.gruppe24;

import com.gruppe24.boardgames.MenuGui;
import com.gruppe24.utils.Validators;
import java.util.logging.Level;

/**
 * Main class to start the application.
 * This class is the entry point of the application.
 * It launches the MenuGui and logs when the application is closed.
 */
public class Main {

  /**
   * Main method to start the application.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    MenuGui.launch(MenuGui.class, args);
    Validators.getLogger().log(Level.INFO, "Application closed");
  }
}




