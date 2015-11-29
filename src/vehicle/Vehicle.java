package vehicle;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

/** Created by Giuseppe on 23/09/2015. */
public class Vehicle extends WorldObject {

    public Vehicle(Pane layer, Vector2D location, Vector2D velocity, Vector2D acceleration, double width, double height) {
        super(layer, location, velocity, acceleration, width, height);
    }

    @Override
    public Node createView() {
        return Utils.createArrowImageView( (int) width);
    }
}
