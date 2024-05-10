package javafxlecture;

import java.util.Random;

import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Food {
    private Rectangle rect;
    private int cellSize;

    public Food(int cellSize) {
        this.cellSize = cellSize;
        rect = new Rectangle(cellSize, cellSize);
        rect.setFill(Color.RED);
        relocate();
    }

    public Rectangle getShape() {
        return rect;
    }

    public void relocate() {
        
        Random rand = new Random();
        int x = rand.nextInt((int)SnakeGame.WIDTH / cellSize) * cellSize;
        int y = rand.nextInt((int)SnakeGame.HEIGHT / cellSize) * cellSize;
        rect.relocate(x, y);
    }

        public Bounds getBounds() {
            return rect.getBoundsInParent();
        }

        public double getX() {
            return rect.getLayoutX();
        }
        public double getY() {
            return rect.getLayoutY();
        }
}

