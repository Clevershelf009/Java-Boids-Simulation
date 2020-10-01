import java.util.Objects;

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
        if (divider == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
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

    public void setX(double x) {this.x = x; }

    public void setY(double y) {this.y = y; }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String toString() {
        return "x: " + x + " - y: " + y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2D vector2D = (Vector2D) o;
        return Double.compare(vector2D.getX(), getX()) == 0 &&
                Double.compare(vector2D.getY(), getY()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}
