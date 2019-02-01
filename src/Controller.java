public class Controller {

    private Ship[] ships;
    private Board board;


    public Controller() throws InterruptedException{
        setUpGame();
        playGame();

    }

    private void setUpGame(){
        ships = Ship.values();
        board = new Board(10);
        placeShips();
        board.displayBoard();
    }

    private void placeShips(){
        for (Ship s: ships) {
            board.placeShip(s);
        }
    }

    private void playGame()throws InterruptedException{
        Board playerBoard = new Board(10);
        ShipView shipview = new ShipView(playerBoard);





        /*
        View view = new View(10);
        int shipsLeft = Ship.values().length;
        Ship s;
        boolean gameWon = false;
        int[] nextShot;
        while(!gameWon){
            nextShot = view.getNextShot();
            if(nextShot != null && !board.beenHit(nextShot[0], nextShot[1])){
                board.setHit(nextShot[0], nextShot[1]);
                view.log("Shot fired at sector ("+(nextShot[0]+1)+","+(nextShot[1]+1)+")");
                if(checkShipHit(nextShot)){
                    view.log("It's a hit!\n");
                    view.setHitType(nextShot[1], nextShot[0], true); //Rows are [1], columns are [0]. Oops
                    s = getTile(board.getTile(nextShot[0], nextShot[1]));
                    s.shipHit();
                    if(s.getHealth()==0) {
                        System.out.println("You just sunk the " + s.toString());
                        view.log("You just sunk the " + s.toString());
                        view.log("");
                        shipsLeft--;
                        if (shipsLeft == 0){
                            gameWon = true;
                        }
                    }
                }else{
                    view.log("It's a miss\n");
                    view.setHitType(nextShot[1], nextShot[0], false); //Rows are [1], columns are [0]. Oops
                }
                view.repaint();
            }
            Thread.sleep(50);
        }
        view.log("Congratulations\n You have won BattleShips!!");
        */
    }

    private Ship getShip(char s){
        switch (s){
            case 'A':
                return ships[0];
            case 'B':
                return ships[1];
            case 'C':
                return ships[2];
            case 'S':
                return ships[3];
            case 'D':
                return ships[4];
            default:
                System.err.println("Error, invalid ship char");
                return null;
        }
    }

    private boolean checkShipHit(int[] shot){
        if(board.getTile(shot[0],shot[1])!=' '){
            return true;
        }
        return false;
    }
}