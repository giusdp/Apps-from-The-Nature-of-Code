package repeller;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import repeller.structure.Circ;
import repeller.structure.Particle;
import repeller.structure.Rect;

/** Created by Giuseppe on 21/09/2015. */
public class RepellerMain extends Application {

    public static double SCENE_WIDTH = 800, SCENE_HEIGHT = 600;

    public static void main(String[] lols){ launch(lols); }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Repeller Test");

        Group root = new Group();

        ParticleSystem ps = new ParticleSystem(new Vector2D(SCENE_WIDTH / 2, 200));
        Repeller repeller = new Repeller(new Vector2D(SCENE_WIDTH / 2 - 10, 230));
        root.getChildren().add(repeller.circle);

        Vector2D gravity = new Vector2D(0, .1);
        AnimationTimer appLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                ps.run();
                ps.applyForce(gravity);
                ps.applyRepeller(repeller);
                Particle p;
                if (Math.random() < .5) p = new Rect(ps.getOrigin());
                else p = new Circ(ps.getOrigin());

                root.getChildren().add(ps.addParticle(p));
            }
        };

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        primaryStage.setScene(scene);

        appLoop.start();
        primaryStage.show();
    }
}
