package repeller;

import javafx.scene.shape.Shape;
import repeller.structure.Particle;

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

    public Shape addParticle(Particle particle){
        particles.add(particle);
        return particle.getShape();
    }

    public Vector2D getOrigin(){ return origin; }

    public void run(){
        Iterator<Particle> it = particles.iterator();
        while (it.hasNext()) {
            Particle p = it.next();
            p.run();
            if (p.isDead()) it.remove();
        }
    }

    public void applyForce(Vector2D forceToApply){
        particles.stream().forEach(particle -> particle.applyForce(forceToApply));
    }

    public void applyRepeller(Repeller repeller) {
        particles.stream().forEach(particle -> particle.applyForce(repeller.repel(particle)));
    }
}
