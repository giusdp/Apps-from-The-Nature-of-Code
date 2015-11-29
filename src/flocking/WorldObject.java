package flocking;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

import java.util.List;

/** Created by Giuseppe on 23/09/2015. */
public abstract class WorldObject extends Region {

    Vector2D location, velocity, acceleration;

    double maxSpeed = Constants.MAX_SPEED;
    double maxForce = Constants.MAX_FORCE;

    Node view;

    // view dimensions
    double width;
    double height;
    double centerX;
    double centerY;
    double radius;
    double neighbourDist;

    double angle;

    Pane layer = null;

    public WorldObject( Pane layer, Vector2D location, double width, double height, double radius, double neighbourDist) {

        this.layer = layer;

        this.location = location;
        this.velocity = new Vector2D(0,0);
        this.acceleration = new Vector2D(0,0);
        this.width = width;
        this.height = height;
        this.centerX = width / 2;
        this.centerY = height / 2;
        this.radius = radius;
        this.neighbourDist = neighbourDist;

        this.view = createView();

        setPrefSize(width, height);

        // add view to this node
        getChildren().add(view);

        // add this node to layer
        layer.getChildren().add(this);

        display();

    }

    public abstract Node createView();

    public void applyForce(Vector2D force) {
        acceleration.add(force);
    }

    public void move() {

        // set velocity depending on acceleration
        velocity.add(acceleration);

        // limit velocity to max speed
        velocity.limit(maxSpeed);

        // change location depending on velocity
        location.add(velocity);

        // angle: towards velocity (ie target)
        angle = velocity.heading();

        // clear acceleration
        acceleration.mul(0);
    }

    public void flock(List<? extends WorldObject> worldObjects){
        Vector2D sep = separate(worldObjects);
        Vector2D ali = align(worldObjects);
        Vector2D coh = cohesion(worldObjects);

        sep.mul(1.5);
        ali.mul(1.0);
        coh.mul(1.0);

        applyForce(sep);
        applyForce(ali);
        applyForce(coh);
    }

    private Vector2D separate(List<? extends WorldObject> worldObjects) {
        double desiredSep = radius * 2;

        Vector2D sum = new Vector2D(0, 0);
        int count = 0;
        for (WorldObject other : worldObjects) {
            double d = Vector2D.dist(location, other.location);
            if ((d > 0) && (d < desiredSep)) {
                Vector2D diff = Vector2D.sub(location, other.location);
                diff.normalize();
                diff.div(d);
                sum.add(diff);
                count++;
            }
        }
        if (count > 0) {
            sum.div(count);
            sum.normalize();
            sum.mul(maxSpeed);
            Vector2D steer = Vector2D.sub(sum, velocity);
            steer.limit(maxForce);
            return steer;
        } else return new Vector2D(0,0);
    }

    public Vector2D align(List<? extends WorldObject> worldObjects){
        Vector2D sum = new Vector2D(0,0);
        int count = 0;
        for (WorldObject other : worldObjects) {
            double d = Vector2D.dist(location,other.location);
            if ((d > 0) && (d < neighbourDist)) {
                sum.add(other.velocity);
                count++;
            }
        }
        if (count > 0) {
            sum.div(count);
            sum.normalize();
            sum.mul(maxSpeed);
            Vector2D steer = Vector2D.sub(sum,velocity);
            steer.limit(maxForce);
            return steer;
        } else return new Vector2D(0,0);
    }

    public Vector2D cohesion(List<? extends WorldObject> worldObjects) {

        Vector2D sum = new Vector2D(0,0);
        int count = 0;
        for (WorldObject other : worldObjects) {
            double d = Vector2D.dist(location,other.location);
            if ((d > 0) && (d < neighbourDist)) {
                sum.add(other.location);
                count++;
            }
        }
        if (count > 0) {
            sum.div(count);
            return seek(sum);
        } else return new Vector2D(0,0);
    }



    /** Move object towards target */
    public Vector2D seek(Vector2D target) {

        Vector2D desired = Vector2D.sub(target, location); // The distance is the magnitude of the vector pointing from location to target.
        double distance = desired.mag();
        desired.normalize();

        // If we are closer than 100 pixels...
        if (distance < Constants.SLOW_DOWN_DISTANCE) {
            // ...set the magnitude according to how close we are.
            desired.mul(Utils.map(distance, 0, Constants.SLOW_DOWN_DISTANCE, 0, maxSpeed));
        }
        // Otherwise, proceed at maximum speed.
        else {
            desired.mul(maxSpeed);
        }

        // The usual steering = desired - velocity
        Vector2D steer = Vector2D.sub(desired, velocity);
        steer.limit(maxForce);

        return steer;
    }

    /**
     * Update node position
     */
    public void display() {

        relocate(location.x - centerX, location.y - centerY);

        setRotate(Math.toDegrees(angle));

    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public Vector2D getLocation() {
        return location;
    }

    public void setLocation( double x, double y) {
        location.x = x;
        location.y = y;
    }

    public void setLocationOffset( double x, double y) {
        location.x += x;
        location.y += y;
    }

}
