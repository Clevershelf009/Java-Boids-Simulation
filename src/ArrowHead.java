import java.awt.*;

public class ArrowHead {
    protected Vector2D position;
    protected Vector2D velocity;

    public ArrowHead(Vector2D position, Vector2D velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        int x = (int) position.getX();
        int y = (int) position.getY();

        g2d.setColor(Color.BLACK);

        g2d.rotate(Math.atan2(velocity.getX(),-velocity.getY()), x, y);
        g2d.fillPolygon(new int[] {x, x-10, x+10},new int[] {y, y+30, y+30},3);

    }

    public Vector2D getPosition() {
        return position;
    }

    public Vector2D getVelocity() {
        return velocity;
    }
}
