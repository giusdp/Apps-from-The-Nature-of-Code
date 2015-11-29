package repeller;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import repeller.structure.Particle;

/** Created by Giuseppe on 21/09/2015. */
public class Repeller {

    public Vector2D location;

    public double strength = 100;

    public Circle circle;
    public Repeller(Vector2D location) {
        this.location = location;
        circle = new Circle(20,20, 20, Color.PINK);
        circle.relocate(location.x, location.y);
    }

    public Vector2D repel(Particle p) {
        Vector2D dir =  Vector2D.sub(location, p.getLocation()); //Get force direction.

        double d = dir.mag(); // Get distance (constrain distance).
        if (d < 5) d = 5;
        else if (d > 100) d = 100;
        dir.normalize();
        double force = -1 * strength / (d * d); // Calculate magnitude.
        dir.mul(force);         // Make a vector out of direction and magnitude.
        return dir;
    }
}
