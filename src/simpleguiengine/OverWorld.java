package simpleguiengine;

import java.awt.Color;
import java.util.ArrayList;
import simplegui.SimpleGUI;
import simplegui.TimerListener;

public class OverWorld implements TimerListener {

    double floor;
    ArrayList<GroundObject> GroundObjects = new ArrayList();
    ArrayList<BoxCollider> BoxColliders = new ArrayList();
    SimpleGUI globe;
    Player p1 = null;
    int indexOfPlayerGroundObject;

    public OverWorld(SimpleGUI sg, double baseElevation) {
        floor = baseElevation;
        globe = sg;
        globe.setBackgroundColor(Color.BLUE);
        globe.registerToTimer(this);

    }

    public void addGroundObject(GroundObject g) {
        GroundObjects.add(g);
        BoxColliders.add(g.b);
    }

    public void addPlayer(Player p) {
        p1 = p;
        GroundObjects.add(p1);
        indexOfPlayerGroundObject = GroundObjects.size();
        BoxColliders.add(p1.b);
    }

    public void visualizeAll() {
        for (int i = 0; i < GroundObjects.size(); i++) {
            if (GroundObjects.get(i) == p1) {
                continue;
            }
            GroundObjects.get(i).visualize(globe);
        }

        if (p1 != null) {
            p1.visualize();
        }

        globe.drawFilledBox(0, globe.getHeight() - floor, globe.getWidth(), floor, Color.DARK_GRAY, 1.0, "floor");
    }

    @Override
    public void reactToTimer(long l) {
        for (int i = 0; i < BoxColliders.size(); i++) {
            if (BoxColliders.get(i) == p1.b) {
                continue;
            }
            if (p1.b.isColliding(BoxColliders.get(i))) {
                System.out.println("Colliding");
            }
        }

        //System.out.println("1");
    }
}
