package battlesShip;

import java.awt.*;
import java.util.ArrayList;

public class User {
    private ArrayList<Ship> ships;
    private boolean isAlive;

    //Constructor

    public User(ArrayList<Ship> ships) throws IllegalArgumentException {
        if (ships.isEmpty()) {
            throw new IllegalArgumentException("El usuario debe tener al menos un barco.");
        }
        this.ships = ships;
        this.isAlive = true;
    }
    public ArrayList<Ship> getShips() {
        return ships;
    }

    public void setShips(ArrayList<Ship> ships) {
        this.ships = ships;
    }

    public boolean isAlive() {
        return isAlive;
    }

    // Attack method
    public boolean attack(Point shotPoint, User user) throws IllegalArgumentException {
        if (user == null) {
            throw new IllegalArgumentException("El usuario al que se va a atacar no puede ser nulo.");
        }
        for (Ship ship : user.getShips()) {
            if (ship.getShot(shotPoint)) {
                return true;
            }
        }
        return false;
    }

    // Get shot method
    public boolean getShot(Point shotPoint) {
        for (Ship ship : ships) {
            if (ship.getShot(shotPoint)) {
                if (isAllShipsSunk()) {
                    isAlive = false;
                }
                return true;
            }
        }
        return false;
    }

    // Is all ships sunk method
    private boolean isAllShipsSunk() {
        for (Ship ship : ships) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        return true;
    }

    // Die method
    public void die() {
        isAlive = false;
    }
}
