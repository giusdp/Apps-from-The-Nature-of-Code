package mover;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/** Created by Giuseppe on 18/09/2015. */
public class Mover {

    private Vector2D location, velocity, acceleration, mousePosition;
    private Circle circle;
    private double maxSpeed;

    public Mover(Vector2D location, double maxSpeed) {
        this.location = location;
        this.maxSpeed = maxSpeed;

        velocity = new Vector2D(0,0);
        acceleration = new Vector2D(0,0);
        mousePosition = new Vector2D(0,0);

        circle = new Circle(20, 20, 20, Color.BLACK);
        move();
    }

    public Circle getCircle(){ return circle; }

    public void setMousePosition(Vector2D mousePosition){
        this.mousePosition = mousePosition;
    }

    public void update(){

        Vector2D direction = Vector2D.sub(mousePosition, location);
        direction.normalize();
        direction.mul(0.5);
        acceleration = direction;

        velocity.add(acceleration);
        velocity.limit(maxSpeed);
        location.add(velocity);

        checkBounds();
        move();
    }

    public void move(){
        circle.relocate(location.x, location.y);
    }

    private void checkBounds(){
        if (location.x < 0) location.x = MoverMain.SCENE_WIDTH;
        else if (location.x > MoverMain.SCENE_WIDTH) location.x = 0;

        if (location.y < 0) location.y = MoverMain.SCENE_HEIGHT;
        else if (location.y > MoverMain.SCENE_HEIGHT) location.y = 0;
    }



}
