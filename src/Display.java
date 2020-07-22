import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;

class Display extends Canvas {

    public static void main(String[] args){
       JFrame frame = new JFrame("Boids Simulation");
       Canvas canvas = new Display();
       canvas.setSize(400, 400);
       frame.add(canvas);
       frame.pack();
       frame.setVisible(true);
    }

    public void paint(Graphics g) {
        g.fillOval(150, 100, 200, 200);
    }
}