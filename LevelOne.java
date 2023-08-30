import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.*;


public class LevelOne extends Level {
    //properties of level one
    //private Color backgroundCol;
    private Color blockCol;
    private BufferedImage backgroundImage;

    public LevelOne(int gravity, String filename) {
        super(gravity);
        //this.backgroundCol = new Color(155, 86, 245);
        this.blockCol = new Color(202, 161, 255);

        //(ground is at 750)!!!
        //border blocks are w = 50
        //regular spikes are h = 40
        //cube is w = 40, so make platforms in that increment

        //make the border blocks
        super.getBorderBlocks().add(new Rectangle(0, 0, 1200, 50));
        super.getBorderBlocks().add(new Rectangle(0, 50, 50, 600));
        super.getBorderBlocks().add(new Rectangle(1150, 165, 50, 600));
        //deco/failsafe blocks
        super.getBorderBlocks().add(new Rectangle(340, 350, 40, 40));
        super.getBorderBlocks().add(new Rectangle(440, 80, 40, 40));
        super.getBorderBlocks().add(new Rectangle(520, 120, 40, 40));
        super.getBorderBlocks().add(new Rectangle(720, 80, 40, 40));
        super.getBorderBlocks().add(new Rectangle(800, 120, 40, 40));

        //ground block that supports cube starting off screen
        super.getBlocks().add(new Rectangle(-100, 750, 1350, 50));
        //first story ledges
        super.getBlocks().add(new Rectangle(880, 600, 120, 15));
        super.getBlocks().add(new Rectangle(660, 545, 80, 15));
        super.getBlocks().add(new Rectangle(460, 505, 40, 15));
        super.getBlocks().add(new Rectangle(300, 550, 80, 15));
        super.getBlocks().add(new Rectangle(200, 470, 40, 15));
        //second story ledges
        super.getBlocks().add(new Rectangle(340, 220, 80, 15));
        super.getBlocks().add(new Rectangle(460, 260, 40, 15));
        super.getBlocks().add(new Rectangle(540, 300, 80, 15));
        super.getBlocks().add(new Rectangle(660, 220, 40, 15));
        super.getBlocks().add(new Rectangle(740, 260, 40, 15));
        super.getBlocks().add(new Rectangle(820, 300, 80, 15));
        //last safe block to cover border when finished
        super.getBlocks().add(new Rectangle(1150, 150, 50, 15));
        
        //first 2 ground spikes
        super.getSpikes().add(new Spike(480, 710, false));
        super.getSpikes().add(new Spike(770, 710, false));
        //deco spikes
        super.getSpikes().add(new Spike(360, 430, true));
        super.getSpikes().add(new Spike(460, 160, true));
        super.getSpikes().add(new Spike(540, 200, true));
        super.getSpikes().add(new Spike(740, 160, true));
        super.getSpikes().add(new Spike(820, 200, true));

        //first story orb
        super.getOrbs().add(new Orb(1070, 600, true));
        //second story orbs
        super.getOrbs().add(new Orb(60, 360, true));
        super.getOrbs().add(new Orb(160, 260, false));
        super.getOrbs().add(new Orb(1000, 200, false));

        //saws 
        super.getSaws().add(new Saw(810, 580, 32));
        super.getSaws().add(new Saw(570, 540, 40));
        super.getSaws().add(new Saw(260, 530, 21));
        super.getSaws().add(new Saw(130, 520, 45));
        super.getSaws().add(new Saw(660, 280, 21));
        super.getSaws().add(new Saw(1030, 360, 60));

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

    public void draw(Graphics2D g, Cube player){
        //draw background
        //g.setColor(this.backgroundCol);
        //g.fillRect(0, 0, 1200, 800);
        g.drawImage(this.backgroundImage, 0, 0, 1200, 750, null);

        //player entering level
        if(player.getCollisionBox().x > 100 && !super.getEnteredLevel()) {
            super.setEnteredLevel(true);
            //make a new rectangle to close the door
            super.getBorderBlocks().add(new Rectangle(0, 650, 50, 100));
        }

        //player exiting level
        if(player.getCollisionBox().x > 1250 && !super.getExitedLevel()) {
            super.setExitedLevel(true);
            //another rectangle to close the door when you leave
            super.getBorderBlocks().add(new Rectangle(1150, 50, 50, 100));
            //set the values for the next level
            player.setCoords(-100, 110);
            player.setCurrentLevel(2);
        }

        //draw each border block
        for (Rectangle r : super.getBorderBlocks()) {
            //regular border blocks
            g.setColor(Color.BLACK);
            if(super.getBorderBlocks().indexOf(r) >= 3 && super.getBorderBlocks().indexOf(r) <= 7) {
                //deco blocks
                g.setColor(blockCol);
            }
            g.fill(r);
        }

        // draw blocks
        for (int i = 0; i < super.getBlocks().size(); i++) {
            g.setColor(blockCol);
            //the only 2 black blocks you can stand on
            if(i == 0 || i == 12) {
                //ground
                g.setColor(Color.BLACK);
            }
            //fill
            g.fill(super.getBlocks().get(i));
        }

        //draw door and border blocks, firstly check if they are in the levels and what not
        //make sure the rectangle is only made once as well

        //draw each spike
        g.setColor(Color.WHITE);
        for(Spike s : super.getSpikes()) {
            g.fill(s.getTriangle());
        }

        //draw each orb
        for(Orb o : super.getOrbs()) {
            g.setColor(o.getColor());
            g.fill(o.getArc());
        }

        //draw each saw
        g.setColor(Color.WHITE);
        for(Saw s: super.getSaws()) {
            g.fill(s.getSaw());
        }
    }
}

