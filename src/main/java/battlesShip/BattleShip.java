package battlesShip;

import java.awt.*;
import java.util.ArrayList;

public class BattleShip extends Ship {
    private boolean[] isolatedHitsArray;

    //Constructor
    public BattleShip(Point startPoint, Point endPoint) {
        super(startPoint, endPoint);
        this.size = 5;
        this.hitsArray = new boolean[5];
    }

    @Override
    public boolean getShot(Point shotPoint) {
        if (isSunk) {
            return false;
        }

        int deltaX = (int) (endPoint.getX() - startPoint.getX());
        int deltaY = (int) (endPoint.getY() - startPoint.getY());

        int shotX = (int) (shotPoint.getX() - startPoint.getX());
        int shotY = (int) (shotPoint.getY() - startPoint.getY());

        if (shotX < 0 || shotX >= size || shotY < 0 || shotY >= size) {
            return false;
        }

        int hitIndex;
        if (deltaX == 0) {
            hitIndex = shotY;
        } else {
            hitIndex = shotX;
        }

        if (!hitsArray[hitIndex]) {
            hitsArray[hitIndex] = true;
            hits++;
        }

        if (hits == 5) {
            isSunk = true;
        }

        return true;
    }
}