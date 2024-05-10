package javafxlecture;

import java.util.LinkedList;

import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Snake {
    private Rectangle head;
    private Rectangle bodyPart1;
    private Rectangle bodyPart2;
    private static Rectangle newTail;


   
    private LinkedList<Rectangle> body;
    public int dx = 0;//direction X
    public int dy = 0;//direction Y
    private int cellSize;//cell size for moving maths 

    public Snake(double startX, double startY, int cellSize) {
        this.cellSize = cellSize;

        head = new Rectangle(startX, startY, cellSize, cellSize);
        head.setFill(Color.GREEN);

        bodyPart1 = new Rectangle(head.getX()-(cellSize), startY, cellSize, cellSize);
        bodyPart1.setFill(Color.GREEN);
        bodyPart2 = new Rectangle(head.getX()-(2*cellSize), startY, cellSize, cellSize);
        bodyPart2.setFill(Color.GREEN);
        
        
        
    
        body = new LinkedList<>();
        body.add(head);
        body.add(bodyPart1);
        body.add(bodyPart2);


        
        
    }

    public Rectangle getHead() {
        return head;
    }
    public Rectangle getBodyPart() {
        return bodyPart1;
    }
    public Rectangle getBodyPart2() {
        return bodyPart2;
    }
    
    public Rectangle getNewSeg() {
        return newTail;
    }
    
    public LinkedList<Rectangle> getBody() {
        return body;
    }

    public void setDirection(int dx, int dy) {
        this.dx = dx * cellSize;
        this.dy = dy * cellSize;
    }
    

    

    public void move() {
        double newX = head.getX() + dx;
        double newY = head.getY() + dy;
        head.setX(newX);
        head.setY(newY);

        for (int i = body.size() - 1; i > 0; i--) {
            Rectangle current = body.get(i);// sets the last body piece as current
            Rectangle prev = body.get(i - 1);//sets the (2nd to last tp prev)
            current.setX(prev.getX());//sets the x and y of the current piece to the piece infront of it
            current.setY(prev.getY());
        }
    }

    

    public void grow() {
        Rectangle tail = body.getLast();
        double newX = tail.getX() + (dx*cellSize);
        double newY = tail.getY() + (dy*cellSize);
        Rectangle newSegment = new Rectangle(newX, newY, cellSize, cellSize);
        newSegment.setFill(Color.GREEN);
        body.add(newSegment);
        newTail = newSegment;
        
    }

    public Bounds getBounds() {
        return head.getBoundsInParent();
    }

    public boolean intersects(Bounds food) {
        return head.getBoundsInParent().intersects(food);
    }

    public double getHeadX() {
        return head.getX();
    }
    
    public double getHeadY() {
        return head.getY();
    }


    public boolean touching(double minX, double minY, double maxX, double maxY) {
        //  touching walls
        if (head.getLayoutX() < minX || head.getLayoutX() + head.getWidth() > maxX ||
            head.getLayoutY() < minY || head.getLayoutY() + head.getHeight() > maxY) {
            return true; // Collision with walls
        }

        // Check collision with itself
        for (int i = 1; i < body.size(); i++) {
            Rectangle segment = body.get(i);
            Bounds segmentBounds = segment.getBoundsInParent();
            if (head.getBoundsInParent().intersects(segmentBounds)) {
                return true; // Collision with itself
            }
        }

        return false; 
    }

        
    
}