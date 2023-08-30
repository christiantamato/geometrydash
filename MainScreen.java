import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Rectangle;

public class MainScreen extends Screen {
    private int numImages;
    private int numButtons;
    
    public MainScreen(int width, int height, int numImages, int numButtons) {
        super(width, height, numButtons, numImages);
        //add all the stuff on screen

        //buttons
        super.getButtons()[0] = new Rectangle(475, 270, 250, 215);

        //load images
        try {
            // try to load images
            super.getImages()[0] = ImageIO.read(new File("ImageMainScreen.jpg"));
        } 
        catch(Exception e) {
            // if an error occurs, print the error
            e.printStackTrace();
        }
        
    }

    //draw method

    public void draw(Graphics2D g) {
        //draw background
         g.drawImage(super.getImages()[0], 0, 0, 1200, 800, null);

         //buttons (drawings to test collision boxes)
         g.setColor(Color.RED);
         //g.draw(super.getButtons()[0]);

    }

     //handle interactions
    public void buttonInteractions(int mouseX, int mouseY, Cube player) {

        //go through each button and decide what to do with it
        for (Rectangle r : super.getButtons()) {
            //check if it collides
            if(mouseCollides(mouseX, mouseY, r)) {
                //(do actions)

                if(super.getButtons()[0] == r) {
                    //this is play button

                    //set current level of player
                    player.setCurrentLevel(1);
                }
            }
        }
    }
}
