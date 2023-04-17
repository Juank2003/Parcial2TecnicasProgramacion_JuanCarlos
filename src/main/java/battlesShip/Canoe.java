package battlesShip;

import java.awt.*;

public class Canoe extends Ship{

    public Canoe(Point startPoint, Point endPoint) {
        super(startPoint, endPoint);
        this.size = 1;
        this.hitsArray = new boolean[1];
    }
}
