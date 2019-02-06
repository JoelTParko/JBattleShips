import javax.swing.*;
import java.awt.*;

public class ShipBoardPanel extends BoardPanel {

    private Board board;
    private int[] savedPosition = null;


    public ShipBoardPanel(Board board){
        super();
        this.board = board;
    }
    public void setHit(int row, int column){
        board.setHit(row, column);
    }
    public boolean addShip(Ship ship, int xPos, int yPos){
        int[] position = registerClick(xPos, yPos);
        if(savedPosition==null) {
           savedPosition = position;
           return false;
        }else if (savedPosition[0] == position[0] && savedPosition[1]<position[1]) {
            if(!board.addShip(ship, savedPosition[1], savedPosition[0], 0)) {JOptionPane.showMessageDialog(null, "Error, requested positioning would cause ships to collide"); return false;}
            clearSavedPosition();
        }else if (savedPosition[0] == position[0] && savedPosition[1]>position[1]) {
            if(!board.addShip(ship, savedPosition[1]-ship.getSize()+1, savedPosition[0], 0)) {JOptionPane.showMessageDialog(null, "Error, requested positioning would cause ships to collide"); return false;}
            clearSavedPosition();
        }else if (savedPosition[1] == position[1] && savedPosition[0]<position[0]) {
            if(!board.addShip(ship, savedPosition[1], savedPosition[0], 1)) {JOptionPane.showMessageDialog(null, "Error, requested positioning would cause ships to collide"); return false;}
            clearSavedPosition();
        }else {
            if(!board.addShip(ship, savedPosition[1], savedPosition[0]-ship.getSize()+1, 1)) {JOptionPane.showMessageDialog(null, "Error, requested positioning would cause ships to collide"); return false;}
            clearSavedPosition();
        }
        repaint();
        return true;
    }

    public void clearSavedPosition(){
        savedPosition = null;
    }

    @Override
    public void paintComponent(Graphics g){
        char shipChar;
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int count = 0;
        int boardHeight = getBoardHeight();
        int boardWidth = getBoardWidth();
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                shipChar = board.getTile(i, j);
                if(shipChar != ' '){
                    g2d.drawString(String.valueOf(shipChar), 5*getWidth()/40 + j*boardWidth/10, 6*getHeight()/40 +i*boardHeight/10);
                    System.out.println(count);
                }
                if(board.beenHit(i, j)){
                    g2d.setColor(Color.RED);
                    g2d.drawLine(getWidth()/10+boardWidth/40+i*boardWidth/10, getHeight()/10+boardHeight/40+j*boardHeight/10, getWidth()/10+3*boardWidth/40+i*boardWidth/10, getHeight()/10+3*boardHeight/40+j*boardHeight/10);
                    g2d.drawLine(getWidth()/10+3*boardWidth/40+i*boardWidth/10, getHeight()/10+boardHeight/40+j*boardHeight/10, getWidth()/10+boardWidth/40+i*boardWidth/10, getHeight()/10+3*boardHeight/40+j*boardHeight/10);
                }
                count++;
            }

        }
    }

}
