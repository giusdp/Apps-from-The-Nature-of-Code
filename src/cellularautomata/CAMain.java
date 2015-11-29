package cellularautomata;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/** Created by Giuseppe on 24/09/2015. */
public class CAMain extends Application {

    public static final int WIDTH = 800, HEIGHT = 600;

    public static void main(String[] args){ launch(args);}

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Cellular Automata Test");

        Pane layer = new Pane();
        int w = 10;
        SimpleCA simpleCA = new SimpleCA(layer, w);

        for (int i = 0; i < HEIGHT/w; i++){
            simpleCA.generate();
        }

        Scene scene = new Scene(layer, WIDTH, HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
