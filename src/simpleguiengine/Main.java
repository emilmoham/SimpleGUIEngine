package simpleguiengine;
import simplegui.SimpleGUI;

public class Main {

    public static void main(String[] args) {
        SimpleGUI sg = new SimpleGUI();
        
        sg.timerStart(15);
        double squareSize = 50;
        
        OverWorld world = new OverWorld(sg, 50);
        Player p1 = new Player(sg.getWidth()/20, sg.getHeight() - world.floor - squareSize, squareSize, squareSize, sg);
        //world.addGroundObject(new GroundObject(5*squareSize, sg.getHeight() - world.floor - squareSize, squareSize, squareSize));
        world.addPlayer(p1);
        //world.addGroundObject(new GroundObject(5*squareSize, sg.getHeight() - world.floor - squareSize, squareSize, squareSize));
        world.addGroundObject(new GroundObject(-10, 0, 10, sg.getHeight()));
        world.addGroundObject(new GroundObject(sg.getWidth(),0,10,sg.getHeight()));
        world.visualizeAll();
        
        
    }
}
