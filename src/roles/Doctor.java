package roles;

import town.of.salem.Player;

public class Doctor extends Player {

    public Doctor(String name) {
        super(name);
    }
    
    public void role() {
        System.out.println("Doctor");
    }
}
