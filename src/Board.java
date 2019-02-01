import java.util.Random;

public class Board {
    private int size;
    private BoardSector[][] board;

    public Board(int size){
        this.size = size;
        board = new BoardSector[size][size];
        setUpBoard();
    }

    private void setUpBoard(){
        for (int i = 0; i <size ; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new BoardSector();
            }
        }
    }

    public void displayBoard(){
        System.out.print(" ");
        for (int i = 1; i <= size; i++)
            System.out.printf(" %s", i);
        System.out.println();
        for (int i = 0; i < size; i++) {
            if(i!=9)
                System.out.printf(" %s", (i + 1));
            else
                System.out.print(i+1);
            for (int j = 0; j < size; j++) {
                if(j!=9){System.out.printf("%s|", board[i][j].getTile());}
                else{System.out.printf(" %s", board[i][j].getTile());}
            }
            System.out.println();
        }
    }

    public boolean addShip(Ship s, int x, int y, int orientation){
        if(orientation == 0){
            //First check if space is empty, if no ship is present, ship is added to board
            for (int i = x; i< x + s.getSize(); i++)
                if (board[y][i].getTile() != ' ') return false;
            for (int i = x; i < x+ s.getSize(); i++)
                board[y][i].setTile(s.getSymbol());
        }else{
            for (int i = y; i < y + s.getSize(); i++)
                if(board[i][x].getTile() != ' ') return  false;
            for (int i = y; i < y+ s.getSize(); i++)
                board[i][x].setTile(s.getSymbol());
        }
        return true;
    }

    public void placeShip(Ship s){
        int x, y, orientation;
        boolean canPlace;
        Random rnd = new Random();
        do{
            x = rnd.nextInt(size);
            y = rnd.nextInt(size);
            orientation = rnd.nextInt(2);
            canPlace = checkPlacement(x, y, orientation, s.getSize());
        }while(!canPlace);
        if(orientation == 0){
            for (int i = x; i < x + s.getSize(); i++) {
                board[i][y].setTile(s.getSymbol());
            }
        }else{
            for (int i = y; i < y + s.getSize(); i++) {
                board[x][i].setTile(s.getSymbol());
            }
        }
    }

    public boolean checkPlacement(int x, int y, int orientation, int  length){
        if (!(orientation == 0 && x + (length - 1) < size || orientation == 1 && y + (length - 1) < size)) {
            return false;
        }
        if (orientation == 0) {
            for (int i = x; i < x + length; i++) {
                if (board[i][y].getTile() != ' ') {
                    return false;
                }
            }
        } else {
            for (int i = y; i < y + length ; i++) {
                if (board[x][i].getTile() != ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public void setHit(int row, int column){
        board[row][column].setHit();
    }

    public boolean beenHit(int row, int column){
        return board[row][column].hasBeenHit();
    }

    public char getTile(int row, int column){
        return board[row][column].getTile();
    }

    public int getSize(){
        return size;
    }
}
