package town.of.salem;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Lobby extends JPanel {
    
    PlayersList playerslist;
    ChatBox chatbox;
    ChatText chatText;
    RoleList rolelist;
    ArrayList<Player> players;
    JButton start;
    
    Server server;
    Client client;
    
    public Lobby(MainMenu.Type type) {
        players = new ArrayList<>();
        setLayout(new GridBagLayout());
        addComponents();
        if(type == MainMenu.Type.HOST)
            server = new Server(this);
        else
            client = new Client(this);

    }
    
    public void addComponents() {
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = c.weighty = 1;
        c.gridheight = 5;
        c.gridwidth = 10;
        playerslist = new PlayersList(c);
        add(playerslist, c);
        rolelist = new RoleList(c);
        add(rolelist, c);
        chatbox = new ChatBox(c);
        add(chatbox, c);
        chatText = new ChatText(c);
        chatText.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               String text = chatText.getText();
               chatbox.append(Game.player.name + ": " + text + "\n");
               if(server != null) {
                   server.sendAll(Game.player.name + ": " + text + "\n");
               }
               else {
                   client.send(Game.player.name + ": " + text + "\n");
               }
               chatText.setText("");
           } 
        });
        add(chatText, c);
        start = new JButton("Test");
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(server != null)
                    server.startGame();
                else
                    Game.player.role();
            }
        });
        add(start);
        
        /*playersList = new JTextArea(15, 10);
        JTextArea roles = new JTextArea(15, 10);
        chat = new JTextArea(10, 30);
        start = new JButton("Start");
        JTextField chatText = new JTextField();
        
        c.weighty = 1;
        c.weightx = 1;
        c.gridheight = 5;
        c.gridwidth = 10;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.gridx = 0;
        c.gridy = 0;
        add(playersList, c);
        
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        add(roles , c);
        
        c.anchor = GridBagConstraints.PAGE_END;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0,0,50,0);  //top padding
        add(chat, c);
        
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(0, 0, 0, 0);
        add(chatText, c);

        playersList.setEditable(false);
        roles.setEditable(false);
        chat.setEditable(false);
        roles.append("Roles\n");*/
    }
    
    protected void refreshList() {
        playerslist.setText("Players\n");
        for(Player p: players) {
            playerslist.append(p.name + "\n");
        }
    }
}
