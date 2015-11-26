package simpleguiengine;

import java.awt.Color;
import java.util.ArrayList;
import simplegui.SimpleGUI;
import simplegui.TimerListener;
import simplegui.KeyboardListener;

public class Player extends GroundObject implements TimerListener {

    //Visualization Data
    SimpleGUI globe;
    //double playerSize = 50;

    boolean moveLeft = false;
    boolean moveRight = false;

    double speedX = 5;
    double movement;

    public ArrayList<BoxCollider> bcs;

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
        //playerSize = (w+h)/2;
        globe = sg;

        globe.registerToKeyboard(controls);
        globe.registerToTimer(this);
    }

    public double checkDistanceX(double desiredMovement) {

        double r = desiredMovement;
        b.updatePosition(xTopLeft + r, yTopLeft);

        int i = 0;
        for (i = 0; i < bcs.size(); i++) {
            if (bcs.get(i) == b) {
                continue;
            } else if (b.isColliding(bcs.get(i))) {
                break;
            }
        }

        if (i < bcs.size()) {
            while (b.isColliding(bcs.get(i)) ) {
                if (moveRight) {
                    r -= 1.0;
                    b.updatePosition(xTopLeft + r, yTopLeft);
                } else if (moveLeft) {
                    r += 1.0;
                    b.updatePosition(xTopLeft + r, yTopLeft);
                }
            }
        }

        b.updatePosition(xTopLeft + (-1 * r), yTopLeft);
        return r;
    }

    public void visualize() {
        globe.drawFilledBox(xTopLeft, yTopLeft, width, height, Color.GREEN, 1.0, "Player");
    }

    @Override
    public void reactToTimer(long l) {
        if (moveRight) {
            movement = Math.min(speedX, checkDistanceX(speedX));
            moveRight = false;
            globe.animMoveAllRel(movement, 0, "Player");
            xTopLeft += movement;
        } else if (moveLeft) {
            movement = Math.max(-1 * speedX, checkDistanceX(-1 * speedX));
            moveLeft = false;
            globe.animMoveAllRel(movement, 0, "Player");
            xTopLeft += movement;
        }
        b.updatePosition(xTopLeft, yTopLeft);
        globe.repaintPanel();
    }

}
