package tablesHash;

import java.util.HashMap;

public class HashTables {
    private HashMap<String, HashMap<String, Ship>> hashTableType;
    private HashMap<Integer, HashMap<String, Ship>> hashTableNumber;
    private HashMap<String, HashMap<String, Ship>> hashTableName;

    // Constructor
    public HashTables() {
        this.hashTableType = new HashMap<>();
        this.hashTableNumber = new HashMap<>();
        this.hashTableName = new HashMap<>();
    }

    // Methods
    public void addShip(Ship ship) {

        if (hashTableType.containsKey(ship.getType())) {
            HashMap<String, Ship> subTable = hashTableType.get(ship.getType());
            subTable.put(ship.getName(), ship);
        } else {
            HashMap<String, Ship> subTable = new HashMap<>();
            subTable.put(ship.getName(), ship);
            hashTableType.put(ship.getType(), subTable);
        }


        if (hashTableNumber.containsKey(ship.getNumber())) {
            HashMap<String, Ship> subTable = hashTableNumber.get(ship.getNumber());
            subTable.put(ship.getName(), ship);
        } else {
            HashMap<String, Ship> subTable = new HashMap<>();
            subTable.put(ship.getName(), ship);
            hashTableNumber.put(ship.getNumber(), subTable);
        }


        if (hashTableName.containsKey(ship.getName())) {
            HashMap<String, Ship> subTable = hashTableName.get(ship.getName());
            subTable.put(ship.getType(), ship);
        } else {
            HashMap<String, Ship> subTable = new HashMap<>();
            subTable.put(ship.getType(), ship);
            hashTableName.put(ship.getName(), subTable);
        }
    }

    public void removeShip(Ship ship) {

        if (hashTableType.containsKey(ship.getType())) {
            HashMap<String, Ship> subTable = hashTableType.get(ship.getType());
            subTable.remove(ship.getName());
        }

        if (hashTableNumber.containsKey(ship.getNumber())) {
            HashMap<String, Ship> subTable = hashTableNumber.get(ship.getNumber());
            subTable.remove(ship.getName());
        }

        if (hashTableName.containsKey(ship.getName())) {
            HashMap<String, Ship> subTable = hashTableName.get(ship.getName());
            subTable.remove(ship.getType());
        }
    }

    public HashMap<String, Ship> getShipsByType(String type) {
        if (hashTableType.containsKey(type)) {
            return hashTableType.get(type);
        } else {
            return null;
        }
    }

    public HashMap<String, Ship> getShipsByName(String name) {
        if (hashTableName.containsKey(name)) {
            return hashTableName.get(name);
        } else {
            return null;
        }
    }

    public HashMap<String, Ship> getShipsByNumber(int number) {
        if (hashTableNumber.containsKey(number)) {
            return hashTableNumber.get(number);
        } else {
            return null;
        }
    }
}
