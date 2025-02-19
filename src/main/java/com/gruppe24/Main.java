package com.gruppe24;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage stage) {
    Group root = new Group();
    Scene scene = new Scene(root);


    Text text = new Text();
    text.setText("Hallah aleikum'a kompis?");
    text.setX(400);
    text.setY(50);
    text.setFont(Font.font("Verdana",25));
    text.setFill(Color.PURPLE);
    root.getChildren().add(text);

    Line line = new Line();
    line.setStartX(400);
    line.setStartY(50);
    line.setEndX(800);
    line.setEndY(50);
    root.getChildren().add(line);
    line.setStrokeWidth(5);
    line.setStroke(Color.AQUA);
    line.setOpacity(0.3);
    //line.setRotate(45);

    Rectangle rectangle = new Rectangle();
    rectangle.setX(100);
    rectangle.setY(300);
    rectangle.setWidth(100);
    rectangle.setHeight(100);
    root.getChildren().add(rectangle);
    rectangle.setFill(Color.DARKOLIVEGREEN);
    rectangle.setStrokeWidth(5); //kant rundt kvadrat
    rectangle.setStroke(Color.BLACK);

    Polygon triangle = new Polygon();
    triangle.getPoints().setAll(
        200.0,200.0,
        300.0,300.0,
        200.0,300.0);
    triangle.setFill(Color.GREENYELLOW);
    root.getChildren().add(triangle);

    Circle circle = new Circle();
    circle.setCenterX(400);
    circle.setCenterY(400);
    circle.setRadius(50);
    circle.setFill(Color.YELLOWGREEN);
    root.getChildren().add(circle);

    stage.setTitle("Et dukkehjem");
    stage.setWidth(1080);
    stage.setHeight(700);

    Image icon = new Image("Ladder.png");
    stage.getIcons().add(icon);

    Image image = new Image("Ladder.png");
    ImageView imageView = new ImageView(image);
    imageView.setX(200);
    imageView.setY(200);
    imageView.setFitHeight(300);
    imageView.setFitWidth(300);
    root.getChildren().add(imageView);

    //stage.setX(500);
    //stage.setY(200);
    //stage.setFullScreen(true);
    stage.setFullScreenExitHint("Press q or esc to quit, ya feel blud?");
    stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("q"));

    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}




