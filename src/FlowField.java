import java.awt.*;
import java.util.ArrayList;

public class FlowField {

    private int screenWidth;
    private int screenHeight;

    private final int HORIZONTAL_SEPARATION = 10;
    private final int VERTICAL_SEPARATION = 10;

    private ArrayList<Boid> boids = new ArrayList<>();

    public FlowField(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        initialiseBoids();
    }

    private void initialiseBoids() {
        int boidsInAHorizontalLine = screenWidth / HORIZONTAL_SEPARATION;
        int boidsInAVerticalLine = screenHeight / VERTICAL_SEPARATION;

        double[] flowFieldValues = PerlinNoise.PerlinNoise2D(boidsInAHorizontalLine, boidsInAVerticalLine, 5, 1);

        for (int row = 0; row < boidsInAVerticalLine; row++) {
            for (int col = 0; col < boidsInAHorizontalLine; col++) {
                Vector2D position = new Vector2D(col * VERTICAL_SEPARATION, row * HORIZONTAL_SEPARATION);
                Vector2D velocity = mapDoubleToUnitCircleCoordinate(flowFieldValues[boidsInAHorizontalLine * row + col]);
                Boid newBoid = new Boid(position, velocity);
                boids.add(newBoid);
            }
        }
    }

    public void tick() {

    }

    public void render(Graphics g) {
        for (Boid current : boids) {
            current.render(g);
        }
    }

    private Vector2D mapDoubleToUnitCircleCoordinate(double n) {
        double angle = mapFromOneRangeToAnother(n, 0, 1, 0, Math.PI * 2);
        return new Vector2D(Math.sin(angle), Math.cos(angle));
    }

    private double mapFromOneRangeToAnother(double x, double inMin, double inMax, double outMin, double outMax) {
        return (x - inMin) * (outMax - outMin) / (inMax - inMin) + outMin;
    }
}
