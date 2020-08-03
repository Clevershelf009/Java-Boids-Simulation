import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class FlowField {

    private int screenWidth;
    private int screenHeight;

    private final int HORIZONTAL_SEPARATION = 50;
    private final int VERTICAL_SEPARATION = 50;

    private ArrayList<Boid> boids = new ArrayList<>();

    public FlowField(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        initialiseBoids();
    }

    private void initialiseBoids() {
        int boidsInAHorizontalLine = screenWidth / HORIZONTAL_SEPARATION;
        int boidsInAVerticalLine = screenHeight / VERTICAL_SEPARATION;

        /**Random r = new Random();

        double[] flowFieldValues = new double[boidsInAHorizontalLine*boidsInAVerticalLine];
        for (int i = 0; i < boidsInAHorizontalLine*boidsInAVerticalLine; i++) {
            flowFieldValues[i] = r.nextDouble();
        }**/
        // above for testing

        double[] flowFieldValues = PerlinNoise.PerlinNoise2D(boidsInAHorizontalLine, boidsInAVerticalLine, 1, 32);

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
