package battlesShip;

import java.awt.*;

public class Ship {
    protected Point startPoint;
    protected Point endPoint;
    protected int size;
    protected int hits;
    protected boolean isSunk;
    protected CardinalPoints direction;
    protected boolean[] hitsArray;

    //Constructor

    public Ship(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.size = calculateSize();
        this.hits = 0;
        this.isSunk = false;
        this.direction = calculateDirection();
        this.hitsArray = new boolean[size];
        for (int i = 0; i < size; i++) {
            hitsArray[i] = false;
        }
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public int getSize() {
        return size;
    }

    public int getHits() {
        return hits;
    }

    public boolean isSunk() {
        return isSunk;
    }

    public void setSunk(boolean sunk) {
        isSunk = sunk;
    }

    public CardinalPoints getDirection() {
        return direction;
    }

    //Metodos MOdificados

    private int calculateSize() {
        int deltaX = (int) Math.abs(endPoint.getX() - startPoint.getX());
        int deltaY = (int) Math.abs(endPoint.getY() - startPoint.getY());
        return Math.max(deltaX, deltaY) + 1;
    }

    private CardinalPoints calculateDirection() {
        int deltaX = (int) (endPoint.getX() - startPoint.getX());
        int deltaY = (int) (endPoint.getY() - startPoint.getY());
        if (deltaX == 0) {
            return deltaY > 0 ? CardinalPoints.SOUTH : CardinalPoints.NORTH;
        } else {
            return deltaX > 0 ? CardinalPoints.EAST : CardinalPoints.WEST;
        }
    }

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

        int hitIndex = direction == CardinalPoints.NORTH || direction == CardinalPoints.SOUTH ? shotY : shotX;

        if (hitsArray[hitIndex]) {
            return false;
        }

        hitsArray[hitIndex] = true;
        hits++;

        if (hits == size) {
            isSunk = true;
        }

        return true;
    }
}
