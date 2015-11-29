package pendulum;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/** Created by Giuseppe on 20/09/2015. */
public class PendulumMain extends Application {
    public static final double SCENE_WIDTH = 800, SCENE_HEIGHT = 600;

    public static void main(String[] args){ launch(args);}

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Pendulum Test");

        Group root = new Group();

        Pendulum pendulum = new Pendulum(new Vector2D(SCENE_WIDTH / 2, 50), 300);
        root.getChildren().addAll(pendulum.line, pendulum.circle);

        AnimationTimer appLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                pendulum.update();
            }
        };

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setScene(scene);

        appLoop.start();
        primaryStage.show();

    }
}
