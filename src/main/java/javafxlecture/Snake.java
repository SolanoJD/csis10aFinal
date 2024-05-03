package javafxlecture;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;



public class Snake extends Application {
    public static Circle circle;
    public static Pane canvas;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // -- 1. The stage is a Window. The application provides this for you so you
        // don't need to create one
        primaryStage.setTitle(getClass().getSimpleName());

        // -- 2. You need a root node that contains ALL other nodes in the scene graph. The root node must be
        // an instance of javafx.scene.Parent. JavafX provides several subclasses of Parent: Group, Region, and WebView.
        // - Group is a node where you explicitly provide coordinates for child nodes
        // - Regions provide layout managers for child nodes. They can also be styled with CSS
        // - WebView is used to render HTML.
       // Group root = new Group();

        // -- 3. A scene is added to the stage. THe scene contains the scene graph
        // This construction takes the width, height and a background color
        canvas = new Pane();
        final Scene scene = new Scene(canvas, 800, 600);

        //Pane SHI
        //canvas.setStyle("-fx-background-color: blue;");
        


        // -- 2. We can shift the node relative to it's parent's coordinates
        //canvas.setLayoutX(400);
        //canvas.setLayoutY(600);
        //canvas.getChildren().add(canvas);

        // -- 3. Add some objects to subnode
        // More shapes at http://docs.oracle.com/javase/8/javafx/api/javafx/scene/shape/package-summary.html
        

        

        //Random Location
        Random randomNum = new Random();

        //int [] spawnerX = new int[]{10,20,30,40,50,60,70,80,90,};


        double spawnX = randomNum.nextInt(41)*10;
        double spawnY = randomNum.nextInt(41)*10;

        Random randomCirc = new Random();

        double spawnCircX = randomCirc.nextInt(41)*10;
        double spawnCircY = randomCirc.nextInt(41)*10;


        circle = new Circle(10);
        circle.setCenterX(200); // You could also use the layoutX property which is the upper-left corner of circle
        circle.setCenterY(200);
        circle.setFill(Color.RED);
        canvas.getChildren().add(circle);






        // Adds movable rectangle and makes it interact
        final Rectangle rectangle = new Rectangle((int)spawnX, (int)spawnY, 20, 20);
        canvas.getChildren().add(rectangle);
        
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.LEFT)) {
                    for (int i = 0; i < 10; i++) {
                        rectangle.setLayoutX(rectangle.getLayoutX() - 10);
                    try{
                        Thread.sleep(5000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    
                }
            }
                else if (event.getCode().equals(KeyCode.RIGHT)) {
                    for (int i = 0; i < 10; i++) {
                        rectangle.setLayoutX(rectangle.getLayoutX() + 10);
                    try{
                        Thread.sleep(5000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    
                }
                }
                else if (event.getCode().equals(KeyCode.UP)) {
                    for (int i = 0; i < 10; i++) {
                        rectangle.setLayoutY(rectangle.getLayoutY() - 10);
                    try{
                        Thread.sleep(5000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    
                }
                }
                else if (event.getCode().equals(KeyCode.DOWN)) {
                    for (int i = 0; i < 10; i++) {
                        rectangle.setLayoutY(rectangle.getLayoutY() + 10);
                    try{
                        Thread.sleep(5000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    
                }
                }
                
            }

            
        });

        final Timeline loop = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {

            

            @Override
            public void handle(final ActionEvent t) {
                

                final Bounds bounds = canvas.getBoundsInLocal();
                //final boolean go = true;
                final boolean atRectangle = circle.getBoundsInParent().intersects(rectangle.getBoundsInParent());

                
                if (atRectangle) {
                    canvas.getChildren().remove(circle);
                }
                //if(go==true){
                    //rectangle.setLayoutX(rectangle.getLayoutX() - 10);
                //}

            }
        }));
        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();

        



        // -- 4. Add the scene to the stage and all show to make if visible
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}


    

