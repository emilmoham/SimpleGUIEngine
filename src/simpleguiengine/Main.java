package simpleguiengine;

import simplegui.SimpleGUI;

public class Main {

    public static void main(String[] args) {
        SimpleGUI sg = new SimpleGUI();
        sg.timerStart(30);
        //Player p1 = new Player(sg);
        //World w1 = new World(p1, sg);
        System.out.println(""+ (int)'<');
        double squareSize = 50;
        OverWorld world = new OverWorld(sg, 50);
        world.addGroundObject(new GroundObject(9*squareSize, sg.getHeight() - world.floor - squareSize, squareSize, squareSize));
        world.visualizeAll();
    }
}
