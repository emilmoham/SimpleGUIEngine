package simpleguiengine;

import java.awt.Color;
import simplegui.SimpleGUI;
import simplegui.TimerListener;
import simplegui.KeyboardListener;

public class Player extends GroundObject implements TimerListener {

    //Visualization Data
    SimpleGUI globe;
    double playerSize = 50;

    boolean moveLeft = false;
    boolean moveRight = false;

    KeyboardListener controls = new KeyboardListener() {

        @Override
        public void reactToKeyboardEntry(String string) {
            //Unused
        }

        @Override
        public void reactToKeyboardSingleKey(String string) {
            //WSAD
            char c = string.charAt(0);
            /*
             if (c == 'w') {
             globe.print("");
             globe.print("UP");
             }
             if(c == 's'){
             globe.print("");
             globe.print("DOWN");
             }
             */
            if (c == 'a') {
                globe.print("");
                globe.print("LEFT");
                moveLeft = true;
            }
            if (c == 'd') {
                globe.print("");
                globe.print("RIGHT");
                moveRight = true;
            }
        }
    };

    public Player(double x, double y, double w, double h, SimpleGUI sg) {
        super(x, y, w, h);
        playerSize = (w+h)/2;
        globe = sg;
        
        globe.registerToKeyboard(controls);
        globe.registerToTimer(this);
    }

    public void visualize() {
        globe.drawFilledBox(xTopLeft, yTopLeft, width, height, Color.GREEN, 1.0, "Player");
    }

    @Override
    public void reactToTimer(long l) {
        if (moveRight) {
            moveRight = false;
            globe.animMoveAllRel(4, 0, "Player");
            xTopLeft += 4;
        } else if (moveLeft) {
            moveLeft = false;
            globe.animMoveAllRel(-4, 0, "Player");
            yTopLeft -= 4;
        }
        b.updatePosition(xTopLeft, yTopLeft);
        globe.repaintPanel();
    }

}
