package attractor;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/** Created by Giuseppe on 19/09/2015. */
public class Attractor {

    public double mass;    // Mass, tied to size
    public double G;       // Gravitational Constant
    public Vector2D location;   // Location

    public Circle attractor;

    public Attractor(Vector2D location) {
        this.location = location;
        mass = 20;
        G = 1;
        attractor = new Circle(30, Color.GREEN);
        attractor.relocate(location.x, location.y);
    }

    public Vector2D attract(Mover mover) {
        Vector2D force = Vector2D.sub(location, mover.location);   // Calculate direction of force
        double d = force.mag();         // Distance between objects
        if (d < 5) d = 5;               // Limiting the distance to eliminate
        else if (d > 25) d = 25;        // "extreme" results for very close or very far objects
        force.normalize();              // Normalize vector (distance doesn't matter here, we just want this vector for direction)
        double strength = (G * mass * mover.mass) / (d * d);  // Calculate gravitional force magnitude
        force.mul(strength);                                  // Get force vector --> magnitude * direction
        return force;
    }
}
