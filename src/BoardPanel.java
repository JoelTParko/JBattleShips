import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class BoardPanel extends JPanel {


    private int boardHeight;
    private int boardWidth;

    public BoardPanel(){
        super();
        this.boardHeight = this.getHeight() - this.getHeight()/5;
        this.boardWidth = this.getWidth() - this.getWidth()/5;
    }

    public int[] registerClick(int x, int y){
        int column, row;
        column = findClickColumn(x);
        row = findClickRow(y);
        return new int[] {row, column};
    }

    public int getBoardWidth(){
        return boardWidth;
    }
    public int getBoardHeight(){
        return boardHeight;
    }


    private int findClickColumn(int x){
        if(x > boardWidth/10){
            for (int i = 0; i < 10; i++) {
                if((getWidth()/10 + (i+1)*boardWidth/10) > x){
                    return i;
                }
            }
        }
        return -1;
    }

    private int findClickRow(int y){
        if(y >boardHeight/10){
            for (int i = 0; i < 10; i++) {
                if((5*this.getHeight()/40 + this.getHeight()/240 + i*boardHeight/10+boardHeight/15) > y){
                    return i;
                }
            }
        }

        return -1;
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(new Font("TimesNewRoman", Font.PLAIN, 20));
        int width = this.getWidth();
        int height = this.getHeight();
        boardHeight = height-height/5;
        boardWidth = width-width/5;
        //Draw board
        g2d.drawRect(width/10, height/10, boardWidth, boardHeight);
        for (int i = 0; i < 10; i++) {
            g2d.drawString(Integer.toString(i+1), 5*width/40 + i*boardWidth/10, height/10 - 15);
            g2d.drawLine(width/10 + i*boardWidth/10, height/10, width/10 + i*boardWidth/10,height/10+boardHeight);
            g2d.drawString(Integer.toString(i+1), width/10 - 30, 6*height/40 + i*boardHeight/10);
            g2d.drawLine(width/10, height/10 + i*boardHeight/10, width/10+boardWidth, height/10 + i*boardHeight/10);
        }

    }
}
