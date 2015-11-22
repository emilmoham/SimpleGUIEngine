package simpleguiengine;

import java.util.ArrayList;

public class BoxCollider {

    double xTopLeft;
    double yTopLeft;
    double width;
    double height;

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
            boolean topLeft;
            boolean topRight;
            boolean bottomLeft;
            boolean bottomRight;
            //Check top left corner
            
            //Check top right corner
            
            //Check bottom right corner
            
            //Check bottom left corner
            
        }
        return false; //Temporary Line
    }
}
