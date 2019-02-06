import javax.swing.*;
import java.awt.*;

public class ShipDisplayView extends JFrame {
    public ShipDisplayView(ShipBoardPanel panel){
        super("Your Ships");
        init(panel);
    }

    private void init(ShipBoardPanel panel) {
        this.setSize(400, 400);
        this.setContentPane(panel);
        this.pack();
        this.setVisible(true);
    }

}
