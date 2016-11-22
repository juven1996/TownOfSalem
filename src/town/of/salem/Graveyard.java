package town.of.salem;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JTextArea;

public class Graveyard extends JTextArea {
    
    public Graveyard(GridBagConstraints c) {
        setup(c);
    }
    
    private void setup(GridBagConstraints c) {
        setRows(15);
        setColumns(10);
        setEditable(false);
        append("Graveyard\n");
        setBorder(BorderFactory.createLineBorder(Color.black));
        position(c);
    }
    
    private void position(GridBagConstraints c) {
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.gridx = 0;
        c.gridy = 0;
    }
    
    private void components() {
        
    }

}
