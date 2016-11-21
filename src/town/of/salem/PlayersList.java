package town.of.salem;

import java.awt.GridBagConstraints;
import javax.swing.JTextArea;

public class PlayersList extends JTextArea {

    public PlayersList(GridBagConstraints c) {
        setup(c);
        components();
    }
    
    private void setup(GridBagConstraints c) {
        setRows(15);
        setColumns(10);
        position(c);
        append("Players\n");
        setEditable(false);
    }
    
    private void position(GridBagConstraints c) {
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.gridx = 0;
        c.gridy = 0;
    }
    
    private void components() {
    }
}
