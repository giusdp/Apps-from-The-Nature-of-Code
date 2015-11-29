package repeller.structure;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import repeller.Vector2D;

/** Created by Giuseppe on 21/09/2015. */
public class Rect extends Particle {

    public Rect(Vector2D origin) {
        super(origin, new Rectangle(10, 10, Color.BLACK), 2);
    }

    @Override
    public void run() {
        super.run();
        shape.setOpacity(lifeSpan);
    }
}
