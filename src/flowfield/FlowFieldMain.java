package flowfield;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** Created by Giuseppe on 23/09/2015. */
public class FlowFieldMain extends Application {

    private final List<Vehicle> vehicleList = new ArrayList<>();
    private final Random random = new Random();
    private final FlowField flowField = new FlowField(10);
    private Pane layer;

    public static void main(String[] args){ launch(args); }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FlowField test");

        BorderPane root = new BorderPane();

        layer = new Pane();
        layer.setPrefSize(Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT);

        Pane layerPane = new Pane();

        layerPane.getChildren().addAll(layer);

        root.setCenter(layerPane);

        Scene scene = new Scene(root, Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT);

        primaryStage.setScene(scene);

        startApp();
        primaryStage.show();

        prepareApp();

    }

    private void prepareApp(){
        for ( int i = 0 ; i < Constants.VEHICLE_COUNT ; i++){
            addVehicle();
        }
    }
    private void addVehicle(){
        double x = random.nextDouble() * layer.getWidth();
        double y = random.nextDouble() * layer.getHeight();
        double w = 10;
        double h = w/2;
        Vector2D loc = new Vector2D(x,y);
        vehicleList.add(new Vehicle(layer, loc, w, h));
    }

    private void startApp(){
        AnimationTimer appLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                vehicleList.stream().forEach(vehicle -> vehicle.follow(flowField));
                vehicleList.stream().forEach(WorldObject::move);
                vehicleList.stream().forEach(WorldObject::display);
            }
        };

        appLoop.start();
    }
}
