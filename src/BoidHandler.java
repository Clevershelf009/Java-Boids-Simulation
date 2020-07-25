import java.awt.Graphics;
import java.util.ArrayList;

public class BoidHandler {

    private ArrayList<Boid> boids = new ArrayList<>();

    private TrailHandler trails = new TrailHandler(Boid.historyMaxLength);

    private boolean enableTrails = false;

    public void tick(){
        for(int i = 0; i < boids.size(); i++){
            Boid current = boids.get(i);
            current.tick(boids);
        }
    }

    public void render(Graphics g){
        for (Boid current : boids) {
            current.render(g);
        }
        if (enableTrails) {
            trails.render(g);
        }
    }

    public void addBoid(Boid boid){
        boids.add(boid);
        trails.addTrail(boid.positionHistory);
    }

    public void removeBoid(Boid boid){
        boids.remove(boid);
    }

    public void enableTrails() {
        enableTrails = true;
    }

    public void disableTrails() {
        enableTrails = false;
    }

}
