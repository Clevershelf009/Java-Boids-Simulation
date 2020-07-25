import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Vector;

public class TrailHandler {

    private ArrayList<ArrayList<Vector2D>> trails = new ArrayList<>();

    private int trailLength;

    private Color[] fadingTrailColours;

    public TrailHandler(int trailLength) {
        this.trailLength = trailLength;
        fadingTrailColours = new Color[trailLength];

        //pre-calculate colours for the fade in the trail
        generateFadeColors();
    }

    public void addTrail(ArrayList<Vector2D> trail) {
        trails.add(trail);
    }

    private void generateFadeColors() {
        for (int i = 0; i < trailLength - 1; i++) {
            int alphaValue = (int) mapFromOneRangeToAnother(i, 0, trailLength - 1, 0, 255);
            Color fade = new Color(0, 0, 0, alphaValue);
            fadingTrailColours[i] = fade;
        }
    }

    private double mapFromOneRangeToAnother(double x, double inMin, double inMax, double outMin, double outMax) {
        return (x - inMin) * (outMax - outMin) / (inMax - inMin) + outMin;
    }

    //TODO: Throw error if trails is empty
    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();

        for (int i = 0; i < trails.get(0).size() - 1; i++) {

            g2d.setColor(fadingTrailColours[i]);

            for (ArrayList<Vector2D> trail : trails) {

                Vector2D lineStart = trail.get(i);
                Vector2D lineEnd = trail.get(i + 1);

                g2d.drawLine((int) lineStart.getX(), (int) lineStart.getY(), (int) lineEnd.getX(), (int) lineEnd.getY());
            }
        }
    }
}
