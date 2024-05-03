package javafxlecture;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by brian on 5/16/14.
 */
public class FEventsAgain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle(getClass().getSimpleName());

        final Group root = new Group();
        Scene scene = new Scene(root, 400, 400, Color.WHEAT);

        // -- 1. Add a cirle node
        final Circle circle = new Circle(30, Color.RED);
        circle.setCenterX(200);
        circle.setCenterY(200);
        root.getChildren().add(circle);

        // -- 2. Toggle the circles' color when it's clicked
        circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (circle.getFill().equals(Color.RED)) {
                    circle.setFill(Color.BLUE);
                }
                else {
                    circle.setFill(Color.RED);
                }
            }
        });


        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
