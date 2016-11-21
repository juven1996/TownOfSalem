package roles;

import town.of.salem.Player;

public class Escort extends Player {

    public Escort(String name) {
        super(name);
    }

    public void role() {
        System.out.println("Escort");
    }
}
