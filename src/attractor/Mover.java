package attractor;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/** Created by Giuseppe on 18/09/2015. */
public class Mover {

    public Vector2D location, velocity, acceleration;
    public Circle circle;
    public double mass, maxSpeed;

    public Mover(Vector2D location, double mass, double maxSpeed) {
        this.location = location;
        this.mass = mass;
        this.maxSpeed = maxSpeed;

        velocity = new Vector2D(0,0);
        acceleration = new Vector2D(0,0);

        circle = new Circle(20, 20, 20, Color.BLACK);
        move();
    }

    public void applyForce(Vector2D force) {
        acceleration.add(Vector2D.div(force, mass));
    }

    public void update(){

        velocity.add(acceleration);
        velocity.limit(maxSpeed);
        location.add(velocity);

        acceleration.mul(0);

        checkBounds();
        move();
    }

    public void move(){
        circle.relocate(location.x, location.y);
    }

    private void checkBounds(){
        if (location.x < 0) {
            location.x = 0;
            velocity.x *= -1;
        } else if (location.x > AttractorMain.SCENE_WIDTH) {
            location.x = AttractorMain.SCENE_WIDTH;
            velocity.x *= -1;
        }

        if (location.y < 0) {
            location.y = 0;
            velocity.y *= -1;
        } else if (location.y > AttractorMain.SCENE_HEIGHT) {
            location.y = AttractorMain.SCENE_HEIGHT;
            velocity.y *= -1;
        }
    }

    double g = 1;
    public Vector2D attract(Mover mover) {
        Vector2D force = Vector2D.sub(location, mover.location);    // Calculate direction of force
        double distance = force.mag();                              // Distance between objects
        if (distance < 5) distance = 5;                             // Limiting the distance to eliminate "extreme"
        else if (distance > 20) distance = 25;                      // results for very close or very far objects
        force.normalize();                                          // Normalize vector (distance doesn't matter here, we just want this vector for direction

        double strength = (g * mass * mover.mass) / (distance * distance); // Calculate gravitional force magnitude
        force.mul(strength);                                        // Get force vector --> magnitude * direction
        return force;
    }



}
