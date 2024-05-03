package javafxlecture;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Created by brian on 5/20/14.
 */
public class VCanvasDemo extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle(getClass().getSimpleName());

        Group root = new Group();

        Canvas canvas = new Canvas(200, 200);
        canvas.setLayoutX(100);
        canvas.setLayoutY(50);
        draw(canvas.getGraphicsContext2D());
        root.getChildren().add(canvas);


        Scene scene = new Scene(root, 500, 300, Color.GOLD);
        primaryStage.setScene(scene);
        primaryStage.show();

        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(3), canvas);
        rotateTransition.setCycleCount(10);
        rotateTransition.setAutoReverse(true);
        rotateTransition.setFromAngle(-15);
        rotateTransition.setToAngle(15);
        rotateTransition.play();
    }

    private void draw(GraphicsContext g) {
        g.setStroke(Color.BLACK);
        g.fillOval(70, 60, 10, 10);
        g.fillOval(130, 60, 10, 10);
        g.fillOval(100, 90, 5, 5);
        g.strokeArc(60, 75, 90, 80, 180, 180, ArcType.OPEN);
        for (int i = 0; i < 30; i++) {
            g.strokeLine(i * 3 + 60, 40, i * 3 + 60, 35);
        }
    }
}
