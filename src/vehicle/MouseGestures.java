package vehicle;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/** Created by Giuseppe on 23/09/2015. */
public class MouseGestures {

    final Vector2D dragContext = new Vector2D(0, 0);

    public void makeDraggable(final WorldObject wObj) {

        wObj.setOnMousePressed(onMousePressedEventHandler);
        wObj.setOnMouseDragged(onMouseDraggedEventHandler);
        wObj.setOnMouseReleased(onMouseReleasedEventHandler);

    }

    EventHandler<MouseEvent> onMousePressedEventHandler = event -> {
        dragContext.x = event.getSceneX();
        dragContext.y = event.getSceneY();
    };

    EventHandler<MouseEvent> onMouseDraggedEventHandler = event -> {

        WorldObject wObj = (WorldObject) event.getSource();

        double offsetX = event.getSceneX() - dragContext.x;
        double offsetY = event.getSceneY() - dragContext.y;

        wObj.setLocationOffset(offsetX, offsetY);

        dragContext.x = event.getSceneX();
        dragContext.y = event.getSceneY();

    };

    EventHandler<MouseEvent> onMouseReleasedEventHandler = event -> {

    };


}
