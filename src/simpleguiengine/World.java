package simpleguiengine;

import java.awt.Color;
import simplegui.KeyboardListener;
import simplegui.TimerListener;
import simplegui.SimpleGUI;

public class World implements TimerListener {

    //Player Data
    Player p1;
    double player_X;
    double player_Y;
    int player_size;
    boolean player_is_jumping = false;
    boolean player_is_moving_left = false;
    boolean player_is_moving_right = false;
    double player_velocity_X = 0;
    double player_velocity_Y = 0;

    //Physics Data
    double gravity = 1.0;
    double max_velocity = 24.0;
    double acceleration = 1.0;

    //Visualization Data
    SimpleGUI globe;

    //Player Control
    KeyboardListener event = new KeyboardListener() {

        @Override
        public void reactToKeyboardEntry(String string) {
            //Unused
        }

        @Override
        public void reactToKeyboardSingleKey(String string) {
            char c = string.charAt(0);
            if (c == 'w') {
                globe.print("");
                if (!player_is_jumping) {
                    player_is_jumping = true;
                    player_velocity_Y = -20;

                }
                //System.out.println("UP");
            }
            if (c == 'a') {
                globe.print("");
                if (!player_is_moving_left && !player_is_moving_right) {
                    player_is_moving_left = true;
                }
                //System.out.println("LEFT");
            }
            if (c == 'd') {
                globe.print("");
                if (!player_is_moving_left && !player_is_moving_right) {
                    player_is_moving_right = true;
                }
                //System.out.println("RIGHT");
            }
        }
    };

    public World(Player p, SimpleGUI sg) {
        globe = sg;

        globe.registerToKeyboard(event);
        globe.registerToTimer(this);

        globe.setBackgroundColor(new Color(104, 136, 255));
        globe.drawFilledBox(0, globe.getHeight() - 50, globe.getWidth(), 50, Color.GREEN, 1.0, "Ground");

        p1 = p;
        player_X = p1.startX;
        player_Y = p1.startY;
        player_size = p1.playerSize;

        visualize();
    }

    public void visualize() {
        p1.visualize();
    }

    public boolean playerIsOnGround() {
        if (player_velocity_Y < 0) {
            return false;
        } else {
            return (player_Y + player_size >= globe.getHeight() - 50);
        }
    }

    @Override
    public void reactToTimer(long l) {
        if (player_is_jumping) {
            if (playerIsOnGround()) {
                player_is_jumping = false;

            } else {
                player_velocity_Y += gravity;
                player_Y += player_velocity_Y;
                globe.animMoveAllRel(0, player_velocity_Y, "Player");
            }
        }

        if (player_X + player_size >= globe.getWidth() || player_X <= 0) {
            player_velocity_X = 0;
        }

        if (player_is_moving_left) { /* If left key was pressed */

            //Check if player is stationary or already moving left

            if (player_velocity_X <= 0 && player_velocity_X > -1 * max_velocity) {
                player_velocity_X += -1 * acceleration;
            } //If player is moving right
            else if (player_velocity_X > 0) {
                player_velocity_X += -5 * acceleration;
            }

            //If player is in bounds moving left
            if (player_X > 0) {
                player_X += player_velocity_X;
                globe.animMoveAllRel(player_velocity_X, 0, "Player");
            }

            player_is_moving_left = false;
        } else if (player_is_moving_right) { /* If right key was pressed */

            //If player is stationary or already moving right

            if (player_velocity_X >= 0 && player_velocity_X < max_velocity) {
                player_velocity_X += acceleration;
            } //If player is moving left
            else if (player_velocity_X < 0) {
                player_velocity_X += 5 * acceleration;
            }

            //If the player is in bounds moving right
            if (player_X + player_size < globe.getWidth()) {
                player_X += player_velocity_X;
                globe.animMoveAllRel(player_velocity_X, 0, "Player");
            }

            player_is_moving_right = false;
        } else { /* If no X direction key was pressed */

            if (player_velocity_X > 0) {
                player_velocity_X += -2 * acceleration;
                player_X += player_velocity_X;
                globe.animMoveAllRel(player_velocity_X, 0, "Player");
            } else if (player_velocity_X < 0) {
                player_velocity_X += 2 * acceleration;
                player_X += player_velocity_X;
                globe.animMoveAllRel(player_velocity_X, 0, "Player");
            }
        }

        globe.repaintPanel();
    }
}
