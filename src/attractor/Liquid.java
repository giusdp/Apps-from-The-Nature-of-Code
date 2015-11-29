package attractor;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/** Created by Giuseppe on 19/09/2015. */
public class Liquid {

    // Liquid is a rectangle
    public double x,y,w,h;
    // Coefficient of drag
    public double dragCoefficient;

    public Rectangle rekt;

    public Liquid(double x, double y, double w, double h, double dragCoefficient) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.dragCoefficient = dragCoefficient;

        rekt = new Rectangle(x, y, w, h);
        rekt.setFill(Color.AQUAMARINE);

    }

    // Is the Mover in the Liquid?
    public boolean contains(Mover mover) {
        Vector2D location = mover.location;
        return location.x > x && location.x < x + w && location.y > y && location.y < y + h;
    }

    // Calculate drag force
    public Vector2D drag(Mover mover) {
        // Magnitude is coefficient * speed squared
        double speed = mover.velocity.mag();
        double dragMagnitude = dragCoefficient * speed * speed;

        // Direction is inverse of velocity
        Vector2D dragForce = mover.velocity.get();
        dragForce.mul(-1);

        // Scale according to magnitude
        // dragForce.setMag(dragMagnitude);
        dragForce.normalize();
        dragForce.mul(dragMagnitude);
        return dragForce;
    }
}
