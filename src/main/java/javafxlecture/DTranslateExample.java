package javafxlecture;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Created by brian on 5/17/14.
 *
 * See http://docs.oracle.com/javase/8/javafx/api/javafx/animation/Transition.html for other transitions
 * and example on implementing your own custom view
 */
public class DTranslateExample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle(getClass().getSimpleName());
        Group root = new Group();
        Scene scene = new Scene(root, 400, 400, Color.CORAL);


        // -- 1. add a circle to scene
        Circle circle = new Circle(20);
        root.getChildren().add(circle);

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(3), circle);
        translateTransition.setToX(400);
        translateTransition.setToY(400);
        translateTransition.setCycleCount(10);
        translateTransition.setAutoReverse(true);
        translateTransition.play();

        // -- 4. Add the scene to the stage and all show to make if visible
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
