import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2DTest {

    @Test
    public void add() {
        Vector2D v1 = new Vector2D(1, 2);
        Vector2D v2 = new Vector2D(2, 1);

        Vector2D expected = new Vector2D(3, 3);

        v1.add(v2);
        assertEquals(v1, expected, "Vector2D add method isn't working properly");
    }

    @Test
    public void subtract() {
        Vector2D v1 = new Vector2D(1, 2);
        Vector2D v2 = new Vector2D(2, 1);

        Vector2D expected = new Vector2D(-1, 1);

        v1.subtract(v2);
        assertEquals(v1, expected, "Vector2D subtract method isn't working properly");
    }

    @Test
    void multiply() {
        Vector2D vec = new Vector2D(1,0);
        double multiplier = 3.14;

        Vector2D expected = new Vector2D(3.14, 0);

        vec.multiply(multiplier);

        assertEquals(vec, expected, "Vector2D multiply method isn't working properly");

    }

    @Test
    void divide() {
        Vector2D vec = new Vector2D(3.14,0);
        double divider = 3.14;

        Vector2D expected = new Vector2D(1, 0);

        vec.divide(divider);

        assertEquals(vec, expected, "Vector2D divide method isn't throwing the correct error when dividing by zero");
    }

    void divideByZero() {
        Vector2D vec = new Vector2D(1,1);
        Throwable exception = assertThrows(IllegalArgumentException.class,
                ()->{vec.divide(0);});
    }

    @Test
    void getMagnitude() {
        Vector2D vec = new Vector2D(3,4);
        double expected = 5;

        assertEquals(vec.getMagnitude(), expected, "Vector2D getMagnitude isn't working properly");
    }

    @Test
    void getDelta() {
    }

    @Test
    void normalise() {
        Vector2D vec = new Vector2D(6,8);
        Vector2D expected = new Vector2D(0.6, 0.8);

        vec.normalise();

        assertEquals(vec, expected, "Vector2D normalise isn't working properly");
    }

    @Test
    void distanceTo() {
    }

    @Test
    void setX() {
        Vector2D vec = new Vector2D(2,2);
        double expected = 1;
        vec.setX(1);
        assertEquals(expected, vec.getX(), "Vector2D setX isn't working properly");
    }

    @Test
    void setY() {
        Vector2D vec = new Vector2D(2,2);
        double expected = 1;
        vec.setY(1);
        assertEquals(expected, vec.getY(), "Vector2D setY isn't working properly");
    }

    @Test
    void getX() {
        Vector2D vec = new Vector2D(1,2);
        double expected = 1;
        assertEquals(expected, vec.getX(), "Vector2D getX isn't working properly");
    }

    @Test
    void getY() {
        Vector2D vec = new Vector2D(1,2);
        double expected = 2;
        assertEquals(expected, vec.getY(), "Vector2D getY isn't working properly");
    }

    @Test
    void testToString() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }
}