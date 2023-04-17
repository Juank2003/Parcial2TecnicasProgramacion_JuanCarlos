package battlesShip;

import java.awt.*;

public class Ship {
    private Point startPoint;
    private Point endPoint;
    private int size;
    private int hits;
    private boolean isSunk;
    private CardinalPoints direction;

    //Constructor

    public Ship(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.size = calculateSize();
        this.hits = 0;
        this.isSunk = false;
        this.direction = calculateDirection();
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
        return (int) (Math.max(Math.abs(startPoint.getX() - endPoint.getX()),
                Math.abs(startPoint.getY() - endPoint.getY())) + 1);
    }

    private CardinalPoints calculateDirection() {
        if (startPoint.getX() == endPoint.getX()) {
            if (startPoint.getY() > endPoint.getY()) {
                return CardinalPoints.NORTH;
            } else {
                return CardinalPoints.SOUTH;
            }
        } else if (startPoint.getY() == endPoint.getY()) {
            if (startPoint.getX() > endPoint.getX()) {
                return CardinalPoints.WEST;
            } else {
                return CardinalPoints.EAST;
            }
        } else {
            return null;
        }
    }

    public boolean getShot(Point shotPoint) {
        if (isSunk()) {
            return false;
        }

        if (shotPoint.getX() >= getStartPoint().getX() && shotPoint.getX() <= getEndPoint().getX() &&
                shotPoint.getY() >= getStartPoint().getY() && shotPoint.getY() <= getEndPoint().getY()) {

            int hitIndex = -1;
            for (int i = 0; i < getSize(); i++) {
                Point point = new Point(getStartPoint());
                if (getDirection() == CardinalPoints.NORTH || getDirection() == CardinalPoints.SOUTH) {
                    point.translate(i, 0);
                } else {
                    point.translate(0, i);
                }
                if (shotPoint.equals(point)) {
                    hitIndex = i;
                    break;
                }
            }

            if (hitIndex == -1) {
                return false;
            }

            hits++;
            if (hits == size) {
                isSunk = true;
            }

            return true;
        }

        return false;
    }
}
