package flocking;

/** Created by Giuseppe on 24/09/2015. */

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FlockingMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private final Flock flock = new Flock();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Flocking Test");

        Pane layer = new Pane();
        layer.setPrefSize(Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT);
        Scene scene = new Scene(layer, Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT);
        primaryStage.setScene(scene);

        primaryStage.show();
        prepareApp(layer);
        startApp();
    }

    private void prepareApp(Pane layer){
        final Random random = new Random();
        for (int i = 0; i < Constants.OBJ_COUNT; i++){
            double x = random.nextDouble() * layer.getWidth();
            double y = random.nextDouble() * layer.getHeight();
            flock.addObject(new Boid(layer, new Vector2D(x, y), 10, 5, 3, 50));
        }
    }

    private void startApp(){
        AnimationTimer appLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                flock.run();
                flock.objects.stream().forEach(WorldObject::move);
                flock.objects.stream().forEach(WorldObject::display);
            }
        };
        appLoop.start();
    }
}
