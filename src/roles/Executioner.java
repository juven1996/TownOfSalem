package roles;

import town.of.salem.Player;

public class Executioner extends Player {

    public Executioner(String name) {
        super(name);
    }
    
    public void role() {
        System.out.println("Executioner");
    }
}
