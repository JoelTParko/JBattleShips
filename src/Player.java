import java.util.HashMap;

public class Player {
    HashMap<Ship, int[]> ships = new HashMap();

    public Player(){

    }

    public boolean placeShip(Ship s, int row, int column, int orientation){
        if(ships.size() < 5){
            ships.put(s, new int[]{row, column, orientation});
            return true;
        }
        return false;
    }
}
