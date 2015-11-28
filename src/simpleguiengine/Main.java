package simpleguiengine;

import simplegui.SimpleGUI;

public class Main {
    
    public static void main(String[] args) {
        SimpleGUI sg = new SimpleGUI();
        
        sg.timerStart(15);
        double squareSize = 50;
        
        OverWorld world = new OverWorld(sg, 49);
        Player p1 = new Player(sg.getWidth() / 20, sg.getHeight() - world.floor - squareSize - 1, squareSize, squareSize, sg);
        world.addGroundObject(new GroundObject(5 * squareSize, sg.getHeight() - world.floor - squareSize - 1, squareSize, squareSize));
        world.addGroundObject(new GroundObject(5 * squareSize + 20, sg.getHeight() - 3 * world.floor - 20, squareSize, squareSize));
        world.addGroundObject(new GroundObject(-10, 0, 10, sg.getHeight()));
        world.addGroundObject(new GroundObject(sg.getWidth(), 0, 10, sg.getHeight()));
        world.addGroundObject(new GroundObject(0, sg.getHeight() - world.floor, sg.getWidth(), world.floor));
        world.addPlayer(p1);
        world.visualizeAll();
        p1.ControlWindow.requestFocus();
    }
}
