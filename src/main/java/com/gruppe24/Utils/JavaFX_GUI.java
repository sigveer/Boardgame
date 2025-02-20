package com.gruppe24.Utils;

import javafx.scene.control.Button;

public class JavaFX_GUI {
  public static void styleButton(Button button) {
    String normalStyle =
        "-fx-background-color: linear-gradient(to bottom, #4a6cd4, #283a60); " +
            "-fx-text-fill: white; " +
            "-fx-font-size: 16px; " +
            "-fx-padding: 10px 20px; " +
            "-fx-border-radius: 5px; " +
            "-fx-background-radius: 5px; " +
            "-fx-border-color: #1f2a44; " +
            "-fx-border-width: 2px; " +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 8, 0, 2, 2);";

    String hoverStyle =
        "-fx-background-color: linear-gradient(to bottom, #5c7ee8, #2e4370); " +
            "-fx-text-fill: white; " +
            "-fx-font-size: 16px; " +
            "-fx-padding: 10px 20px; " +
            "-fx-border-radius: 5px; " +
            "-fx-background-radius: 5px; " +
            "-fx-border-color: #1f2a44; " +
            "-fx-border-width: 2px; " +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 3, 3);";

    String pressedStyle =
        "-fx-background-color: linear-gradient(to top, #283a60, #3a5590); " +
            "-fx-text-fill: white; " +
            "-fx-font-size: 16px; " +
            "-fx-padding: 12px 20px 8px 20px; " + // Endrer padding for å simulere dybde
            "-fx-border-radius: 5px; " +
            "-fx-background-radius: 5px; " +
            "-fx-border-color: #1f2a44; " +
            "-fx-border-width: 2px; " +
            "-fx-effect: innershadow(gaussian, rgba(0,0,0,0.7), 5, 0, 0, 0);";

    button.setStyle(normalStyle);

    button.setOnMouseEntered(e -> button.setStyle(hoverStyle));
    button.setOnMouseExited(e -> button.setStyle(normalStyle));
    button.setOnMousePressed(e -> button.setStyle(pressedStyle));
    button.setOnMouseReleased(e -> button.setStyle(hoverStyle));
  }
}
