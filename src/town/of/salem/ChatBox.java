package town.of.salem;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextArea;

public class ChatBox extends JTextArea {
    
    public ChatBox() {
        setup();
    }
    
    private void setup() {
        setRows(20);
        setColumns(30);
        setEditable(false);
    }
    
    private void position(GridBagConstraints c) {
        c.anchor = GridBagConstraints.PAGE_END;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0,0,50,0);  //top padding
    }
    
    private void components() {
        
    }
}
