package simpleguiengine;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import simplegui.SimpleGUI;
import simplegui.TimerListener;
import simplegui.KeyboardListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Player extends GroundObject implements TimerListener {

    //Control Data
    ArrayList<Integer> keysDown;
    JFrame ControlWindow;
    KeyListener controls = new KeyListener() {

        @Override
        public void keyTyped(KeyEvent e) {
            //Unused
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (!keysDown.contains(e.getKeyCode())) {
                keysDown.add(new Integer(e.getKeyCode()));
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            keysDown.remove(new Integer(e.getKeyCode()));;
        }
    };

    //Visualization Data
    SimpleGUI globe;
    //double playerSize = 50;

    //Physics and movement data
    boolean playerIsJumping = false;
    boolean playerIsFalling = false;
    double gravity = 1.0;
    double jumpStrength = -20;
    double speedY = 0;
    double speedX = 5;
    double movement;
    double sampleRate = 0.5;

    public ArrayList<BoxCollider> bcs;

    public Player(double x, double y, double w, double h, SimpleGUI sg) {
        super(x, y, w, h);
        keysDown = new ArrayList();
        ControlWindow = new JFrame();
        ControlWindow.addKeyListener(controls);
        ControlWindow.setFocusable(true);
        ControlWindow.setFocusTraversalKeysEnabled(false);
        ControlWindow.setTitle("Control Window");
        ControlWindow.setSize(500, 50);
        ControlWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ControlWindow.setVisible(true);
        ControlWindow.setLocation(new Point(sg.getWidth(), 0));

        globe = sg;
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
                if (keysDown.contains(KeyEvent.VK_D)) {
                    r -= sampleRate;
                    b.updatePosition(xTopLeft + r, yTopLeft);
                } else if (keysDown.contains(KeyEvent.VK_A)) {
                    r += sampleRate;
                    b.updatePosition(xTopLeft + r, yTopLeft);
                }
            }
        }

        b.updatePosition(xTopLeft, yTopLeft);
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
                if (r > 0) {
                    r -= sampleRate;
                    //b.updatePosition(xTopLeft, yTopLeft+r);
                } else {
                    r += sampleRate;
                    //b.updatePosition(xTopLeft, yTopLeft-r);
                }
                b.updatePosition(xTopLeft, yTopLeft + r);
            }
            playerIsJumping = false;
        }

        b.updatePosition(xTopLeft, yTopLeft);
        //playerIsJumping = false;
        return r;
    }

    public void visualize() {
        globe.drawFilledBox(xTopLeft, yTopLeft, width, height, Color.GREEN, 1.0, "Player");
    }

    @Override
    public void reactToTimer(long l) {
        if (keysDown.contains(KeyEvent.VK_D)) {
            movement = Math.min(speedX, checkDistanceX(speedX));
            globe.animMoveAllRel(movement, 0, "Player");
            xTopLeft += movement;
            //System.out.println("Player:   " + xTopLeft + " " + yTopLeft);
            //System.out.println("Collider: " + b.xTopLeft+ " " + b.yTopLeft);
        } else if (keysDown.contains(KeyEvent.VK_A)) {
            movement = Math.max(-1 * speedX, checkDistanceX(-1 * speedX));
            globe.animMoveAllRel(movement, 0, "Player");
            xTopLeft += movement;
            //System.out.println("Player:   " + xTopLeft + " " + yTopLeft);
            //System.out.println("Collider: " + b.xTopLeft+ " " + b.yTopLeft);
        }

        if (!playerIsJumping) {
            if (keysDown.contains(KeyEvent.VK_W)) {
                playerIsJumping = true;
                speedY = jumpStrength;
            } else {
                speedY += gravity;
                speedY = Math.min(speedY, checkDistanceY(speedY));
                globe.animMoveAllRel(0, speedY, "Player");
                yTopLeft += speedY;
            }
        } else {
            speedY += gravity;
            if (speedY > 0) {
                speedY = Math.min(speedY, checkDistanceY(speedY));
            } else if (speedY < 0) {
                speedY = Math.max(speedY, checkDistanceY(speedY));
            }
            globe.animMoveAllRel(0, speedY, "Player");
            yTopLeft += speedY;

        }

        b.updatePosition(xTopLeft, yTopLeft);
        globe.repaintPanel();
    }
}
