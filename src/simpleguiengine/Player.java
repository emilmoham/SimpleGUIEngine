package simpleguiengine;

import java.awt.Color;
import simplegui.SimpleGUI;

public class Player {

    //Visualization Data
    SimpleGUI globe;
    int playerSize = 50;
    int startX;
    int startY;

    public Player(SimpleGUI sg) {
        globe = sg;
        startX = globe.getWidth() / 2;
        startY = globe.getHeight() - 100;
    }

    public void visualize() {
        globe.drawFilledBox(startX, startY, playerSize, playerSize, Color.BLUE, 1.0, "Player");
    }
    
    
}
