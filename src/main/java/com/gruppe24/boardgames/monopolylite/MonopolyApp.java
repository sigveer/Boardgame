package com.gruppe24.boardgames.monopolylite;

import com.gruppe24.boardgames.monopolylite.view.View;

import javafx.application.Application;
import javafx.stage.Stage;

public class MonopolyApp extends Application {

  @Override
  public void start(Stage primaryStage) {
//    Model model = new Model();
//    Controller controller = new Controller(model);
//    View view = new View(primaryStage, model, controller);
//
//    controller.setView(view);

    View view = new View(primaryStage);

    view.initializeView();
  }
}
