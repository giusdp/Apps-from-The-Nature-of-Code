package particles;

/** Created by Giuseppe on 20/09/2015. */

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParticlesMain extends Application {

    public static double SCENE_WIDTH = 800, SCENE_HEIGHT = 600;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Particles Test - click on the screen");

        Group root = new Group();

        final List<ParticleSystem> psList = new ArrayList<>();
        AnimationTimer appLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                psList.forEach(ps -> {
                    ps.run();
                    root.getChildren().add(ps.addParticle());
                });
            }
        };

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> psList.add(new ParticleSystem(new Vector2D(event.getSceneX(), event.getSceneY()))));

        primaryStage.setScene(scene);

        appLoop.start();
        primaryStage.show();
    }

}
