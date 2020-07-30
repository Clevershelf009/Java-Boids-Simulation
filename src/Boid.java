import java.awt.*;
import java.util.ArrayList;

public class Boid extends ArrowHead {

    private static final double SPEED_LIMIT = 15;

    private final Double COHESION_FACTOR = 0.005;
    private final Double SEPARATION_FACTOR = 0.5;
    private final Double ALIGNMENT_FACTOR = 0.005;

    private double proximityThreshold = 50;
    private double socialDistancingThreshold = 30;

    public ArrayList<Vector2D> positionHistory = new ArrayList<>();
    public static int historyMaxLength = 20;

    Boid(Vector2D position, Vector2D velocity) {
        super(position, velocity);
    }

    private void addPositionToHistory() {
        Vector2D currentPosition = new Vector2D(position.getX(), position.getY());
        positionHistory.add(currentPosition);

        if (positionHistory.size() > historyMaxLength) {
            positionHistory.remove(0);
        }
    }

    public void tick(ArrayList<Boid> boids) {
        coherenceRule(boids);
        separationRule(boids);
        alignmentRule(boids);
        mapSpeedToLimit();
        DirectAwayFromEdges();

        position.add(velocity);
        addPositionToHistory();
    }

    private void DirectAwayFromEdges() {
        int edgeMargin = 100;
        double turnFactor = 1.5;

        if (position.getX() < edgeMargin) {
            velocity.setX(velocity.getX() + turnFactor);
        }
        if (position.getX() > Simulation.WIDTH - edgeMargin) {
            velocity.setX(velocity.getX() - turnFactor);
        }
        if (position.getY() < edgeMargin) {
            velocity.setY(velocity.getY() + turnFactor);
        }
        if (position.getY() > Simulation.HEIGHT - edgeMargin) {
            velocity.setY(velocity.getY() - turnFactor);
        }
    }

    private void coherenceRule(ArrayList<Boid> boids) {
        Vector2D centreOfMass = new Vector2D(0,0);
        int boidsWithinThreshold = 0;

        for (Boid boid : boids) {
            if (boid != this && position.distanceTo(boid.position) <= proximityThreshold) {
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
            if (boid.position != position && position.distanceTo(boid.position) <= proximityThreshold) {
                averageVelocity.add(boid.getVelocity());
                boidsWithinThreshold++;
            }
        }
        if (boidsWithinThreshold > 0) {
            averageVelocity.divide(boidsWithinThreshold);
            averageVelocity.subtract(position);
            averageVelocity.multiply(ALIGNMENT_FACTOR);
            velocity.add(averageVelocity);
        }
    }

    private void mapSpeedToLimit() {
        double speed = velocity.getMagnitude();
        if (speed > SPEED_LIMIT) {
            velocity.divide(speed);
            velocity.multiply(SPEED_LIMIT);
        }
    }

}
