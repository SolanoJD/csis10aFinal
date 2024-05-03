package javafxlecture;
import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Created by brian on 5/16/14.
 */
public class IPathAnimation extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Basic Scene Graph Demo");

        Group root = new Group();

        // -- 1. Build a Path using helper elements
        Path path = new Path();
        root.getChildren().add(path); // comment this line to hide path from stage

        MoveTo moveTo = new MoveTo();
        moveTo.setX(300.0f);
        moveTo.setY(300.0f);

        HLineTo hLineTo = new HLineTo();
        hLineTo.setX(70.0f);

        QuadCurveTo quadCurveTo = new QuadCurveTo();
        quadCurveTo.setX(220.0f);
        quadCurveTo.setY(160.0f);
        quadCurveTo.setControlX(100.0f);
        quadCurveTo.setControlY(0.0f);

        LineTo lineTo = new LineTo();
        lineTo.setX(175.0f);
        lineTo.setY(75.0f);

        ArcTo arcTo = new ArcTo();
        arcTo.setX(50.0f);
        arcTo.setY(70.0f);
        arcTo.setRadiusX(50.0f);
        arcTo.setRadiusY(50.0f);

        path.getElements().add(moveTo);
        path.getElements().add(hLineTo);
        path.getElements().add(quadCurveTo);
        path.getElements().add(lineTo);
        path.getElements().add(arcTo);

        // -- 2. Create a circle
        Circle circle = new Circle(100, 100, 20, Color.DARKORCHID);
        root.getChildren().add(circle);

        // -- 3. Animate cirlce along path
        PathTransition pathTransition = new PathTransition(Duration.seconds(5), path, circle);
        pathTransition.setCycleCount(10);
        pathTransition.setAutoReverse(true);
        pathTransition.play();

        // -- 4. For fun let's change the size too
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(5), circle);
        scaleTransition.setToX(0.2);
        scaleTransition.setToY(0.2);
        scaleTransition.setCycleCount(10);
        scaleTransition.setAutoReverse(true);
        scaleTransition.play();

        Scene scene = new Scene(root, 400, 400, Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}