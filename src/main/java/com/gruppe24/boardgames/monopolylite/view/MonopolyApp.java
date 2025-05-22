package com.gruppe24.boardgames.monopolylite.view;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * View class that initializes javafx in View-class.
 */
public class MonopolyApp extends Application {

  @Override
  public void start(Stage primaryStage) {
    View view = new View(primaryStage);

    view.initializeView();
  }
}
