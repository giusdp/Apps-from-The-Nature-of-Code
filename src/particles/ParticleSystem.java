package particles;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** Created by Giuseppe on 21/09/2015. */
public class ParticleSystem {

    private final List<Particle> particles = new ArrayList<>();
    private Vector2D origin;

    public ParticleSystem(Vector2D origin) {
        this.origin = origin;
    }

    public Shape addParticle(){
        Particle p = new Particle(new Circle(10, Color.BLACK), origin);
        particles.add(p);
        return p.shape;
    }

    public void run(){
        Iterator<Particle> it = particles.iterator();
        while (it.hasNext()) {
            Particle p = it.next();
            p.update();
            if (p.isDead()) it.remove();
        }
    }
}
