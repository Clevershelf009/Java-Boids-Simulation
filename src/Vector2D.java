public class Vector2D {

    private double x;
    private double y;

    Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void add(Vector2D vector) {
        x += vector.getX();
        y += vector.getY();
    }

    public void scale(float scaleFactor) {
        x *= scaleFactor;
        y *= scaleFactor;
    }

    public double getMagnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public void normalise() {
        double magnitude = getMagnitude();
        x /= magnitude;
        y /= magnitude;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
