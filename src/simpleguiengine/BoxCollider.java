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

    public boolean isColliding(ArrayList<BoxCollider> b, int checkIndex) {
        for (int i = 0; i < b.size(); i++) {
            if (i == checkIndex) {
                continue;
            }
            boolean xCollide;
            boolean yCollide;
            //Check x Axis Elements
            //-Check Right side of this element
            xCollide = ((xTopLeft + width) >= b.get(i).xTopLeft && (xTopLeft + width) <= (b.get(i).xTopLeft + b.get(i).width));
            //-Check Left side of this element
            xCollide = xCollide || ((xTopLeft <= (b.get(i).xTopLeft + b.get(i).width)) && (xTopLeft >= b.get(i).xTopLeft));
            //Check y Axis Elements
            //-Check top side of this element
            yCollide = (yTopLeft <= (b.get(i).yTopLeft + b.get(i).height) && yTopLeft >= (b.get(i).yTopLeft));
            //-Check bottom side of this element
            yCollide = yCollide || ((yTopLeft + height) >= b.get(i).yTopLeft && (yTopLeft + height) <= b.get(i).yTopLeft + b.get(i).height);
            if (xCollide && yCollide) {
                return true;
            }
        }
        return false;
    }

    public boolean isColliding(BoxCollider b) {
        boolean xCollide;
        boolean yCollide;
        
        xCollide = ((xTopLeft + width) >= b.xTopLeft && (xTopLeft + width) <= (b.xTopLeft + b.width));
        xCollide = xCollide || ((xTopLeft <= (b.xTopLeft + b.width)) && (xTopLeft >= b.xTopLeft));
        yCollide = (yTopLeft <= (b.yTopLeft + b.height) && yTopLeft >= (b.yTopLeft));
        yCollide = yCollide || ((yTopLeft + height) >= b.yTopLeft && (yTopLeft + height) <= b.yTopLeft + b.height);
        
        return (xCollide && yCollide);
    }

    public void updatePosition(double x, double y) {
        xTopLeft = x;
        yTopLeft = y;
    }

    public void updateSize(double w, double h) {
        width = w;
        height = h;
    }
}
