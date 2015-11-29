package walker;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

/** Created by Giuseppe on 17/09/2015. */
public class Walker {

    public Rectangle rekt;
    private double x, y, w = 5;

    private Random random;

    public Walker() {
        random = new Random();
        x = WalkerMain.SCENE_WIDTH / 2;
        y = WalkerMain.SCENE_HEIGHT / 2 + 1;

        rekt = new Rectangle(x, y, w, w);
        rekt.setFill(Color.BLACK);
    }

    public Rectangle takeAStep(){
        final double oldX = x, oldY = y;
        x += random.nextInt(3) - 1;
        y += random.nextInt(3) - 1;

        rekt.relocate(x, y);

        Rectangle rek = new Rectangle(oldX, oldY, w, w);
        rek.setFill(Color.BLACK);
        return rek;
    }

}
