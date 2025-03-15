package com.gruppe24.BoardGames.TicTacToe.View;

import com.gruppe24.BoardGames.TicTacToe.Model.Cell;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class UIFactory {

  public static Scene createGameScene(BoardView boardView, Runnable resetAction) {
    BorderPane borderPane = new BorderPane();

    HBox controlPanel = createControlPanel(resetAction, boardView);
    borderPane.setBottom(controlPanel);
    borderPane.setCenter(boardView.getBoardGroup());

    return new Scene(borderPane, 300, 300);
  }

  private static HBox createControlPanel(Runnable resetAction, BoardView boardView) {
    HBox controlPanel = new HBox();
    controlPanel.setPrefHeight(40);
    controlPanel.setSpacing(10.0);
    controlPanel.setAlignment(Pos.BASELINE_CENTER);

    Button startButton = new Button("New Game");
    startButton.setOnMouseClicked(event -> {
      resetAction.run();
      startButton.getParent().getScene().setRoot(createGameScene(boardView, resetAction).getRoot());
    });

    controlPanel.getChildren().add(startButton);
    return controlPanel;
  }

  public static void configureCellUI(Cell cell, int x, int y, int size) {
    cell.setX(x * size);
    cell.setY(y * size);
    cell.setHeight(size);
    cell.setWidth(size);
    cell.setFill(Color.WHITE);
    cell.setStroke(Color.BLACK);
  }
}