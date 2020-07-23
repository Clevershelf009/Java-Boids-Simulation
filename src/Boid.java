import java.awt.*;
import java.util.ArrayList;

public class Boid {

    private static final double SPEED_LIMIT = 0.1;
    private Vector2D INITIALVELOCITY = new Vector2D(3,1);

    private final Double COHESION_FACTOR = 0.1;
    private final Double SEPARATION_FACTOR = 0.1;
    private final Double ALIGNMENT_FACTOR = 0.1;

    private Vector2D position;
    private Vector2D velocity;
    private double proximityThreshold = 5;
    private double socialDistancingThreshold = 2;


    Boid(Vector2D position) {
        this.position = position;
        this.velocity = INITIALVELOCITY;
    }

    public void tick(ArrayList<Boid> boids) {
        coherenceRule(boids);
        separationRule(boids);
        //alignmentRule(boids);
        position.add(velocity);

        if(getPosition().getY() <= 0 || getPosition().getY() >= Simulation.HEIGHT){
            velocity.setY(velocity.getY() * -1);
        }
        if(getPosition().getX() <= 0 || getPosition().getX() >= Simulation.WIDTH){
            velocity.setX(velocity.getX() * -1);
        }
    }

    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        int x = (int) position.getX();
        int y = (int) position.getY();

        g2d.setColor(Color.BLACK);

        g2d.rotate(Math.atan2(velocity.getX(),-velocity.getY()), x, y);
        g2d.fillPolygon(new int[] {x, x-10, x+10},new int[] {y, y+30, y+30},3);

    }

    private void coherenceRule(ArrayList<Boid> boids) {
        Vector2D centreOfMass = new Vector2D(0,0);
        int boidsWithinThreshold = 0;

        for (Boid boid : boids) {
            if (position.distanceTo(boid.position) <= proximityThreshold) {
                centreOfMass.add(boid.getPosition());
                boidsWithinThreshold++;
            }
        }

        if (boidsWithinThreshold != 0) {
            centreOfMass.divide(boidsWithinThreshold);
            centreOfMass.subtract(position);
            centreOfMass.multiply(COHESION_FACTOR);
            velocity.add(centreOfMass);
        }

    }

    private void separationRule(ArrayList<Boid> boids) {
        //I have no real idea what to name this vector, because I don't really understand how this rule works
        Vector2D avoidInterceptVector = new Vector2D(0, 0);

        for (Boid boid : boids) {
            // reference check is to stop from comparing one boid against itself,
            // as naturally, unless the minimum distance is 0, it will be within it.
            if (boid.position != position && position.distanceTo(boid.position) <= socialDistancingThreshold) {
                avoidInterceptVector.add(position.getDelta(boid.position));
            }
        }
        avoidInterceptVector.multiply(SEPARATION_FACTOR);
        velocity.add(avoidInterceptVector);


    }

    private void alignmentRule(ArrayList<Boid> boids) {
        Vector2D averageVelocity = new Vector2D(0,0);
        int boidsWithinThreshold = 0;

        for (Boid boid : boids) {
            if (position.distanceTo(boid.position) <= proximityThreshold) {
                averageVelocity.add(boid.getVelocity());
                boidsWithinThreshold++;
            }
        }

        averageVelocity.divide(boidsWithinThreshold);
        averageVelocity.subtract(position);
        averageVelocity.multiply(ALIGNMENT_FACTOR);
        velocity.add(averageVelocity);
    }

    private void mapSpeedToLimit() {
        double speed = velocity.getMagnitude();
        if (speed > SPEED_LIMIT) {
            velocity.divide(speed);
            velocity.multiply(SPEED_LIMIT);
        }
    }

    public Vector2D getPosition() {
        return position;
    }

    public Vector2D getVelocity() {
        return velocity;
    }
}
