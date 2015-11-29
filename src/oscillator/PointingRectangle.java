package oscillator;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import mover.MoverMain;

/** Created by Giuseppe on 19/09/2015. */
public class PointingRectangle {

    public Vector2D location, velocity, acceleration, mousePosition;
    public Rectangle rekt;

    public PointingRectangle(double x, double y, double w, double h) {
        rekt = new Rectangle(x, y, w, h);
        rekt.setFill(Color.BLACK);

        location = new Vector2D(x, y);
        velocity = new Vector2D(0,0);
        acceleration = new Vector2D(1, 1);
        mousePosition = new Vector2D(0, 0);
    }

    public void setMousePosition(Vector2D mousePosition){
        this.mousePosition = mousePosition;
    }

    public void update(){

        Vector2D direction = Vector2D.sub(mousePosition, location);
        direction.normalize();
        direction.mul(0.5);

        acceleration = direction;
        velocity.add(acceleration);

        velocity.limit(15);

        location.add(velocity);

        rekt.setRotate(Math.toDegrees(velocity.heading()));
        rekt.relocate(location.x, location.y);

        checkBounds();
    }

    private void checkBounds(){
            if (location.x < 0) location.x = MoverMain.SCENE_WIDTH;
            else if (location.x > MoverMain.SCENE_WIDTH) location.x = 0;

            if (location.y < 0) location.y = MoverMain.SCENE_HEIGHT;
            else if (location.y > MoverMain.SCENE_HEIGHT) location.y = 0;
    }
}
