package path;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

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

    double angle;

    Pane layer = null;

    public WorldObject( Pane layer, Vector2D location, double width, double height) {

        this.layer = layer;

        this.location = location;
        this.velocity = new Vector2D(0,0);
        this.acceleration = new Vector2D(0,0);
        this.width = width;
        this.height = height;
        this.centerX = width / 2;
        this.centerY = height / 2;

        this.view = createView();

        setPrefSize(width, height);

        // add view to this node
        getChildren().add(view);

        // add this node to layer
        layer.getChildren().add(this);

        display();

    }

    public abstract Node createView();

    public void applyForce(path.Vector2D force) {
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

    /**
     * Move sprite towards target
     */
    public void seek(Vector2D target) {

        Vector2D desired = Vector2D.sub(target, location);

        // The distance is the magnitude of the vector pointing from location to target.

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

        applyForce(steer);
    }

    /*
    public void follow(FlowField flowField){
        flowfield.Vector2D desired = flowField.lookup(location);
        desired.mul(maxSpeed);
        flowfield.Vector2D steer = flowfield.Vector2D.sub(desired, velocity);
        steer.limit(maxForce);
        applyForce(steer);
    }
    */

    public void follow(Path path){
        Vector2D predict = velocity.copy();
        predict.normalize();
        predict.mul(25);
        Vector2D predictLoc = Vector2D.add(location, predict);

        Vector2D a = path.startPoint;
        Vector2D b = path.endPoint;
        Vector2D normalPoint = getNormalPoint(predictLoc, a, b);

        Vector2D dir = Vector2D.sub(b, a);
        dir.normalize();
        dir.mul(10);
        Vector2D target = Vector2D.add(normalPoint, dir);

        double distance = Vector2D.dist(normalPoint, predictLoc);
        if (distance > path.radius) seek(target);
    }

    private Vector2D getNormalPoint(Vector2D p, Vector2D a, Vector2D b){
        Vector2D ap = Vector2D.sub(p, a);
        Vector2D ab = Vector2D.sub(b, a);
        ab.normalize();
        ab.mul(ap.dot(ab));
        return Vector2D.add(a, ab);
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
