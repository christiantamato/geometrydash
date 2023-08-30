import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Rectangle;

public class EndScreen extends Screen {
    private int numImages;
    private int numButtons;
    
    public EndScreen(int width, int height, int numImages, int numButtons) {
        super(width, height, numButtons, numImages);
        //add all the stuff on screen

        //load images
        try {
            // try to load images
            super.getImages()[0] = ImageIO.read(new File("ImageCelebration.gif"));
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

         String celebration = "YOU WIN ;)";
         Font stringFont = new Font("SansSerif", Font.BOLD, 40);
         g.setFont(stringFont);
         g.setColor(Color.WHITE);
         g.drawString(celebration, 800, 600);

    }

     //handle interactions
    public void buttonInteractions(int mouseX, int mouseY, Cube player) {

        //go through each button and decide what to do with it
        for (Rectangle r : super.getButtons()) {
            //check if it collides
            if(mouseCollides(mouseX, mouseY, r)) {
                //(do actions)

                if(super.getButtons()[0] == r) {
                    //this is will be reset button or something idk
                    
                }
            }
        }
    }
}
