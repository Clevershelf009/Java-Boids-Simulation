import java.util.ArrayList;

public class Boid {

    private final Double COHESION_FACTOR = 0.1;
    private final Double SEPARATION_FACTOR = 0.1;
    private final Double ALIGNMENT_FACTOR = 0.1;

    private Vector2D position;
    private Vector2D velocity;
    private double proximityThreshold = 5;
    private double socialDistancingThreshold = 2;


    Boid(Vector2D position, Vector2D velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    public void updateBoid(ArrayList<Boid> boids) {
        coherenceRule(boids);
        separationRule(boids);
        alignmentRule(boids);
        position.add(velocity);
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

    public Vector2D getPosition() {
        return position;
    }

    public Vector2D getVelocity() {
        return velocity;
    }
}
