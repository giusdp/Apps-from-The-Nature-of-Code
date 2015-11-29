package attractor;
/** Created by Giuseppe on 19/09/2015. */

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AttractorMain extends Application {

    public static final double SCENE_WIDTH = 800, SCENE_HEIGHT = 600;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Attractor Test");

        Group root = new Group();

        Attractor attractor = new Attractor(new Vector2D(480, 200));
        Mover mover = new Mover(new Vector2D(SCENE_WIDTH / 2,0), 20, 10);
        Liquid liquid = new Liquid(0, SCENE_HEIGHT / 2, SCENE_WIDTH, 300, 0.2);

        root.getChildren().addAll(attractor.attractor, liquid.rekt, mover.circle);

        AnimationTimer appLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                mover.applyForce(attractor.attract(mover));
                if (liquid.contains(mover)) mover.applyForce(liquid.drag(mover));
                mover.update();
            }
        };

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setScene(scene);

        appLoop.start();
        primaryStage.show();
    }
}
