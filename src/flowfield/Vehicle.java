package flowfield;


import javafx.scene.Node;
import javafx.scene.layout.Pane;

/** Created by Giuseppe on 23/09/2015. */
public class Vehicle extends WorldObject {

    public Vehicle(Pane layer, Vector2D location, double width, double height) {
        super(layer, location, width, height);
    }

    @Override
    public Node createView() {
        return Utils.createArrowImageView(width);
    }
}
