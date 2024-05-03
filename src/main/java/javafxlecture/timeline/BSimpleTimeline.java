package javafxlecture.timeline;

import java.time.Duration;
import java.util.Random;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafxlecture.util.ColorUtil;

public class BSimpleTimeline extends Application {

	public record Anno(String name, Duration elapsedTime) {

		public DisplayedAnno toDisplayedAnno() {
			var label = new Label(name);
			var line = new Line();
			return new DisplayedAnno(this, label, line);
		}
	}

	public record DisplayedAnno(Anno anno, Label label, Line line) {

	}

	public static class Controller {
		Pane root;
		DoubleProperty endXProperty = new SimpleDoubleProperty();
		DoubleBinding distanceBetweenMinutesProperty;
		Line horizontalAxis;
		ObservableList<DisplayedAnno> displayedAnno = FXCollections.observableArrayList();

		private static final int OFFSET = 10;
		public static final int NUM_MINUTES = 15;

		public Controller() {
			init();
		}

		private void init() {
			root = new Pane();
			root.setStyle("-fx-background-color: #ffffff;");
			endXProperty.bind(root.widthProperty().subtract(OFFSET));

			horizontalAxis = new Line(OFFSET, OFFSET * 3, endXProperty.get(), OFFSET * 3);
			horizontalAxis.endXProperty().bind(endXProperty);
			horizontalAxis.setStyle("-fx-stroke: #333333; -fx-stroke-width: 2px;");
			root.getChildren().add(horizontalAxis);

			// Draw the minute lines
			int numMinutes = NUM_MINUTES;
			distanceBetweenMinutesProperty = horizontalAxis.endXProperty()
          .subtract(horizontalAxis.startXProperty())
					.divide(numMinutes);

			for (int i = 0; i <= numMinutes; i++) {

				var minuteLine = new Line();
				minuteLine.startXProperty().bind(distanceBetweenMinutesProperty.multiply(i).add(OFFSET));
				minuteLine.endXProperty().bind(minuteLine.startXProperty());

				minuteLine.startYProperty().bind(horizontalAxis.startYProperty().subtract(OFFSET));
				minuteLine.endYProperty().bind(horizontalAxis.startYProperty().add(OFFSET));
				minuteLine.setStyle("-fx-stroke: #333333; -fx-stroke-width: 2px;");
				root.getChildren().add(minuteLine);

				var label = new Label("" + i);
				label.layoutXProperty().bind(minuteLine.startXProperty().subtract(label.widthProperty().divide(2)));
				label.layoutYProperty().bind(minuteLine.startYProperty().subtract(OFFSET * 2));
				label.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");
				root.getChildren().add(label);

			}

			displayedAnno.addListener(new ListChangeListener<DisplayedAnno>() {

				@Override
				public void onChanged(Change<? extends DisplayedAnno> c) {
					while (c.next()) {
						if (c.wasAdded()) {
							c.getAddedSubList().forEach(da -> addDisplayedAnno(da));
						}
						if (c.wasRemoved()) {
							c.getRemoved().forEach(da -> {
								root.getChildren().remove(da.label());
								root.getChildren().remove(da.line());
							});
						}
					}

				}

			});

		}

		public Pane getRoot() {
			return root;
		}

		public void addAnno(Anno a) {
			var da = a.toDisplayedAnno();
			Platform.runLater(() -> displayedAnno.add(da));
		}

		public void removeAnno(Anno a) {
			var da = a.toDisplayedAnno();
			Platform.runLater(() -> displayedAnno.remove(da));
		}

		private void addDisplayedAnno(DisplayedAnno d) {
			var minutes = d.anno().elapsedTime().toMillis() / 1000.0 / 60.0;
			if (minutes > NUM_MINUTES) {
				return;
			}
			var xProp = distanceBetweenMinutesProperty.multiply(minutes).add(OFFSET);
			var label = d.label();
			var shortName = d.anno.name().charAt(0) + "";
			var charCode = (int) shortName.charAt(0);
			label.setText(shortName);
			label.layoutXProperty().bind(xProp.subtract(label.widthProperty().divide(2)));
      var incremProp = root.heightProperty()
        .subtract(OFFSET * 2)
        .subtract(horizontalAxis.startYProperty())
        .divide(26)
        .multiply(charCode - 65)
        .add(horizontalAxis.startYProperty());
			label.layoutYProperty().bind(incremProp);
			label.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
			var fillCalc = ColorUtil.stringToColor(d.anno.name());
      var fill = new Color(fillCalc.getRed(), fillCalc.getGreen(), fillCalc.getBlue(), 0.7);
			label.setTextFill(fill);
			label.setTooltip(new Tooltip(d.anno.name() + " at " + minutes + " minutes"));
			root.getChildren().add(label);

			var line = new Line();
			line.startXProperty().bind(xProp);
			line.endXProperty().bind(xProp);
			line.startYProperty().bind(horizontalAxis.startYProperty());
			line.endYProperty().bind(label.layoutYProperty());

			var lightStroke = new Color(fill.getRed(), fill.getGreen(), fill.getBlue(), 0.15);
			var heavyStroke = new Color(fill.getRed(), fill.getGreen(), fill.getBlue(), 1);
			line.setStroke(lightStroke);

			label.setOnMouseEntered(evt -> Platform.runLater(() -> {
				line.setStroke(heavyStroke);
				line.setStrokeWidth(3);
				label.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");
        label.setText(d.anno().name());
        label.setTextFill(heavyStroke);
			}));

			label.setOnMouseExited(evt -> Platform.runLater(() -> {
				line.setStroke(lightStroke);
				line.setStrokeWidth(3);
				label.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
        label.setText(d.anno().name().charAt(0) + "");
        label.setTextFill(fill);
			}));

			root.getChildren().add(line);
		}


	}

	public static void main(String[] args) {
		launch(args);

	}

  private static final String randomName(int n) {
    var sb = new StringBuilder();
    for (int i = 0; i < n; i++) {
      var charCode = ((int)(Math.random()*100)%26+65);
      char uppercaseChar = (char) charCode;
      sb.append(uppercaseChar);
    }
    return sb.toString();
  }

  private void seed(Controller controller) {
    var random = new Random();
    for (int i = 0; i <= 1000; i++) {
      var millis = random.nextLong(Controller.NUM_MINUTES * 1000 * 60);
      var name = randomName(random.nextInt(10));
      var anno = new Anno(name, Duration.ofMillis(millis));
      controller.addAnno(anno);
    }
  }


	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle(getClass().getSimpleName());
		var controller = new Controller();
    seed(controller);
		primaryStage.setScene(new javafx.scene.Scene(controller.getRoot()));
    primaryStage.setWidth(500);
    primaryStage.setHeight(500);
		primaryStage.show();
	}

}
