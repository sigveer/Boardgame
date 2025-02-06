package com.gruppe24;

import com.gruppe24.BoardGames.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage stage) {
    MainMenu menu = new MainMenu();
    menu.start(stage);
  }

  public static void main(String[] args) {
    launch();
  }
}
