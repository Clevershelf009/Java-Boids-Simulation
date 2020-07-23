import java.awt.Graphics;
import java.util.ArrayList;

public class Handler {

    private ArrayList<Boid> boids = new ArrayList<Boid>();

    public void tick(){
        for(int i = 0; i < boids.size(); i++){
            Boid current = boids.get(i);
            current.tick(boids);
        }
    }

    public void render(Graphics g){
        for(int i = 0; i < boids.size(); i++){
            Boid current = boids.get(i);
            current.render(g);
        }
    }

    public void addBoid(Boid boid){
        boids.add(boid);
    }

    public void removeBoid(Boid boid){
        boids.remove(boid);
    }

}
