import java.awt.*;

public class TargetPanel extends BoardPanel {

    private int[][] board;

    public TargetPanel(int size){
        super();
        setUpBoard(size);
    }

    private void setUpBoard(int size){
        this.board = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = -1;
            }
        }
    }

    public int getBoardSector(int row, int column) {
        return board[row][column];
    }

    public void setHitType(int row, int column, boolean shipHit){
        if(shipHit){
            board[row][column] = 1;
        }else{
            board[row][column] = 0;
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        int width = getWidth();
        int height = getHeight();
        int boardHeight = height-height/5;
        int boardWidth = width-width/5;

        //Draw shots onto the board
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (getBoardSector(i,j) > -1){
                    if(getBoardSector(i, j) == 1){
                        g2d.setColor(Color.RED);
                    }else{
                        g2d.setColor(Color.BLACK);
                    }
                    g2d.drawLine(width/10+boardWidth/40+i*boardWidth/10, height/10+boardHeight/40+j*boardHeight/10, width/10+3*boardWidth/40+i*boardWidth/10, height/10+3*boardHeight/40+j*boardHeight/10);
                    g2d.drawLine(width/10+3*boardWidth/40+i*boardWidth/10, height/10+boardHeight/40+j*boardHeight/10, width/10+boardWidth/40+i*boardWidth/10, height/10+3*boardHeight/40+j*boardHeight/10);
                }
            }
        }
    }

}
