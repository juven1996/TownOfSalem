package town.of.salem;

import java.awt.Color;
import java.awt.GridBagConstraints;
import javax.swing.BorderFactory;
import javax.swing.JTextArea;

public class RoleList extends JTextArea {

    public RoleList() {
        setup();
        components();
    }
    
    private void setup() {
        setRows(15);
        setColumns(10);
        append("Roles\n");
        setEditable(false);
        setBorder(BorderFactory.createLineBorder(Color.black));
    }
    
    private void position(GridBagConstraints c) {
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.gridx = 1;
        c.gridy = 0;
    }
    
    private void components() {
        
    }
}
