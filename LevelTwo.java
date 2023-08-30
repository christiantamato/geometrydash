import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class LevelTwo extends Level{
    //practically a copy of level one, but different layout, colors, mechanics, etc.
    //private Color backgroundCol;
    private Color blockCol;
    private Color sawCol;
    private BufferedImage backgroundImage;

    //constructor
    public LevelTwo(int gravity, String filename) {
        super(gravity);
        //create the colors
        //this.backgroundCol = new Color(74, 210, 255);
        this.blockCol = new Color(128, 238, 255);
        this.sawCol = new Color (255, 255, 255);

        //border blocks, player enters through top left
        super.getBorderBlocks().add(new Rectangle(0, 0, 1200, 50));
        super.getBorderBlocks().add(new Rectangle(1150, 50, 50, 600));
        super.getBorderBlocks().add(new Rectangle(0, 165, 50, 600));
        
        //deco blocks
        //block supporting spike at start
        super.getBorderBlocks().add(new Rectangle(250, 225, 40, 15));
        //block supporting spike at end
        super.getBorderBlocks().add(new Rectangle(975, 710, 40, 40));
        //block
        super.getBorderBlocks().add(new Rectangle(710, 340, 40, 40));

        //blocks
        //ground block
        super.getBlocks().add(new Rectangle(0, 750, 1200, 50));
        //ledge block for entrance, make it a little off screen so we can make the cube move in
        super.getBlocks().add(new Rectangle(-100, 150, 150, 15));
        //ledges
        super.getBlocks().add(new Rectangle(125, 225, 80, 15));
        super.getBlocks().add(new Rectangle(305, 145, 80, 15));
        super.getBlocks().add(new Rectangle(700, 225, 240, 15));
        //next story ledges
        super.getBlocks().add(new Rectangle(780, 500, 120, 15));
        super.getBlocks().add(new Rectangle(690, 540, 40, 15));
        super.getBlocks().add(new Rectangle(600, 580, 40, 15));
        super.getBlocks().add(new Rectangle(400, 540, 120, 15));
        super.getBlocks().add(new Rectangle(160, 640, 80, 15));

        //orbs
        super.getOrbs().add(new Orb(500, 250, false));
        super.getOrbs().add(new Orb(1070, 400, true));
        super.getOrbs().add(new Orb(70, 500, true));

        //spikes in chronological order
        super.getSpikes().add(new Spike(820, 185, false));
        super.getSpikes().add(new Spike(620, 540, false));
        super.getSpikes().add(new Spike(580, 710, false));
        super.getSpikes().add(new Spike(620, 710, false));
        super.getSpikes().add(new Spike(660, 710, false));
        super.getSpikes().add(new Spike(995, 670, false));
        //deco spikes/failsafe
        super.getSpikes().add(new Spike(270, 185, false));
        super.getSpikes().add(new Spike(730, 420, true));

        //saws
        super.getSaws().add(new Saw(1050, 160, 40));
        super.getSaws().add(new Saw(1050, 550, 51));
        super.getSaws().add(new Saw(400, 400, 30));

        //loadImage
        try {
            // try to load images
            this.backgroundImage = ImageIO.read(new File(filename));
        } 
        catch(Exception e) {
            // if an error occurs, print the error
            e.printStackTrace();
        }
        

    }

    public void draw(Graphics2D g, Cube player) {
        //draw the background
        //g.setColor(backgroundCol);
        //g.fillRect(0, 0, 1200, 800);
        g.drawImage(this.backgroundImage, 0, 0, 1200, 750, null);

        //player entering level
        if(player.getCollisionBox().x > 100 && !super.getEnteredLevel()) {
            super.setEnteredLevel(true);
            //make a new rectangle to close the door
            super.getBorderBlocks().add(new Rectangle(0, 50, 50, 100));
        }

        //player exiting level
        if(player.getCollisionBox().x > 1250 && !super.getExitedLevel()) {
            super.setExitedLevel(true);
            //another rectangle to close the door when you leave
            super.getBorderBlocks().add(new Rectangle(1150, 650, 50, 100));

            //send this man to the abyss (good coding i know)
            player.setCoords(10000, 10000);

            //end screen LMAO
            player.setCurrentLevel(3);
        }

        //draw the border blocks
        for(Rectangle r : super.getBorderBlocks()) {
            g.setColor(Color.BLACK);
            if(super.getBorderBlocks().indexOf(r) >= 3 && super.getBorderBlocks().indexOf(r) <= 5) {
                //deco blocks 
                g.setColor(blockCol);
            }
            g.fill(r);
        }

        //draw the blocks
        for(Rectangle r : super.getBlocks()) {
            g.setColor(blockCol);
            if(super.getBlocks().indexOf(r) == 0 || super.getBlocks().indexOf(r) == 1) {
                //ground block or ledge block are black
                g.setColor(Color.BLACK);
            }
            g.fill(r);
        }

        //draw the orbs
        for(Orb o : super.getOrbs()) {
            g.setColor(o.getColor());
            g.fill(o.getArc());
        }

        //draw the spikes
        g.setColor(Color.WHITE);
        for(Spike s : super.getSpikes()) {
            g.fill(s.getTriangle());
        }

        //draw the saws
        g.setColor(this.sawCol);
        for(Saw s : super.getSaws()) {
            g.fill(s.getSaw());
        }
    }
}
