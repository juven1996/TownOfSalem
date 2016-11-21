package town.of.salem;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

public class ChatText extends JTextField {
    
    public ChatText(GridBagConstraints c) {
        setup(c);
    }
    
    private void setup(GridBagConstraints c) {
        position(c);
    }
    
    private void position(GridBagConstraints c) {
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(0, 0, 0, 0);
    }
    
    private void components() {
        
    }

}
