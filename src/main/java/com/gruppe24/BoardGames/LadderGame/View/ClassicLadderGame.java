package com.gruppe24.BoardGames.LadderGame.View;

import com.gruppe24.BoardGames.LadderGame.Controller.GameController;
import com.gruppe24.BoardGames.LadderGame.Models.Board;
import com.gruppe24.BoardGames.LadderGame.Models.Dice;
import com.gruppe24.BoardGames.LadderGame.Models.Player;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.LadderUpTile;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.LadderDownTile;
import com.gruppe24.Utils.StyleUtils;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ClassicLadderGame extends Application {

  private final Board board;
  private final GameController gameController;
  private List<Player> players;
  private static final int tileSize = 75;
  private int currentPlayerIndex = 0;
  private Label diceResultLabel;
  private Label currentPlayerLabel;
  private Label snakeOrLadderCheck;
  private final Dice dice = new Dice(1);

  public ClassicLadderGame(List<Player> players) {
    this.gameController = new GameController();
    this.board = new Board();
    this.players = players;
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Laddergame Classic");

    //GridPane for tiles
    GridPane gridPane = new GridPane();
    gridPane.setAlignment(Pos.CENTER);
    gridPane.setVgap(1);
    gridPane.setHgap(1);
    gridPane.setStyle("-fx-background-color: #607ee4;");

    //Pane for ladders
    Pane ladderSnakeP = new Pane();
    ladderSnakeP.setMouseTransparent(true);

    //Scene
    Scene scene = new Scene(new StackPane(gridPane,ladderSnakeP), 1000, 850); //AI-suggestion

    //Title Label
    Label title = new Label("Board Games!");
    title.setStyle("-fx-font-size: 40px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");

    //Draw Board
    drawBoard(gridPane, ladderSnakeP); //drawing the board

    //Labels
    currentPlayerLabel = new Label("Current Player: " + players.get(currentPlayerIndex).getName());
    currentPlayerLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");

    diceResultLabel = new Label("Roll the dice!");
    diceResultLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");

    snakeOrLadderCheck = new Label("");
    snakeOrLadderCheck.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");

    //Buttons
    Button diceRoll = new Button("Roll Dice");
    diceRoll.setOnAction(event -> rollDiceAndMove(gridPane, primaryStage));
    StyleUtils.styleNormalButton(diceRoll);

    Button backToMenu = new Button("Back to Menu");
    backToMenu.setOnAction(event -> {new LadderGameMenuGUI().start(primaryStage);});
    StyleUtils.styleNormalButton(backToMenu);

    //Vertical Box for control panel
    VBox controlPanel = new VBox(10);
    controlPanel.setAlignment(Pos.CENTER);
    controlPanel.getChildren().addAll(currentPlayerLabel, diceResultLabel, snakeOrLadderCheck, diceRoll, backToMenu);
    gridPane.add(controlPanel, 11, 0, 1, 5);

    initializePlayerPositions(gridPane);

    primaryStage.setScene(scene);
    primaryStage.setResizable(false); // can not resize the window. A temporary fix?
    primaryStage.show();
  }

  private void initializePlayerPositions(GridPane gridPane) {
    for (Player player : players) {
      if (player.getPosition() > 0) {
        int position = player.getPosition();
        int row = 9 - (position - 1) / 9;
        int col;
        if ((9 - row) % 2 == 0) {
          col = (position - 1) % 9;
        } else {
          col = 8 - (position - 1) % 9;
        }
        gridPane.add(player.getPlayerPiece(), col, row);
      }
    }
  }

  private void rollDiceAndMove(GridPane gridPane, Stage primaryStage) {
    Player currentPlayer = players.get(currentPlayerIndex);
    int previousPosition = currentPlayer.getPosition();

    int diceValue = dice.rollSum();
    diceResultLabel.setText(currentPlayer.getName() + " rolled: " + diceValue);

    gameController.handlePlayerTurn(currentPlayer, diceValue);
    int newPosition = currentPlayer.getPosition();

    if (gameController.checkAndHandleWin(newPosition)) {
      new ClassicWinnerScreen(currentPlayer).start(primaryStage);
      return;
    }

    animateAndMove(gridPane, currentPlayer, previousPosition, newPosition);

    currentPlayerIndex = (currentPlayerIndex + 1) % players.size();

    currentPlayerLabel.setText("Current Player: " + players.get(currentPlayerIndex).getName());
  }

  private void animateAndMove(GridPane gridPane, Player player, int fromPosition, int toPosition) {
    int steps = toPosition - fromPosition;

    Timeline timeline = new Timeline();
    timeline.setCycleCount(1);

    int currentPosition = fromPosition;

    for (int i = 0; i < steps; i++) {
      final int stepPosition = currentPosition + 1;
      int row = 9 - (stepPosition - 1) / 9;
      int col;
      if ((9 - row) % 2 == 0) {
        col = (stepPosition - 1) % 9;
      } else {
        col = 8 - (stepPosition - 1) % 9;
      }

      KeyFrame keyFrame = new KeyFrame(
          Duration.seconds((i + 1) * 0.3), // Speed up animation a bit
          event -> {
            gridPane.getChildren().remove(player.getPlayerPiece());
            gridPane.add(player.getPlayerPiece(), col, row);
          }
      );
      timeline.getKeyFrames().add(keyFrame);
      currentPosition++;
    }

    timeline.setOnFinished(event -> {
      if (gameController.getCheckTileType() == 0) {
        snakeOrLadderCheck.setText("");
      } else if (gameController.getCheckTileType() == 1) {
        snakeOrLadderCheck.setText("Ladder!");
      } else if (gameController.getCheckTileType() == 2) {
        snakeOrLadderCheck.setText("Snake...");
      }
    });

    timeline.play();
  }



  public void drawBoard(GridPane gridPane, Pane ladderSnakePane) {
    for (int row = 9; row >= 0; row--) {
      for (int col = 0; col < 9; col++) {
        int tileNumber;
        if ((9 - row) % 2 == 0) {
          tileNumber = (9 - row) * 9 + col + 1;
        } else {
          tileNumber = (9 - row + 1) * 9 - col;
        }

        StackPane stackPane = new StackPane();
        Rectangle tile = new Rectangle(tileSize, tileSize);
        tile.setStroke(Color.BLACK);
        tile.setStrokeWidth(1);

        if (board.getTile(tileNumber) instanceof LadderUpTile) {
          tile.setFill(Color.GREEN);
        } else if (board.getTile(tileNumber) instanceof LadderDownTile) {
          tile.setFill(Color.RED);
        } else {
          tile.setFill(Color.WHITE);
        }

        Label numberLabel = new Label(Integer.toString(tileNumber));
        numberLabel.setStyle("-fx-font-weight: bold;");
        numberLabel.setTranslateX(26);
        numberLabel.setTranslateY(30);

        stackPane.getChildren().addAll(tile, numberLabel);
        stackPane.setAlignment(Pos.CENTER);

        gridPane.add(stackPane, col, row);
      }
    }

    //Ladders
    Image ladderImage = new Image("Ladder.png");

    ImageView ladderView1 = new ImageView(ladderImage);
    ladderView1.setFitWidth(200);
    ladderView1.setFitHeight(500);
    ladderView1.setX(125);
    ladderView1.setY(350);
    ladderView1.setRotate(30);

    ImageView ladderView2 = new ImageView(ladderImage);
    ladderView2.setFitWidth(150);
    ladderView2.setFitHeight(150);
    ladderView2.setX(630);
    ladderView2.setY(650);
    ladderView2.setRotate(30);

    ImageView ladderView3 = new ImageView(ladderImage);
    ladderView3.setFitWidth(200);
    ladderView3.setFitHeight(250);
    ladderView3.setX(90);
    ladderView3.setY(330);
    ladderView3.setRotate(30);

    ImageView ladderView4 = new ImageView(ladderImage);
    ladderView4.setFitWidth(200);
    ladderView4.setFitHeight(250);
    ladderView4.setX(500);
    ladderView4.setY(240);
    ladderView4.setRotate(18);

    ImageView ladderView5 = new ImageView(ladderImage);
    ladderView5.setFitWidth(200);
    ladderView5.setFitHeight(300);
    ladderView5.setX(450);
    ladderView5.setY(120);
    ladderView5.setRotate(15);

    ImageView ladderView6 = new ImageView(ladderImage);
    ladderView6.setFitWidth(200);
    ladderView6.setFitHeight(230);
    ladderView6.setX(590);
    ladderView6.setY(40);
    ladderView6.setRotate(15);

    ImageView ladderView7 = new ImageView(ladderImage);
    ladderView7.setFitWidth(200);
    ladderView7.setFitHeight(230);
    ladderView7.setX(350);
    ladderView7.setY(40);
    ladderView7.setRotate(15);

    ladderSnakePane.getChildren().addAll(ladderView1,ladderView2,ladderView3,ladderView4,ladderView5,ladderView6,ladderView7);

    //Snakes
    Image snakeImage = new Image("Snake.png");

    ImageView snakeView1 = new ImageView(snakeImage);
    snakeView1.setFitWidth(200);
    snakeView1.setFitHeight(200);
    snakeView1.setX(370);
    snakeView1.setY(590);
    snakeView1.setRotate(-45);

    ImageView snakeView2 = new ImageView(snakeImage);
    snakeView2.setFitWidth(250);
    snakeView2.setFitHeight(150);
    snakeView2.setX(200);
    snakeView2.setY(560);
    snakeView2.setRotate(-58);

    ImageView snakeView3 = new ImageView(snakeImage);
    snakeView3.setFitWidth(100);
    snakeView3.setFitHeight(100);
    snakeView3.setX(500);
    snakeView3.setY(440);
    snakeView3.setRotate(45);

    ImageView snakeView4 = new ImageView(snakeImage);
    snakeView4.setFitWidth(175);
    snakeView4.setFitHeight(150);
    snakeView4.setX(75);
    snakeView4.setY(300);
    snakeView4.setRotate(-58);

    ImageView snakeView5 = new ImageView(snakeImage);
    snakeView5.setFitWidth(400);
    snakeView5.setFitHeight(150);
    snakeView5.setX(530);
    snakeView5.setY(320);
    snakeView5.setRotate(90);

    ImageView snakeView6 = new ImageView(snakeImage);
    snakeView6.setFitWidth(680);
    snakeView6.setFitHeight(200);
    snakeView6.setX(50);
    snakeView6.setY(300);
    snakeView6.setRotate(55);

    ImageView snakeView7 = new ImageView(snakeImage);
    snakeView7.setFitWidth(200);
    snakeView7.setFitHeight(150);
    snakeView7.setX(240);
    snakeView7.setY(70);
    snakeView7.setRotate(-60);

    ladderSnakePane.getChildren().addAll(snakeView1,snakeView2,snakeView3,snakeView4,snakeView5,snakeView6,snakeView7);


  }

}