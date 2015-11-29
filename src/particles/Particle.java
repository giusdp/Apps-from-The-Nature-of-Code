package particles;

import javafx.scene.shape.Shape;

/** Created by Giuseppe on 20/09/2015. */
public class Particle {

    public Vector2D location, velocity, acceleration;
    double lifeSpan, minus;

    public Shape shape;

    public Particle(Shape shape, Vector2D location){
        this.shape = shape;
        this.location = location.get();
        this.lifeSpan = 1.0;
        this.minus = .007;

        this.shape.relocate(location.x, location.y);

        velocity = new Vector2D(0, 0);
        acceleration = new Vector2D(Math.random() - .5, Math.random() - .5);
    }

    public void update(){
        velocity.add(acceleration);
        location.add(velocity);
        shape.relocate(location.x, location.y);
        lifeSpan -= minus;

        shape.setOpacity(lifeSpan);
    }

    public void applyForce(Vector2D forceToApply){
        acceleration.add(forceToApply);
    }

    public boolean isDead(){ return lifeSpan <= 0.0;}
}
