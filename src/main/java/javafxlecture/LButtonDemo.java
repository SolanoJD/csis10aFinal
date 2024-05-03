package javafxlecture;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Created by brian on 5/17/14.
 */
public class LButtonDemo extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250);

        // --- 1. Create a button
        final Button btn = new Button("Hello World");
        btn.setLayoutX(100);
        btn.setLayoutY(80);

        // --- 2. Add an EventHandler to do something when the button is clicked
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Hello World");
                RotateTransition rt = new RotateTransition(Duration.millis(1000), btn);
                rt.setByAngle(360);
                rt.setCycleCount(1);
                rt.play();
            }
        });
        root.getChildren().add(btn);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
