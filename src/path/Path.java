package path;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/** Created by Giuseppe on 23/09/2015. */
public class Path {

    public Vector2D startPoint, endPoint;
    public double radius;

    Line line;

    public Path(Pane pane, Vector2D startPoint, Vector2D endPoint, double radius) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.radius = radius;
        line = new Line(startPoint.x, startPoint.y, endPoint.x, endPoint.y);

        line.setStroke(Color.BLACK);
        line.setStrokeWidth(3);

        pane.getChildren().add(line);
    }
}
