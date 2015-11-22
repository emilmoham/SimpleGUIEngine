package simpleguiengine;

import java.util.ArrayList;

public class BoxCollider {

    double xTopLeft;
    double yTopLeft;
    double width;
    double height;
    
    //boolean isOnScreen; May Later be used to speed up collision detection.

    public BoxCollider(double x, double y, double w, double h) {
        xTopLeft = x;
        yTopLeft = y;
        width = w;
        height = h;
    }
    
    public boolean isColliding(ArrayList<BoxCollider> b, int checkIndex){
        for(int i = 0; i < b.size(); i++){
            if(i == checkIndex){
                continue;
            }
            boolean xCollide;
            boolean yCollide;
            //Check x Axis Elements
            //-Check Right side of this element
            xCollide = ((xTopLeft+width) >= b.get(i).xTopLeft && (xTopLeft+width) <= (b.get(i).xTopLeft+b.get(i).width));
            //-Check Left side of this element
            xCollide = xCollide || ((xTopLeft <= (b.get(i).xTopLeft+b.get(i).width)) && (xTopLeft >= b.get(i).xTopLeft));
            //Check y Axis Elements
            //-Check top side of this element
            yCollide = (yTopLeft <= (b.get(i).yTopLeft+b.get(i).height) && yTopLeft >= (b.get(i).yTopLeft));
            //-Check bottom side of this element
            yCollide = yCollide || ((yTopLeft+height) >= b.get(i).yTopLeft && (yTopLeft+height) <= b.get(i).yTopLeft+height);
            if(xCollide && yCollide){
                return true;
            }
        }
        return false;
    }
    
    public void updatePosition(double x, double y){
        xTopLeft = x;
        yTopLeft = y;
    }
}
