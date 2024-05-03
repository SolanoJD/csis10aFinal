package javafxlecture.util;

import javafx.scene.paint.Color;

public class ColorUtil {

  private ColorUtil() {
  }

  public static String toHex(int r, int g, int b) {
    return String.format("#%02x%02x%02x", r, g, b);
  }

  public static String toHex(int r, int g, int b, int a) {
    return String.format("#%02x%02x%02x%02x", r, g, b, a);
  }

  public static String stringToHexColor(String s) {
    var hash = s.hashCode();
    var color = intToRGBA(hash);
    return toHex(asInt(color.getRed()), asInt(color.getGreen()), asInt(color.getBlue()), asInt(color.getOpacity()));
  }

  public static Color stringToColor(String s) {
    var hash = s.hashCode();
    return intToRGBA(hash);
  }

  private static Color intToRGBA(int i) {
    var a = brighten((i >> 24) & 0xff, 128);
    var r = brighten((i >> 16) & 0xff, 32);
    var g = brighten((i >> 8) & 0xff, 32);
    var b = brighten(i & 0xff, 32);
    System.out.println(toHex(r, g, b));
    return new Color(r / 255D, g / 255D, b / 255D, a / 255D);
  }

  private static int asInt(double color) {
    return (int) Math.round(color * 255);
  }

  public static int brighten(int color, int min) {
    if (min > 255) {
      min = 255;
    }
    else if (min < 0) {
      min = 0;
    }
    return (int) Math.round(((color + min) / (255D + min)) * 255);
  }
  
}
