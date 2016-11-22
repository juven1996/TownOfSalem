package town.of.salem;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JFrame {
    
    static Player player = new Player("A");
    ArrayList<Player> players;
    
    Server server;
    Client client;
    
    protected Game() {
        setup();
    }
    
    private void setup() {
        setSize(1000, 1000);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new MainMenu(this));
        setVisible(true);
    }
    
    protected void showMenu(JPanel panel) {
        this.getContentPane().removeAll();
        this.setContentPane(panel);
        panel.revalidate();
        panel.repaint();
    }
}
