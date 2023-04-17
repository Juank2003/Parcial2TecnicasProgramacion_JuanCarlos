package tablesHash;

public class Ship {
    private String name;
    private String type;
    private int number;
    private int level;

    // Constructor
    public Ship(String name, String type, int number, int level) {
        this.name = name;
        this.type = type;
        this.number = number;
        this.level = level;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}