package town.of.salem;

import java.io.Serializable;

public class Player implements Serializable {
    
    String name;

    public Player(String name) {
        this.name = name;
    }
    
    public void role() {
        System.out.println("Player");
    }
}
