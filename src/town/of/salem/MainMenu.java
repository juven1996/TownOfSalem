package town.of.salem;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainMenu extends JPanel {

    public enum Type {

        CLIENT, HOST
    }

    Game g;

    public MainMenu(Game g) {
        this.g = g;
        setup();
    }

    private void setup() {
        setLayout(new GridBagLayout());
        components();
    }

    private void components() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        JButton button = new JButton("Host Game");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Lobby lobby = new Lobby(Type.HOST, g);
                lobby.g.players.add(g.player);
                lobby.refreshList();
                g.showMenu(lobby);
            }
        });
        add(button, c);
        button = new JButton("Join Game");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Lobby lobby = new Lobby(Type.CLIENT, g);
                g.showMenu(lobby);
            }
        });
        c.gridy = 1;
        add(button, c);
        button = new JButton("Set Name");
        button.addActionListener((ActionEvent event) -> {
            String name="John Doe";
            String result = JOptionPane.showInputDialog("Enter Username: ");
            if ((result.trim() != null) && (result.trim().length() > 0)) {
                name = result.trim();
            } else {
                do {
                    result = JOptionPane.showInputDialog("Enter Username: ");
                } while ((result.trim() == null) || (result.trim().length() == 0));
            }
            g.player = new Player(name);
        });
        c.gridy = 2;
        add(button, c);
    }
}
