package javafxlecture.timeline;

import java.util.Random;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafxlecture.util.ColorUtil;

public class ASimpleTimeline extends Application {

  Pane root = new Pane();

  public ASimpleTimeline() {
    build();
  }

  private void build() {
    var pane = new Pane();
    pane.setStyle("-fx-background-color: #ffffff;");

    // Draw the main line from left to right
    var offset = 10;
    var endXProperty = new SimpleDoubleProperty();
    endXProperty.bind(pane.widthProperty().subtract(offset));

    var horizontalAxis = new Line(offset, offset * 3, endXProperty.get(), offset * 3);
    horizontalAxis.endXProperty().bind(endXProperty);
    
    horizontalAxis.setStyle("-fx-stroke: #333333; -fx-stroke-width: 2px;");
    pane.getChildren().add(horizontalAxis);

    // Draw the minute lines
    int numMinutes = 15;
    var distanceBetweenMinutesProp = pane.widthProperty().divide(numMinutes);
    for (int i = 0; i <= numMinutes; i++) {

      var minuteLine = new Line();
      minuteLine.startXProperty().bind(distanceBetweenMinutesProp.multiply(i).add(offset));
      minuteLine.endXProperty().bind(minuteLine.startXProperty());

      minuteLine.startYProperty().bind(horizontalAxis.startYProperty().subtract(offset));
      minuteLine.endYProperty().bind(horizontalAxis.startYProperty().add(offset));
      minuteLine.setStyle("-fx-stroke: #333333; -fx-stroke-width: 2px;");
      pane.getChildren().add(minuteLine);

      var label = new Label("" + i);
      label.layoutXProperty().bind(minuteLine.startXProperty().subtract(label.widthProperty().divide(2)));
      label.layoutYProperty().bind(minuteLine.startYProperty().subtract(offset * 2));
      label.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");
      pane.getChildren().add(label);

    }

    var random = new Random();
    for (int i = 0; i <= 1000; i++) {
      var time = random.nextDouble(15);
      var xProp = distanceBetweenMinutesProp.multiply(time);

      var charCode = ((int)(Math.random()*100)%26+65);
      char uppercaseChar = (char) charCode;
      var label = new Label(uppercaseChar + "");
      label.layoutXProperty().bind(xProp.subtract(label.widthProperty().divide(2)));
      label.layoutYProperty().bind(horizontalAxis.startYProperty().add(offset * 2).add((charCode - 65) * 4));
      label.setStyle("-fx-font-weight: bold; -fx-font-size: 12px;");
      var fill = ColorUtil.stringToColor(random.nextInt(10000) + "" + uppercaseChar + uppercaseChar + uppercaseChar + uppercaseChar + "");
      label.setTextFill(fill);
      label.setTooltip(new Tooltip(uppercaseChar + " at " + time + " minutes"));
      pane.getChildren().add(label);

      var line = new Line();
      line.startXProperty().bind(xProp);
      line.endXProperty().bind(xProp);
      line.startYProperty().bind(horizontalAxis.startYProperty());
      // line.endYProperty().bind(label.layoutYProperty().subtract(label.heightProperty().divide(2)));
      line.endYProperty().bind(label.layoutYProperty());
      var lineFill = new Color(fill.getRed(), fill.getGreen(), fill.getBlue(), 0.10);
      var lineStroke = new Color(fill.getRed(), fill.getGreen(), fill.getBlue(), 1);
      line.setStroke(lineFill);

      label.setOnMouseEntered(evt -> Platform.runLater(() -> {
        line.setStroke(lineStroke);
        line.setStrokeWidth(3);
        label.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");
      }));

      label.setOnMouseExited(evt -> Platform.runLater(() -> {
        line.setStroke(lineFill);
        line.setStrokeWidth(3);
        label.setStyle("-fx-font-weight: bold; -fx-font-size: 12px;");
      }));

      pane.getChildren().add(line);

    }

    root = pane;

  }


  public Pane getRoot() {
    return root;
  }
  



  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle(getClass().getSimpleName());
    primaryStage.setScene(new javafx.scene.Scene(getRoot()));
    primaryStage.show();
  }
  
}
