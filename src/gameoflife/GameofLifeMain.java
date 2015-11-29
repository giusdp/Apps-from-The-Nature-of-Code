package gameoflife;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;

/** Created by Giuseppe on 24/09/2015. */
public class GameofLifeMain extends Application {

    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    private static final int BOARD_SIZE = 600;

    private Map<String, Rectangle> boardMap = new HashMap<>();
    private Board board = new Board(BOARD_SIZE/10);

    @Override
    public void start(Stage primaryStage) {
        final Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, event -> {
            iterateBoard();
        }), new KeyFrame(Duration.millis(100)));

        timeline.setCycleCount(Timeline.INDEFINITE);

        board.initBoard(1);

        Pane root = new Pane();
        Scene scene = new Scene(root, BOARD_SIZE, BOARD_SIZE);

        // Create a board with dead cells
        for (int x = 0; x < BOARD_SIZE; x = x + WIDTH) {
            for (int y = 0; y < BOARD_SIZE; y = y + HEIGHT) {
                Rectangle cell = new Rectangle(x, y, WIDTH, HEIGHT);
                cell.setFill(Color.BLACK);
                root.getChildren().add(cell);

                //Store the cell in a HashMap for fast access
                //in the iterateBoard method.
                String key = x + " " + y;
                boardMap.put(key, cell);
                makeClickable(key, x, y);
            }
        }

        primaryStage.setTitle("Game of Life - Conway");
        primaryStage.setScene(scene);
        primaryStage.show();

        Stage stage = new Stage();
        String startText = "Start.";
        Button start = new Button(startText);
        HBox hBox = new HBox(start);
        hBox.setAlignment(Pos.CENTER);

        stage.setScene(new Scene(hBox, 250, 50));

        start.setOnAction(event -> {
            if (start.getText().equals(startText)){
                timeline.play();
                start.setText("Stop.");
            }else {
                start.setText(startText);
                timeline.stop();
            }
        });
        stage.show();
    }

    private void makeClickable(String key, int x, int y){
        Rectangle rect = boardMap.get(key);
        rect.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            int nX = x / WIDTH, nY = y / HEIGHT;
            int value = board.getField(nX, nY);
            if (value == 1) {
                board.setField(nX, nY, 0);
                rect.setFill(Color.BLACK);
            } else {
                board.setField(nX, nY, 1);
                rect.setFill(Color.WHITE);
            }
        });
    }

    private void iterateBoard() {
        board.nextPopulation();
        for (int x = 0; x < board.getSize(); x++) {
            for (int y = 0; y < board.getSize(); y++) {
                Rectangle cell = boardMap.get(x * WIDTH + " " + y * HEIGHT);
                // If the cell at (x,y) is a alive use css styling 'alive-cell'
                // otherwise use the styling 'dead-cell'.
                if (board.getField(x, y) == 1) {
                    cell.setFill(Color.WHITE);
                } else {
                    cell.setFill(Color.BLACK);
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
