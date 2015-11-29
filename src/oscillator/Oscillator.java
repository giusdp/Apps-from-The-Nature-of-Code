package oscillator;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/** Created by Giuseppe on 19/09/2015. */
public class Oscillator {
    public Vector2D angle, velocity, amplitude;
    public Circle circle;
    public Line line;

    public Oscillator() {
        angle = new Vector2D(0,0);
        velocity = new Vector2D(.1, .1);
        amplitude = new Vector2D(100, 100);

        circle = new Circle(20, Color.BLACK);
        line = new Line(OscillatorMain.SCENE_WIDTH/2, 100, 0, 0);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(2);
    }

    public void oscillate() {
        angle.add(velocity);
    }

    public void update() {
        oscillate();

        double x = Math.sin(angle.x) * amplitude.x; //Oscillating on the x-axis
        double y = Math.sin(angle.y) * amplitude.y; //Oscillating on the y-axis

        //changed y to 0 just for fun
        y = 0;
        circle.relocate(x + OscillatorMain.SCENE_WIDTH / 2, y + OscillatorMain.SCENE_HEIGHT / 2);
        line.setEndX(x + OscillatorMain.SCENE_WIDTH / 2 + circle.getRadius()); // its way better line.setEndX(circle.getCenterX) and centerY
        line.setEndY(y + OscillatorMain.SCENE_HEIGHT / 2 + circle.getRadius()); // but i didnt set the centers and i dont feel like it so...
    }
}
