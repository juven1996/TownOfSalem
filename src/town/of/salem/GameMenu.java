package town.of.salem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class GameMenu extends JPanel {
    
    Game g;
    
    ChatText chatText;
    ChatBox chatBox;
    Graveyard graveyard;
    RoleList roleList;
    PlayerPanel players;
    
    public GameMenu(Game g) {
        this.g = g;
        setLayout(new GridBagLayout());
        addComponents();
    }
    
    public void addComponents() {
        GridBagConstraints c = new GridBagConstraints();
        JPanel graveroles = new JPanel();
        JPanel chatPanel = new JPanel(new BorderLayout());
        c.weightx = c.weighty = 0.1;
        graveyard = new Graveyard(c);
        graveroles.add(graveyard, c);
        roleList = new RoleList();
        graveroles.add(roleList, c);
        add(graveroles, c);
        chatBox = new ChatBox();
        chatPanel.add(chatBox, BorderLayout.CENTER);
        chatText = new ChatText();
        chatPanel.add(chatText, BorderLayout.SOUTH);
        
        c.anchor = GridBagConstraints.LAST_LINE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        
        add(chatPanel, c);
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.fill = GridBagConstraints.NONE;
        c.gridx = 1;
        c.ipady = (15-6)*16;
        c.ipadx = 50;
        players = new PlayerPanel(g.players, this);
        add(players, c);
        //add(chatBox, c);

        //roleList = new RoleList(c);
        //add(roleList, c);
        /*text = new JLabel("Day 1");
        timeLabel = new JLabel("Time: 10");
        playersPanel = new JPanel(new GridBagLayout());
        playersPanel.setBackground(Color.WHITE);
        playersPanel.setPreferredSize(new Dimension(200, 220));
        chatText.addActionListener((ActionEvent) -> {
            if (lobby.server != null) {
                chat.append(Window.player.name + ": " + chatText.getText() + "\n");
                try {
                    lobby.server.sendAll(Window.player.name + ": " + chatText.getText());
                } catch (Exception e) {
                }
            } else {
                try {
                    lobby.client.send(Window.player.name + ": " + chatText.getText());
                } catch (Exception e) {
                }
            }
            chatText.setText("");
        });*/
        /*c = new GridBagConstraints();
        add(graveyard, c);

        c.insets = new Insets(0, 115, 0, 0);  //top padding
        add(roles, c);

        c.insets = new Insets(0, 300, 0, 0);  //top padding
        add(text, c);

        c.anchor = GridBagConstraints.LAST_LINE_START;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0, 0, 30, 0);  //top padding
        add(chat, c);

        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0, 0, 0, 0);
        add(chatText, c);
        
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        c.gridx = 0;
        c.gridy = 0;
        add(timeLabel, c);

        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.gridx = 0;
        c.gridy = 1;
        c.ipady = 90;
        add(playersPanel, c);*/
    }   
}
