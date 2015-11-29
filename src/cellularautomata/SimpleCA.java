package cellularautomata;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;

import static cellularautomata.CAMain.*;

/** Created by Giuseppe on 24/09/2015. */
public class SimpleCA {

    int[] cells;
    int[] ruleset;

    int w;
    int generation = 0;

    Pane layer;
    public SimpleCA(Pane layer, int w) {
        this.layer = layer;
        this.w = w;
        cells = new int[WIDTH / w];
        ruleset = new int[]{0, 1, 0, 1, 1, 0, 1, 0};
        cells[cells.length/2] = 1;
        layer.getChildren().addAll(getCurrentRects());
        generation++;
    }

    public void generate() {
        int[] nextgen = new int[cells.length];
        for (int i = 1; i < cells.length-1; i++) {
            int left = cells[i-1];
            int me = cells[i];
            int right = cells[i+1];
            nextgen[i] = rules(left, me, right);
        }
        cells = nextgen;
        layer.getChildren().addAll(getCurrentRects());
        generation++;
    }

    public int rules(int a, int b, int c) {
        String s = "" + a + b + c;
        int index = Integer.parseInt(s,2);
        return ruleset[index];
    }

    public List<Shape> getCurrentRects() {
        List<Shape> shapes = new ArrayList<>();
        for (int i = 0; i < cells.length; i++) {
            Rectangle rectangle = new Rectangle(i*w, generation*w, w, w);
            if (cells[i] == 1) rectangle.setFill(Color.BLACK);
            else rectangle.setFill(Color.WHITE);
            shapes.add(rectangle);
        }
        return shapes;
    }
}
