package battlesShip;

import java.awt.*;

public class Frigate extends Ship{
    public Frigate(Point startPoint, Point endPoint) {
        super(startPoint, endPoint);
        this.size = 3;
        this.hitsArray = new boolean[3];
    }
}
