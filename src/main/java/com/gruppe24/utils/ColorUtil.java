package com.gruppe24.utils;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Utility class for color conversion and manipulation.
 * Provides methods to convert color names to Color objects and vice versa.
 */
public class ColorUtil {

  /**
   * Converts a color name to a Color object.
   *
   * @param colorName the name of the color
   * @return the corresponding Color object, or Color.RED if the name is not recognized
   */
  public static Color getColorFromString(String colorName) {
    if (colorName == null) {
      return Color.RED;
    }

    return switch (colorName.toLowerCase()) {
      case "red" -> Color.DARKRED;
      case "blue" -> Color.BLUE;
      case "green" -> Color.DARKGREEN;
      case "yellow" -> Color.YELLOW;
      case "purple" -> Color.PURPLE;
      default -> Color.DARKRED;
    };
  }

  /**
   * Converts a Color object to its corresponding color name.
   *
   * @param paint the Color object
   * @return the name of the color, or "unknown" if the color is not recognized
   */
  public static String getColorName(Paint paint) {
    if (paint.equals(Color.DARKRED) || paint.equals(Color.RED)) {
      return "red";
    }
    if (paint.equals(Color.BLUE)) {
      return "blue";
    }
    if (paint.equals(Color.DARKGREEN) || paint.equals(Color.GREEN)) {
      return "green";
    }
    if (paint.equals(Color.YELLOW)) {
      return "yellow";
    }
    if (paint.equals(Color.PURPLE)) {
      return "purple";
    }
    return "unknown";
  }
}