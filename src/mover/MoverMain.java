package mover;

/** Created by Giuseppe on 18/09/2015. */

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class MoverMain extends Application {

    public static final double SCENE_WIDTH = 800, SCENE_HEIGHT = 600;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Mover Test");
        Group root = new Group();

        final List<Mover> movers = new ArrayList<>();
        for (int i = 0; i < 6; i++){
            Mover mover = new Mover(new Vector2D(Math.random() * SCENE_WIDTH, Math.random() * SCENE_HEIGHT), 10);
            movers.add(mover);
            root.getChildren().add(mover.getCircle());
        }

        AnimationTimer appLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                movers.stream().forEach(Mover::update);
            }
        };


        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        scene.addEventHandler(MouseEvent.ANY, event ->
                movers.stream().forEach(mover -> mover.setMousePosition(new Vector2D(event.getSceneX(), event.getSceneY())))
                );

        primaryStage.setScene(scene);

        appLoop.start();
        primaryStage.show();

    }
}
