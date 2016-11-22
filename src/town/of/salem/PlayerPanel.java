package town.of.salem;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class PlayerPanel extends JPanel {
    
    ArrayList<Player> players;
    ArrayList<JToggleButton> buttons;
    GameMenu gm;
    
    public PlayerPanel(ArrayList<Player> players, GameMenu gm) {
        this.players = players;
        this.gm = gm;
        buttons = new ArrayList<>();
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        setup();
    }
    
    private void setup() {
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = c.weighty = 0.1;
        c.gridx = c.gridy = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        for(int x=0;x<6;x++)
        for(Player p: players) {
            JToggleButton button = new JToggleButton();
            buttons.add(button);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    for(JToggleButton button : buttons)
                        button.setSelected(false);
                    JToggleButton button = (JToggleButton)e.getSource();
                    button.setSelected(true);
                    gm.chatBox.append("You have decided to target someone tonight.\n");
                }
            });
            c.insets.left = 20;
            add(new JLabel(p.name), c);
            
            c.gridx++;
            add(button, c);
            c.gridx = 0;
            c.insets.top += 25;
        }
    }
}
