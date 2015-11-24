package simpleguiengine;
import simplegui.SimpleGUI;

public class Main {

    public static void main(String[] args) {
        SimpleGUI sg = new SimpleGUI();
        
        sg.timerStart(15);
        double squareSize = 50;
        
        OverWorld world = new OverWorld(sg, 50);
        Player p1 = new Player(sg.getWidth()/20, sg.getHeight() - world.floor - squareSize, squareSize, squareSize, sg);
        world.addGroundObject(new GroundObject(9*squareSize, sg.getHeight() - world.floor - squareSize, squareSize, squareSize));
        world.addPlayer(p1);
        world.visualizeAll();
    }
}
