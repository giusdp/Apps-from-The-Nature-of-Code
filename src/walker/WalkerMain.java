package walker;
/** Created by Giuseppe on 18/09/2015. */

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WalkerMain extends Application {

    public static final double SCENE_WIDTH = 400, SCENE_HEIGHT = 300;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Walker Test");

        Group root = new Group();

        Walker walker = new Walker();

        root.getChildren().add(walker.rekt);

        AnimationTimer appLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                root.getChildren().add(walker.takeAStep());
            }
        };

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setScene(scene);

        appLoop.start();
        primaryStage.show();
    }
}
