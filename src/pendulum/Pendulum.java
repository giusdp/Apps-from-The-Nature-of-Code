package pendulum;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/** Created by Giuseppe on 20/09/2015. */
public class Pendulum {

    public Vector2D location; // Location of bob
    public Vector2D origin; // Location of arm origin
    public double r; // Length of arm
    public double angle; // Pendulum arm angle
    public double aVelocity; // Angle velocity
    public double aAcceleration; // Angle acceleration
    public final double damping = 0.995; //An arbitrary damping so that the Pendulum slows over time
    public final double gravity = 0.4;

    public Circle circle;
    public Line line;

    public Pendulum(Vector2D origin, double r) {
        this.origin = origin.get();
        location = new Vector2D(0,0);
        this.r = r;
        angle = Math.PI/4;
        aVelocity = 0.0;
        aAcceleration = 0.0;

        line = new Line(origin.x, origin.y, location.x, location.y);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(2);
        circle = new Circle(30, Color.BLACK);
    }

    void update() {
        location.set(r * Math.sin(angle), r * Math.cos(angle));
        location.add(origin);

        aAcceleration = (-1 * gravity / r) * Math.sin(angle); //Formula we worked out for angular acceleration
        aVelocity += aAcceleration; // Standard angular motion algorithm
        angle += aVelocity;
        aVelocity *= damping; //Apply some damping.

        line.setEndX(location.x + circle.getRadius());
        line.setEndY(location.y + circle.getRadius());
        circle.relocate(location.x, location.y);
    }
}
