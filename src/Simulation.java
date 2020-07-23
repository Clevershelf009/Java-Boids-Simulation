import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Simulation extends Canvas implements Runnable {

    public static int WIDTH = 1080, HEIGHT = 720;


    private Thread thread;
    private boolean running = false;

    private Handler handler;

    public Simulation(){
        handler = new Handler();

        new Display(WIDTH,HEIGHT,"Boids Simulation", this);

        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            handler.addBoid(new Boid(new Vector2D(r.nextInt(WIDTH), r.nextInt(HEIGHT)), new Vector2D(r.nextDouble() * 10, r.nextDouble() * 10)));
        }

    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        } catch(Exception e){
            System.err.println("Unable to halt operation");
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double nanoSeconds = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / nanoSeconds;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta --;
            }
            if(running)
                render();
            frames ++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
        handler.tick();
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(2);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0,0,WIDTH,HEIGHT);

        handler.render(g);

        g.dispose();
        bs.show();
    }

    public static void main(String[] args){
        new Simulation();
    }
}
