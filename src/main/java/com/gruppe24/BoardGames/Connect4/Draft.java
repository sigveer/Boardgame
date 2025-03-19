package com.gruppe24.BoardGames.Connect4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Draft extends Application {
  public static final int ROWS = 6;
  public static final int COLUMNS = 7;
  public static final int TILE_SIZE = 100;
  public static final int CIRCLE_RADIUS = 30;
  public static final int WINNNING_LENGTH = 4;
  public static final int EMPTY = 0;

  public static final Pane[][] board = new Pane[ROWS][COLUMNS];
  public static final int[][] gameStatus = new int[ROWS][COLUMNS]; // 0 = empty, 1 = red, 2 = yellow

  public boolean redTurn = true;

  @Override
  public void start(Stage stage) {
    GridPane grid = new GridPane();

    //Board
    for (int row = 0; row < ROWS; row++) {
      for (int col = 0; col < COLUMNS; col++) {

        Pane tile = new createTile(row, col);
        board[row][col] = tile;
        grid.add(tile, col, row);
      }
    }
    Scene scene = new Scene(grid);
    stage.setTitle("Connect4");
    stage.setScene(scene);
    stage.setResizable(false);
    stage.show();
  }

  private Pane createTile(int row, int col) {
    Pane tile = new Pane();
    tile.setPrefSize(TILE_SIZE, TILE_SIZE);
    return tile;
  }


    public static void main(String[] args) {
    }
}
