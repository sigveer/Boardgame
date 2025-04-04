package com.gruppe24.Utils;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


public class ColorUtil {

  public static Color getColorFromString(String colorName) {
    if (colorName == null) return Color.RED;

    return switch (colorName.toLowerCase()) {
      case "red" -> Color.DARKRED;
      case "blue" -> Color.BLUE;
      case "green" -> Color.DARKGREEN;
      case "yellow" -> Color.YELLOW;
      case "purple" -> Color.PURPLE;
      default -> Color.DARKRED;
    };
  }

  public static String getColorName(Paint paint) {
    if (paint.equals(Color.DARKRED) || paint.equals(Color.RED)) return "red";
    if (paint.equals(Color.BLUE)) return "blue";
    if (paint.equals(Color.DARKGREEN) || paint.equals(Color.GREEN)) return "green";
    if (paint.equals(Color.YELLOW)) return "yellow";
    if (paint.equals(Color.PURPLE)) return "purple";
    return "unknown";
  }
}