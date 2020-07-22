public class Vector2D {

    private int x;
    private int y;

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
        return Math.sqrt(x^2 + y^2);
    }

    public void normalise() {
        double magnitude = getMagnitude();
        x /= magnitude;
        y /= magnitude;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
