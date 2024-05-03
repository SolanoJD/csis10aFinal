package javafxlecture;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Created by brian on 5/16/14.
 */
public class BSceneGraph extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Basic Scene Graph Demo");

        Group root = new Group();
        Scene scene = new Scene(root, 400, 400, Color.WHITE);

        // -- 1. A group is not directly sizeable. It takes on the bound of it's children
        Group subnode = new Group();


        // -- 2. We can shift the node relative to it's parent's coordinates
        subnode.setLayoutX(100);
        subnode.setLayoutY(100);
        root.getChildren().add(subnode);

        // -- 3. Add some objects to subnode
        // More shapes at http://docs.oracle.com/javase/8/javafx/api/javafx/scene/shape/package-summary.html
        Rectangle rectangle = new Rectangle(50, 50);
        rectangle.setLayoutX(50);
        rectangle.setLayoutY(50);
        rectangle.setFill(Color.BLUE);
        subnode.getChildren().add(rectangle);

        Circle circle = new Circle(30);
        circle.setCenterX(100); // You could also use the layoutX property which is the upper-left corner of circle
        circle.setCenterY(90);
        circle.setFill(Color.RED);
        subnode.getChildren().add(circle);

        // -- 4. Transforms applied to a node also apply to it's children
        //RotateTransition rotateTransition = new RotateTransition(Duration.seconds(5), subnode);
        //rotateTransition.setByAngle(360);
        //rotateTransition.setCycleCount(30);
        //rotateTransition.play();


        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
