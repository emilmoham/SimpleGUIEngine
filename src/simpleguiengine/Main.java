package simpleguiengine;

import simplegui.SimpleGUI;

public class Main {

    public static void main(String[] args) {
        SimpleGUI sg = new SimpleGUI();
        sg.timerStart(30);
        Player p1 = new Player(sg);
        World w1 = new World(p1, sg);
    }
}
