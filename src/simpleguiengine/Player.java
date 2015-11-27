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

    //Physics and movement data
    boolean moveLeft = false;
    boolean moveRight = false;
    boolean playerIsJumping = false;
    double gravity = 2;
    double jumpStrength = -20;
    double speedY = 0;
    double speedX = 5;
    double movement;
    double sampleRate = 0.1;

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
            if (c == 'w') {
                globe.print("");
                globe.print("UP");
                if (!playerIsJumping) {
                    playerIsJumping = true;
                    speedY = jumpStrength;
                }
            }
            /*
             if(c == 's'){
             globe.print("");
             globe.print("DOWN");
             }*/

            if (c == 'a') {
                globe.print("");
                globe.print("LEFT");
                moveLeft = true;
            } else if (c == 'd') {
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

    double checkDistanceX(double desiredMovement) {

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
            while (b.isColliding(bcs.get(i))) {
                if (moveRight) {
                    r -= sampleRate;
                    b.updatePosition(xTopLeft + r, yTopLeft);
                } else if (moveLeft) {
                    r += sampleRate;
                    b.updatePosition(xTopLeft + r, yTopLeft);
                }
            }
        }

        b.updatePosition(xTopLeft + (-1 * r), yTopLeft);
        return r;
    }

    double checkDistanceY(double desiredDistance) {
        double r = desiredDistance;
        b.updatePosition(xTopLeft, yTopLeft + r);

        int i = 0;
        for (i = 0; i < bcs.size(); i++) {
            if (bcs.get(i) == b) {
                continue;
            }
            if (b.isColliding(bcs.get(i))) {
                break;
            }
        }

        if (i < bcs.size()) {
            while (b.isColliding(bcs.get(i))) {
                speedY = 0;
                playerIsJumping = false;
                if (r > 0) {
                    r -= sampleRate;
                } else {
                    r += sampleRate;
                }
                b.updatePosition(xTopLeft, yTopLeft + r);
            }
        }

        b.updatePosition(xTopLeft, yTopLeft - r);
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

        if (playerIsJumping) {
            speedY += gravity;
            if (speedY > 0) {
                speedY = Math.min(speedY, checkDistanceY(speedY));
            } else if (speedY < 0) {
                speedY = Math.max(speedY, checkDistanceY(speedY));
            }
            globe.animMoveAllRel(0, speedY, "Player");
            yTopLeft += speedY;
        }else{
            speedY = 0;
            speedY = Math.max(speedY, checkDistanceY(gravity*5));
            globe.animMoveAllRel(0, speedY, "Player");
            yTopLeft += speedY;
        }
        
        
        b.updatePosition(xTopLeft, yTopLeft);
        globe.repaintPanel();
    }
}
