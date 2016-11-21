package town.of.salem;

import java.awt.GridBagConstraints;
import javax.swing.JTextArea;

public class RoleList extends JTextArea {

    public RoleList(GridBagConstraints c) {
        setup(c);
        components();
    }
    
    private void setup(GridBagConstraints c) {
        setRows(15);
        setColumns(10);
        position(c);
        append("Roles\n");
        setEditable(false);
    }
    
    private void position(GridBagConstraints c) {
        c.anchor = GridBagConstraints.FIRST_LINE_END;
    }
    
    private void components() {
    }
}
