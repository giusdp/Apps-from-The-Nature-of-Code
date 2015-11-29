package flowfield;

import java.util.Random;

/** Created by Giuseppe on 23/09/2015. */
public class FlowField {

    Vector2D[][] field;
    int resolution, cols, rows;
    int interval = 2;

    public FlowField(int resolution) {
        Random random = new Random();
        this.resolution = resolution;
        cols = (int) Constants.SCENE_WIDTH / resolution;
        rows = (int) Constants.SCENE_HEIGHT / resolution;
        field = new Vector2D[rows][cols];

        for ( int i = 0; i < rows ; i++) {
            for ( int j = 0; j < cols; j++ ){
                field[i][j] = new Vector2D(random.nextGaussian() * interval, random.nextGaussian() * interval);
            }
        }
    }

    public Vector2D[][] getField() {
        return field;
    }

    public Vector2D lookup(Vector2D lookup){
        int column = (int) Utils.contrain(lookup.x/resolution, 0, cols - 1);
        int row = (int) Utils.contrain(lookup.y/resolution, 0, rows - 1);

        return field[row][column].copy();
    }


}
