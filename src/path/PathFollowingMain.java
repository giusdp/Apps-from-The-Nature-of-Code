package path;

import flowfield.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** Created by Giuseppe on 23/09/2015. */
public class PathFollowingMain extends Application {

    public static int WIDTH = 800, HEIGHT = 600, VEHICLE_COUNT = 100;

    public static void main(String[] args){ launch(args);}


    private final List<Vehicle> vehicleList = new ArrayList<>();
    private Path path;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Path Following Test");
        Pane layer = new Pane();
        layer.setPrefSize(WIDTH, HEIGHT);

        path = new Path(layer, new Vector2D(40, HEIGHT - 100), new Vector2D(WIDTH-40, HEIGHT - 200), 10);

        primaryStage.setScene(new Scene(layer, WIDTH, HEIGHT));
        primaryStage.show();
        prepareApp(layer);
        startApp();
    }

    private void prepareApp(Pane layer){
        final Random random = new Random();
        for (int i = 0; i < VEHICLE_COUNT; i++){
            double x = random.nextDouble() * layer.getWidth();
            double y = random.nextDouble() * layer.getHeight();
            vehicleList.add(new Vehicle(layer, new Vector2D(x, y), 10, 5));
        }
    }

    private void startApp(){
        AnimationTimer appLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                vehicleList.stream().forEach(vehicle -> vehicle.follow(path));
                vehicleList.stream().forEach(WorldObject::move);
                vehicleList.stream().forEach(WorldObject::display);
            }
        };

        appLoop.start();
    }
}
