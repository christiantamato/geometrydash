import java.util.ArrayList;
import java.awt.Rectangle;

public class Level {
    //properties that each level has
    private int gravity;
    private ArrayList<Rectangle> blocks;
    private ArrayList<Spike> spikes;
    private ArrayList<Orb> orbs;
    private ArrayList<Rectangle> borderBlocks;
    private ArrayList<Saw> saws;
    private boolean enteredLevel;
    private boolean exitedLevel;

    //constructor for a level
    public Level(int gravity) {
        this.gravity = gravity;
        this.enteredLevel = false;
        this.exitedLevel = false;
        this.blocks = new ArrayList<>();
        this.spikes = new ArrayList<>();
        this.orbs = new ArrayList<>();
        this.borderBlocks = new ArrayList<>();
        this.saws = new ArrayList<>();
    }

    //getters
    public int getGravity() {
        return this.gravity;
    }

    public boolean getEnteredLevel() {
        return this.enteredLevel;
    }

    public boolean getExitedLevel() {
        return this.exitedLevel;
    }

    public ArrayList<Rectangle> getBlocks() {
        return this.blocks;
    }

    public ArrayList<Spike> getSpikes() {
        return this.spikes;
    }

    public ArrayList<Orb> getOrbs() {
        return this.orbs;
    }

    public ArrayList<Rectangle> getBorderBlocks() {
        return this.borderBlocks;
    }

    public ArrayList<Saw> getSaws() {
        return this.saws;
    }

    //setters
    public void setEnteredLevel(boolean b) {
        this.enteredLevel = b;
    }

    public void setExitedLevel(boolean b) {
        this.exitedLevel = b;
    }

    public void handleDeathCollision() {
        //we gotta reset the level and stuff, as well as remove the doors that are created everytime
        if(this.enteredLevel) {
            //remove a door
            this.borderBlocks.remove(this.borderBlocks.size() - 1);
        }
        if(this.exitedLevel) {
            //also remove this door if it was created
            this.borderBlocks.remove(this.borderBlocks.size() - 1);
        }
        //reset variables
        this.enteredLevel = false;
        this.exitedLevel = false;
        
    }

    //handle collisions with objects in the level
    public void handleCollisions(Cube player){
        //boolean to fix concurrent modification
        boolean dead = false;

        //go through each block
        for(Rectangle blockRect: this.blocks){
            //does the player hit this block?
            if(player.checkBlockCollision(blockRect)){
                //handle it
                player.handleBlockCollision(blockRect);
            }
        }

        //go through each border block(blocks that kill you when collision happens)
        for(Rectangle borderRect: this.borderBlocks) {
            //does the player hit the block? 
            if(player.checkBlockCollision(borderRect)) {
                //handle it
                player.handleDeathCollision();
                dead = true;
            }
        }

        //after we are done iterating, then handle level reset
        if(dead) {
            this.handleDeathCollision();
        }

        //go through each spike
        for(Spike s : this.spikes) {
            //does the player hit the spike? 
            if(player.checkSpikeCollision(s)) {
                //handle death collision
                player.handleDeathCollision();
                this.handleDeathCollision();
            }
        }

        //go through each orb
        for(Orb o : this.orbs) {
            //does the player hit the orb? 
            if(player.checkOrbCollision(o)) {
                //handle death collision
                player.handleOrbCollision(o);
            }
        }

        //go through each saw
        for(Saw s : this.saws) {
            //do they hit the saw
            if(player.checkSawCollision(s)) {
                //handle death collision
                player.handleDeathCollision();
            }
        }
    }
}
