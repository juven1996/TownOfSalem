package town.of.salem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Lobby extends JPanel {
    
    PlayersList playerslist;
    ChatBox chatbox;
    ChatText chatText;
    RoleList rolelist;
    
    JButton start;
    
    Game g;
    
    public Lobby(MainMenu.Type type, Game g) {
        this.g = g;
        g.players = new ArrayList<>();
        setLayout(new GridBagLayout());
        addComponents();
        if(type == MainMenu.Type.HOST)
            g.server = new Server(this);
        else
            g.client = new Client(this);

    }
    
    public void addComponents() {
        GridBagConstraints c = new GridBagConstraints();
        JPanel chatPanel = new JPanel(new BorderLayout());
        JPanel playersroles = new JPanel();
        chatPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        playersroles.setBorder(BorderFactory.createLineBorder(Color.black));
        c.weightx = c.weighty = 0.1;
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        
        playerslist = new PlayersList();
        playersroles.add(playerslist);
        rolelist = new RoleList();
        playersroles.add(rolelist);
        add(playersroles, c);
        
        c.anchor = GridBagConstraints.LAST_LINE_START;
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridy = 1;
        
        chatbox = new ChatBox();
        chatPanel.add(chatbox, BorderLayout.CENTER);
        chatText = new ChatText();
        chatPanel.add(chatText, BorderLayout.SOUTH);
        add(chatPanel, c);
        
        chatText.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               String text = chatText.getText();
               chatbox.append(Game.player.name + ": " + text + "\n");
               if(g.server != null) {
                   g.server.sendAll(Game.player.name + ": " + text + "\n");
               }
               else {
                   g.client.send(Game.player.name + ": " + text + "\n");
               }
               chatText.setText("");
           } 
        });
        start = new JButton("Test");
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(g.server != null)
                    g.showMenu(new GameMenu(g));
                    //server.startGame();
                else
                    Game.player.role();
            }
        });
        add(start);
    }
    
    protected void refreshList() {
        playerslist.setText("Players\n");
        for(Player p: g.players) {
            playerslist.append(p.name + "\n");
        }
    }
}
