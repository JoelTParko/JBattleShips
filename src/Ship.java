public enum Ship {
    AircraftCarrier (5, "Aircraft Carrier"),
    Battleship (4, "Battleship"),
    Cruiser (3, "Carrier"),
    Submarine (3, "Submarine"),
    Destroyer (2, "Destroyer");

    private final int size;
    private final char symbol;
    private final String name;
    private int health;

    Ship(int size, String name){
        this.size = size;
        this.health = size;
        this.name = name;
        this.symbol = name.charAt(0);
    }

    public int getSize(){
        return size;
    }

    public char getSymbol(){
        return symbol;
    }

    public int getHealth(){
        return health;
    }

    public String getName() {return name;}

    public void shipHit(){
        health--;
    }
}
