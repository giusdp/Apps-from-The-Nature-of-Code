package repeller.structure;

import javafx.scene.shape.Shape;
import repeller.Vector2D;

/** Created by Giuseppe on 21/09/2015. */
public class Particle {

    protected Vector2D location, velocity, acceleration;
    protected double mass, lifeSpan, countDown;

    protected Shape shape;

    public Particle(Vector2D origin, Shape shape, double mass) {
        this.location = origin.get();
        this.shape = shape;
        this.mass = mass;

        shape.relocate(location.x, location.y);
        acceleration = new Vector2D(0,0);
        velocity = new Vector2D(Math.random() * 2 - 1, Math.random() - 2);

        lifeSpan = 1;
        countDown = 0.002;
    }


    public void applyForce(Vector2D forceToApply){
        Vector2D f = forceToApply.get();
        f.div(mass);
        acceleration.add(f);
    }

    public Shape getShape(){ return shape; }
    public Vector2D getLocation() { return location; }

    public void run() {
        velocity.add(acceleration);
        location.add(velocity);
        shape.relocate(location.x, location.y);
        acceleration.mul(0);
        lifeSpan -= countDown;
    }

    public boolean isDead(){ return lifeSpan <= 0.0; }

}
