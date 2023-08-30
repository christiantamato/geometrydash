import java.awt.*;
import java.awt.image.BufferedImage;

public class Screen {
    //size of screen
    private int width, height;
    //interactable buttons
    private Rectangle[] buttons;
    //any images were going to have
    private BufferedImage[] images;
    

    //construcor
    public Screen(int width, int height, int numButtons, int numImages) {
        this.width = width;
        this.height = height;
        //initialize arrays
        this.buttons = new Rectangle[numButtons];
        this.images = new BufferedImage[numImages];
    }

    //getters
     public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle[] getButtons() {
        return buttons;
    }

    public BufferedImage[] getImages() {
        return images;
    }

    public boolean mouseCollides(int mouseX, int mouseY, Rectangle button) {
         //mouse collision checking
        //do a lot of comparing
        if (mouseX < button.x + button.width && mouseX > button.x && mouseY < button.y + button.height && mouseY > button.y) {
            //the shpaes collide
            return true;
        }
        else {
            //the shapes do not collide
            return false;
        }
    }
}
