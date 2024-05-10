package javafxlecture;

import java.util.LinkedList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SnakeGame extends Application {

    public static final double WIDTH = 800;//game window size
    public static final double HEIGHT = 600;
    public static final int CELLSIZE = 20;
    public int score = 0;
    private Timeline gameLoop;

    private Snake snake;
    private Food food;

    

    @Override
    public void start(Stage primaryStage) {
        final boolean[] gameDone = {false};
        Group root = new Group();
        Scene scene = new Scene(root, WIDTH, HEIGHT, Color.BLACK);
        Label scoreLabel = new Label("Score: "+score);
        
        root.getChildren().add(scoreLabel);
        snake = new Snake(WIDTH / 2, HEIGHT / 2, CELLSIZE);
        food = new Food(CELLSIZE);

        LinkedList<Rectangle> body = snake.getBody();//makes list for body
        root.getChildren().addAll(body);
        root.getChildren().addAll(food.getShape());//adds body to the scene

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("done");
        alert.setHeaderText(null);
        alert.setContentText("Game Over!");//attempt at live score on screen

        //movement\/\/

        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            if (keyCode == KeyCode.LEFT) {
                snake.setDirection(-1, 0);
                if (snake.getHeadX()<0) {
                    gameDone[0]=true;
                }
            } else if (keyCode == KeyCode.RIGHT) {
                snake.setDirection(1, 0);
                if (snake.getHeadX()>WIDTH) {
                    gameDone[0]=true;
                }
            } else if (keyCode == KeyCode.UP) {
                snake.setDirection(0, -1);
                if (snake.getHeadY()<0) {
                    gameDone[0]=true;
                }
            } else if (keyCode == KeyCode.DOWN) {
                snake.setDirection(0, 1);
                if (snake.getHeadY()<HEIGHT) {
                    gameDone[0]=true;
                }
            }
        });
        


        gameLoop = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> {//movement and interaction loop
            snake.move();
            if (snake.intersects(food.getBounds())) {
                score = score+1;
                scoreLabel.setText("Score: " + score);
                System.out.println("Score: "+score);
                snake.grow();
                root.getChildren().add(snake.getNewSeg());
                food.relocate();
            }

            

            final boolean atRightBorder = snake.getHeadX() >= (WIDTH);
            final boolean atLeftBorder = snake.getHeadX() <= 0;
            final boolean atBottomBorder = snake.getHeadY() >= (HEIGHT);
            final boolean atTopBorder = snake.getHeadY() <= 0;

            if (atRightBorder || atLeftBorder || atBottomBorder || atTopBorder) {
                
                gameLoop.stop();
                String imageUrl = "/newsnakegame/gameOver.jpg";
                Image gameOverImage = new Image(imageUrl); 
                ImageView imageView = new ImageView(gameOverImage);
                imageView.setLayoutX(400); 
                imageView.setLayoutY(300); 
                root.getChildren().add(imageView);
                
            }
            

        }));
        

        

        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();

        primaryStage.setScene(scene);
        primaryStage.setTitle("Snake Game");
        primaryStage.show();
    }
    

    public static void main(String[] args) {
        launch(args);
    }
}
