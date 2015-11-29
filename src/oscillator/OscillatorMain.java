package oscillator;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mover.*;

/** Created by Giuseppe on 19/09/2015. */
public class OscillatorMain extends Application {

    public static final double SCENE_WIDTH = 800, SCENE_HEIGHT = 600;

    public static void main(String[] args){ launch(args);}

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Oscillator Test");

        Group root = new Group();

        Oscillator oscillator = new Oscillator();
        root.getChildren().addAll(oscillator.circle, oscillator.line);

        PointingRectangle pointingRectangle = new PointingRectangle(SCENE_WIDTH / 2, SCENE_HEIGHT / 2, 10, 20);
        root.getChildren().add(pointingRectangle.rekt);

        AnimationTimer appLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                oscillator.update();
                pointingRectangle.update();
            }
        };

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setScene(scene);

        scene.addEventHandler(MouseEvent.ANY, event ->
                pointingRectangle.setMousePosition(new Vector2D(event.getSceneX(), event.getSceneY())));

        appLoop.start();
        primaryStage.show();

    }
}
