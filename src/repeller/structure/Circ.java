package repeller.structure;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import repeller.Vector2D;

/** Created by Giuseppe on 21/09/2015. */
public class Circ extends Particle {

    public Circ(Vector2D origin) {
        super(origin, new Circle(5, Color.BLACK), 2);
    }

    @Override
    public void run() {
        super.run();
        shape.setOpacity(lifeSpan);
    }
}
