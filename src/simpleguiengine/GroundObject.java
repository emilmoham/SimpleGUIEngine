package simpleguiengine;

import java.awt.Color;
import simplegui.SimpleGUI;

public class GroundObject {
    double xTopLeft;
    double yTopLeft;
    double width;
    double height;
    
    BoxCollider b;
    
    public GroundObject(double x, double y, double w, double h){
        xTopLeft = x;
        yTopLeft = y;
        width = w;
        height = h;
        
        b = new BoxCollider(x,y,w,h);
    }
    
    public void visualize(SimpleGUI sg){
        sg.drawFilledBox(xTopLeft, yTopLeft, width, height, Color.red, 1.0, "GO");
    }
}
