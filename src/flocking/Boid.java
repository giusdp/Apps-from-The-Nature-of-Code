package flocking;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

/** Created by Giuseppe on 23/09/2015. */
public class Boid extends WorldObject {

    public Boid(Pane layer, Vector2D location, double width, double height, double radius, double neighbourDist) {
        super(layer, location, width, height, radius, neighbourDist);
    }

    @Override
    public Node createView() {
        return flocking.Utils.createArrowImageView(width);
    }
}
