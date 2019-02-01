import javax.swing.*;
import java.awt.*;

public class ShipPanel extends JPanel {
    private Ship ship;
    private boolean placed = false;

    public ShipPanel(String shipName){
        this.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.BLACK));
        ship = Ship.valueOf(shipName);
    }

    public void mouseEntered(){
        this.setBorder(BorderFactory.createMatteBorder(2,2,2,2,new Color(20,220,60)));
    }

    public void clicked(){
        this.setBorder(BorderFactory.createMatteBorder(2,2,2,2, new Color(60, 60, 220)));
    }

    public void reset(){
        this.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.BLACK));
    }

    public Ship getShip(){
        return ship;
    }

    public void placed(){
        placed = true;
        this.setBorder(BorderFactory.createMatteBorder(2,2,2,2,new Color(220,20,60)));
    }

    public boolean isPlaced(){
        return placed;
    }
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g.drawString(ship.getName(), this.getWidth()/2-(ship.getName().length()/2), this.getHeight()/2);
    }
}
