import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class View extends JFrame {

    private int[] sectorLocation = null;
    Board playerBoard;

    public View(int size, Board playerBoard){
        super("BattleShips");
        this.playerBoard = playerBoard;
        init(size);
    }
    public void init(int size){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(1000, 800));
        JPanel superPanel = new JPanel();
        JPanel logPanel = new JPanel();
        JPanel toolsPanel = new JPanel();
        JPanel gamePanel = new TargetPanel(size);

        logPanel.setLayout(new GridLayout(1,1));
        JTextArea logBox = new JTextArea();
        JScrollPane scroll = new JScrollPane(logBox);
        DefaultCaret caret = (DefaultCaret)logBox.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        logBox.setEditable(false);
        logBox.append("Welcome To BattleShips\n");
        logBox.append("---------------------------------------\n");
        logPanel.add(scroll);

        gamePanel.addMouseListener(new ClickListener((TargetPanel)gamePanel, this));

        toolsPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        JButton button = new JButton(new AbstractAction("Your Ships") {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShipDisplayView view = new ShipDisplayView(new ShipBoardPanel(playerBoard));
            }
        });
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.weightx = 1;
        c.gridx = 1;
        c.gridy = 0;
        toolsPanel.add(button, c);

        superPanel.setLayout(new BorderLayout());
        superPanel.add(logPanel, BorderLayout.EAST);
        logPanel.setPreferredSize(new Dimension(190, this.getHeight()));
        superPanel.add(toolsPanel, BorderLayout.SOUTH);
        superPanel.add(gamePanel, BorderLayout.CENTER);

        this.setContentPane(superPanel);
        this.setVisible(true);
    }

    public void setHitType(int row, int column, boolean shipHit){
        TargetPanel panel = (TargetPanel)this.getContentPane().getComponent(2);
        panel.setHitType(row, column, shipHit);
    }

    public void log(String input){
        JTextArea logBox = (JTextArea)this.getContentPane().getComponent(0).getComponentAt(1,1).getComponentAt(1,1).getComponentAt(1,1);
        logBox.append(input);
        logBox.append("\n");
    }

    public int[] getNextShot(){
        if(sectorLocation!=null){
            int[] tmpShot = sectorLocation;
            sectorLocation = null;
            return tmpShot;
        }
        return null;
    }
    class ClickListener implements MouseListener{
        JFrame container;
        BoardPanel panel;

        ClickListener(BoardPanel panel, JFrame container){
            this.panel = panel;
            this.container = container;
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            int[] newShot;
            newShot = panel.registerClick(e.getX(), e.getY());
            if(newShot[0] != -1 && newShot[1] != -1)
                sectorLocation = newShot;
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

        }
    }

    class WindowListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           // ShipView newView = new ShipView();
        }
    }

}
