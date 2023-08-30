import java.awt.*;

public class Spike {
    //properties of a spike
    private int vertices;
    private int[] xPoints;
    private int[] yPoints;
    private Polygon triangle;

    //regular spike constructor, give it the coordinates of where the tip of the spike would be
    public Spike(int midX, int midY, boolean upsideDown) {
        //base and height will be 40 pixels long
        //make the array for x values
        this.xPoints = new int[3];
        this.xPoints[0] = midX - 20;
        this.xPoints[1] = midX;
        this.xPoints[2] = midX + 20;
        //y values
        this.yPoints = new int[3];
        if(upsideDown) {
            this.yPoints[0] = midY - 40;
            this.yPoints[2] = midY - 40;
        }
        else {
            this.yPoints[0] = midY + 40;
            this.yPoints[2] = midY + 40;
        }
        this.yPoints[1] = midY;
        //create triangle
        this.vertices = 3;
        this.triangle = new Polygon(xPoints, yPoints, this.vertices);
    }

    //custom spike constructor
    public Spike(int x1, int y1, int x2, int y2, int x3, int y3) {
        //make the arrays and put them in there
        this.xPoints = new int[3];
        this.xPoints[0] = x1;
        this.xPoints[1] = x2;
        this.xPoints[2] = x3;
        this.yPoints = new int[3];
        this.yPoints[0] = y1;
        this.yPoints[1] = y2;
        this.yPoints[2] = y3;
        this.vertices = 3;

        //make it into a polygon
        this.triangle = new Polygon(xPoints, yPoints, this.vertices);
    }

    //get the shape so we can draw it, do collisions, etc. 
    public Polygon getTriangle() {
        return this.triangle;
    }
}
