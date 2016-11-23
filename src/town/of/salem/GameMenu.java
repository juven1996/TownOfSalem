package town.of.salem;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class GameMenu extends JPanel {
    
    Game g;
    
    ChatText chatText;
    ChatBox chatBox;
    Graveyard graveyard;
    RoleList roleList;
    PlayerPanel players;
    
    int target = -1;
    
    public GameMenu(Game g) {
        this.g = g;
        setLayout(new GridBagLayout());
        addComponents();
    }
    
    public void addComponents() {
        GridBagConstraints c = new GridBagConstraints();
        JPanel graveroles = new JPanel();
        JPanel chatPanel = new JPanel(new BorderLayout());
        JPanel bottomPanel = new JPanel(new BorderLayout(30, 0));
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.X_AXIS));
        
        c.weightx = c.weighty = 0.1;
        c.gridx = c.gridy = 0;
        
        graveyard = new Graveyard(c);
        graveroles.add(graveyard, c);
        roleList = new RoleList();
        graveroles.add(roleList, c);
        add(graveroles, c);
        
        chatBox = new ChatBox();
        chatPanel.add(chatBox, BorderLayout.CENTER);
        chatText = new ChatText();
        chatPanel.add(chatText, BorderLayout.SOUTH);
        
        bottomPanel.add(chatPanel, BorderLayout.CENTER);
        
        players = new PlayerPanel(g.players, this);
        players.setPreferredSize(new Dimension(200, 100));
        
        box.add(players);
        bottomPanel.add(box, BorderLayout.EAST);
        
        
        c.anchor = GridBagConstraints.LAST_LINE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(bottomPanel, c);
        
        /*JButton show = new JButton("Show");
        show.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                players.showButtons();
            }
        });
        add(show);
        show = new JButton("Hide");
        show.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                players.hideButtons();
            }
        });
        add(show);*/
    }   
}
