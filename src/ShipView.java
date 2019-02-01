import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ShipView extends JFrame {

    ShipPanel selectedPanel = null;
    Board board;

    public ShipView(Board board){
        super("Your Ships");
        this.board = board;
        init();
    }

    private void init(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(new Dimension(1000, 800));
        this.setLocation(screenSize.width/2-this.getSize().width/2, screenSize.height/2-this.getSize().height/2);
        this.setPreferredSize(new Dimension(600, 600));
        JPanel superPanel = new JPanel();
        this.setContentPane(superPanel);

        superPanel.setLayout(new BorderLayout());

        JLabel screenLabel = new JLabel("Your Board");
        screenLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        superPanel.add(screenLabel, BorderLayout.NORTH);

        ShipBoardPanel boardPanel = new ShipBoardPanel(board);
        boardPanel.addMouseListener(new ShipBoardListener(boardPanel));

        JPanel shipPanel = new JPanel(new GridLayout(5, 1));
        shipPanel.setPreferredSize(new Dimension(this.getWidth()/5, getHeight()));

        ShipPanel carrierPanel = new ShipPanel("AircraftCarrier");
        ShipPanel battleshipPanel = new ShipPanel("Battleship");
        ShipPanel cruiserPanel = new ShipPanel("Cruiser");
        ShipPanel subPanel = new ShipPanel("Submarine");
        ShipPanel destroyerPanel = new ShipPanel("Destroyer");

        carrierPanel.addMouseListener(new ShipChoiceListener(carrierPanel));
        battleshipPanel.addMouseListener(new ShipChoiceListener(battleshipPanel));
        cruiserPanel.addMouseListener(new ShipChoiceListener(cruiserPanel));
        subPanel.addMouseListener(new ShipChoiceListener(subPanel));
        destroyerPanel.addMouseListener(new ShipChoiceListener(destroyerPanel));

        shipPanel.add(carrierPanel);
        shipPanel.add(battleshipPanel);
        shipPanel.add(cruiserPanel);
        shipPanel.add(subPanel);
        shipPanel.add(destroyerPanel);

        superPanel.add(shipPanel, BorderLayout.EAST);
        superPanel.add(boardPanel, BorderLayout.CENTER);


        this.pack();
        this.setVisible(true);
    }

    private void deselectPanel(){
        if(selectedPanel != null) {
            selectedPanel.reset();
            selectedPanel = null;
        }
        repaint();
    }


    private void repaintView() {
        repaint();
    }

    class ShipChoiceListener implements MouseListener {

        private ShipPanel ship;

        ShipChoiceListener(ShipPanel ship){
            this.ship=ship;
        }

        /**
         * Invoked when the mouse button has been clicked (pressed
         * and released) on a component.
         *
         * @param e the event to be processed
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            if(!ship.isPlaced()) {
                deselectPanel();
                ship.clicked();
                selectedPanel = ship;
            }
        }

        /**
         * Invoked when a mouse button has been pressed on a component.
         *
         * @param e the event to be processed
         */
        @Override
        public void mousePressed(MouseEvent e) {

        }

        /**
         * Invoked when a mouse button has been released on a component.
         *
         * @param e the event to be processed
         */
        @Override
        public void mouseReleased(MouseEvent e) {

        }

        /**
         * Invoked when the mouse enters a component.
         *
         * @param e the event to be processed
         */
        @Override
        public void mouseEntered(MouseEvent e) {
            if(!ship.isPlaced()){
                ship.mouseEntered();
                repaintView();
            }
        }

        /**
         * Invoked when the mouse exits a component.
         *
         * @param e the event to be processed
         */
        @Override
        public void mouseExited(MouseEvent e) {
            if(ship != selectedPanel && !ship.isPlaced()){
                ship.reset();
                repaintView();
            }
        }
    }

    class ShipBoardListener implements MouseInputListener{

        ShipBoardPanel boardPanel;

        public ShipBoardListener(ShipBoardPanel panel){
            boardPanel = panel;
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            if(selectedPanel != null && boardPanel.addShip(selectedPanel.getShip(), e.getX(), e.getY())) {
                ShipPanel tmpPanel = selectedPanel;
                deselectPanel();
                tmpPanel.placed();
                repaintView();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {
            boardPanel.clearSavedPosition();
        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }

}
