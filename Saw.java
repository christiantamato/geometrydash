import java.awt.*;

public class Saw {
    private Polygon saw;
    private int[] xPoints;
    private int[] yPoints;
    private int vertices;

    //constructor for a custom size saw
    public Saw(int midX, int midY, int rad) {
        //okay lets go (mental math activate)
        this.xPoints = new int[8];
        this.yPoints= new int[8];

        //blade tip
        this.xPoints[0] = midX;
        this.yPoints[0] = midY - rad;

        this.xPoints[1] = this.xPoints[0] + rad/4;
        this.yPoints[1] = this.yPoints[0] + (3 * (rad/4));

        //blade tip
        this.xPoints[2] = midX + rad;
        this.yPoints[2] = midY;

        this.xPoints[3] = this.xPoints[2] - (3 * (rad/4));
        this.yPoints[3] = this.yPoints[2] + rad/4;

        //blade tip
        this.xPoints[4] = midX;
        this.yPoints[4] = midY + rad;

        this.xPoints[5] = this.xPoints[4] - rad/4;
        this.yPoints[5] = this.yPoints[4] - (3 * (rad/4));

        //blade tip
        this.xPoints[6] = midX - rad;
        this.yPoints[6] = midY;

        this.xPoints[7] = this.xPoints[6] + (3 * (rad/4));
        this.yPoints[7] = this.yPoints[6] - rad/4;

        this.vertices = 8;

        //makes it
        this.saw = new Polygon(xPoints, yPoints, vertices);
    }

    //get it
    public Polygon getSaw() {
        return this.saw;
    }
}
