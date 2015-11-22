package simpleguiengine;

import java.awt.Color;
import java.util.ArrayList;
import simplegui.SimpleGUI;

public class OverWorld {
    double floor;
    ArrayList<GroundObject> GroundObjects = new ArrayList();
    SimpleGUI globe;
    
    public OverWorld(SimpleGUI sg, double baseElevation){
        floor = baseElevation;
        globe = sg;
        globe.setBackgroundColor(Color.BLUE);
    }
    
    public void addGroundObject(GroundObject g){
        GroundObjects.add(g);
    }
    
    public void visualizeAll(){
        for(int i = 0; i < GroundObjects.size(); i++){
            GroundObjects.get(i).visualize(globe);
        }
        globe.drawFilledBox(0,globe.getHeight()-floor, globe.getWidth(), floor, Color.DARK_GRAY, 1.0, "floor");
    }
}
