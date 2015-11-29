package fractals;
/** Created by Giuseppe on 25/09/2015. */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class FractalsMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public static final int WIDTH = 800, HEIGHT = 600;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Fractals Test");


        Group group = new Group();
        Scene scene = new Scene(group, WIDTH, HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.show();

        prepareChoice(group);
    }

    private void prepareChoice(Group group) {
        Stage stage = new Stage();
        Button circles = new Button("Fractal Circles");
        Button cantor = new Button("Cantor Lines");
        VBox vBox = new VBox(10, circles, cantor);
        vBox.setAlignment(Pos.CENTER);

        circles.setOnAction(event -> {
            group.getChildren().clear();
            drawCircle(WIDTH/2, HEIGHT/2, 200, group);
        });

        cantor.setOnAction(event -> {
            group.getChildren().clear();
            drawCantorSet(20, 20, WIDTH - 50, group);
        });

        stage.setScene(new Scene(vBox, 200, 200));
        stage.show();
        stage.setX(WIDTH + WIDTH/3);
    }

    private void drawCircle(double x, double y, float radius, Group group){

        Circle circle = new Circle(x, y, radius);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(1);
        group.getChildren().add(circle);
        if(radius > 2) {
            radius /= 2;
            drawCircle(x + radius*2, y, radius, group);
            drawCircle(x - radius*2, y, radius, group);
            drawCircle(x, y + radius*2, radius, group);
            drawCircle(x, y - radius*2, radius, group);
        }
    }

    private void drawCantorSet(double x, double y, double len, Group group){
        if (len >= 1){
            Line line = new Line(x, y, x + len, y);
            line.setStroke(Color.BLACK);
            line.setStrokeWidth(2);
            group.getChildren().add(line);
            y += 20;
            drawCantorSet(x,y,len/3, group);
            drawCantorSet( x + (len*2/3), y, len/3, group);
        }
    }
}
