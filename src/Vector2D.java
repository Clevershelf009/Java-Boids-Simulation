import java.util.Vector;

public class Vector2D {

    private double x;
    private double y;

    Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void add(Vector2D vector) {
        x += vector.getX();
        y += vector.getY();
    }

    public void subtract(Vector2D vector) {
        x -= vector.getX();
        y -= vector.getY();
    }

    public void multiply(double multiplier) {
        x *= multiplier;
        y *= multiplier;
    }

    public void divide(double divider) {
        //TODO: Throw error when divider == 0
        x /= divider;
        y /= divider;

    }

    public double getMagnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector2D getDelta(Vector2D otherVector) {
        return new Vector2D(x - otherVector.getX(), y - otherVector.getY());
    }

    public void normalise() {
        double magnitude = getMagnitude();
        x /= magnitude;
        y /= magnitude;
    }

    public double distanceTo(Vector2D otherVector) {
        return Math.sqrt(Math.pow((x - otherVector.getX()), 2) + Math.pow((y - otherVector.getY()), 2));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
