import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.*;

public class Cube {
    //properties that the cube is going to have
    private Rectangle collisionBox;
    private int speed;
    private int moveY;
    private boolean movingRight;
    private boolean movingLeft;
    private boolean onGround;
    private boolean orbProximity;
    private Orb currentOrb;
    //int variable to keep track of which level cube is in
    private int currentLevel;
    //private Color cubeCol;
    //image for cube
    private BufferedImage cubeImage;

    //constructor that will make the cube
    public Cube(int x, int y, String filename) {
        //make the collision box
        this.collisionBox = new Rectangle(x, y, 40, 40);
        //set the speed the cube is going to move at
        this.speed = 7;
        this.movingLeft = false;
        //start the cube moving right
        this.movingRight = true;
        this.onGround = false;
        this.orbProximity = false;
        this.currentOrb = null;
        //this.cubeCol = new Color(120, 255, 190);
        //current level starts at level one
        this.currentLevel = 0;

        //loadImage
        try {
            // try to load images
            this.cubeImage = ImageIO.read(new File(filename));
        } 
        catch(Exception e) {
            // if an error occurs, print the error
            e.printStackTrace();
        }
    }

    //getters
    public Rectangle getCollisionBox() {
        return this.collisionBox;
    }

    public boolean getMovingLeft() {
        return this.movingLeft;
    }

    public int getCurrentLevel() {
        return this.currentLevel;
    }

    //setters
    public void setRight(boolean b) {
        this.movingRight = b;
    }

    public void setLeft(boolean b) {
        this.movingLeft = b;
    }
    
    public void setCoords(int x, int y) {
        this.collisionBox.x = x;
        this.collisionBox.y = y;
    }

    public void setCurrentLevel(int levelNum) {
        this.currentLevel = levelNum;
    }

    //jumping method
    public void jump() {
        //cube can jump if within an orb or on ground
        if(this.onGround) {
            //apply a negative y force
            this.moveY = -14;
            //not on ground anymore
            this.onGround = false;
        }
        else if(orbProximity) {
            //allow jump
            this.moveY = -16;
            //not in proximity anymore
            this.orbProximity = false;
            //if the current orb requires direction change do it
            if(this.currentOrb.getChangeDirection()) {
                if(movingRight) {
                    //switch the moving direction
                    movingRight = false;
                    movingLeft = true;
                }
                else {
                    //switch back to right
                    movingLeft = false;
                    movingRight = true;
                }
            }  
        }
    }

    public void draw(Graphics2D g) {
        //set the color
        //g.setColor(cubeCol);
        //g.fill(this.collisionBox);
        //draw with image
        g.drawImage(cubeImage, this.collisionBox.x, this.collisionBox.y, 40, 40, null);
    }

    //check collisions with blocks
    public boolean checkBlockCollision(Rectangle r) {
        return this.collisionBox.intersects(r);
    }

    //check collisions with spikes
    public boolean checkSpikeCollision(Spike s) {
        return s.getTriangle().intersects(collisionBox);
    }

    //check collisions with orbs
    public boolean checkOrbCollision(Orb o) {
        return o.getArc().intersects(collisionBox);
    }

    //check collisions with saws
    public boolean checkSawCollision(Saw s) {
        return s.getSaw().intersects(collisionBox);
    }

    public void handleBlockCollision(Rectangle r) {
        // get collision overlap
        Rectangle overlap = this.collisionBox.intersection(r);
        // what is the smallest thing to correct
        if(overlap.width < overlap.height){
            // fix left or right?
            if(this.collisionBox.x < r.x){
                // on the left
                this.collisionBox.x -= overlap.width;
            }else{
                // on the right
                this.collisionBox.x += overlap.width;
            }
        }else{
            // fix up or down
            if(this.moveY < 0){
                // moving up
                this.collisionBox.y += overlap.height;
                // stop moving more up
                this.moveY = 0;
            }else{
                // moving down
                this.collisionBox.y -= overlap.height;
                // stop moving down
                this.moveY = 0;
                // on the ground
                this.onGround = true;
            }
        }
    }

    public void handleDeathCollision() {
        //because spikes and border blocks both cause death the methods is just the same

        //respawn point 
        collisionBox.x = -100;
        collisionBox.y = 110;
        this.movingLeft = false;
        this.movingRight = true;

        
        if(this.currentLevel == 2) {
            //go back to level one lol
            this.currentLevel = 1;
        }
       
    }

    //handling orb collision
    public void handleOrbCollision(Orb o) {
        //let the cube know its in proximity of orb, set current orb, and allow jumps now
        this.currentOrb = o;
        this.orbProximity = true;
    }

    public void update(int gravity) {
        this.onGround = false;
        this.orbProximity = false;

        //gravity
        this.moveY += gravity;

        // sideways movement
        if(this.movingLeft){
            this.collisionBox.x -= this.speed;
        }
        else if(this.movingRight){
            this.collisionBox.x += this.speed;
        }
        // move vertically
        this.collisionBox.y += this.moveY;

    }
}
