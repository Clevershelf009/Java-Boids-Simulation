import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2DTest {

    @Test
    public void testAdd() {
        Vector2D v1 = new Vector2D(1, 2);
        Vector2D v2 = new Vector2D(2, 1);

        Vector2D expected = new Vector2D(3, 3);

        v1.add(v2);
        assertEquals(v1, expected, "Vector2D add method isn't working properly");
    }

    @Test
    public void testSubtract() {
        Vector2D v1 = new Vector2D(1, 2);
        Vector2D v2 = new Vector2D(2, 1);

        Vector2D expected = new Vector2D(-1, 1);

        v1.subtract(v2);
        assertEquals(v1, expected, "Vector2D subtract method isn't working properly");
    }
}