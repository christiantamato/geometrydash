import java.awt.geom.Arc2D;
import java.awt.*;

public class Orb {
    //okay so what are the properties of the orb

    //circular object arc for the orb
    private Arc2D.Double circle;
    private boolean changeDirection;
    //orb color
    private Color orbCol;
    private Color orbCol2;

    //constructor
    public Orb(int x, int y, boolean changeDirection) {
        //make the Arc
        this.circle = new Arc2D.Double(x, y, 60, 60, 0, 360, Arc2D.OPEN);
        //will it change direction?
        this.changeDirection = changeDirection;
        //col
        this.orbCol = new Color(247, 255, 128);
        this.orbCol2 = new Color(255, 173, 248);
    }

    //get the Arc shape once again so we can use it
    public Arc2D.Double getArc() {
        return this.circle;
    }

    public Color getColor() {
        if(!this.changeDirection) {
            return this.orbCol;
        }
        else {
            return this.orbCol2;
        }
    }

    //return change direction
    public boolean getChangeDirection() {
        return this.changeDirection;
    }
}
