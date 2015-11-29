package flocking;

import java.util.ArrayList;
import java.util.List;

/** Created by Giuseppe on 24/09/2015. */
public class Flock {
    public final List<WorldObject> objects = new ArrayList<>();

    public Flock() {}

    public void run() {
        for (WorldObject b : objects) {
            b.flock(objects);
        }
    }

    public void addObject(WorldObject b) {
        objects.add(b);
    }
}
