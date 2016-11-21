package roles;

import java.util.Random;
import town.of.salem.Player;

public class RoleBuilder {
    
    public Player returnRole(String name, Roles role) {
        switch(role) {
            case DOCTOR:
                return new Doctor(name);
            case ESCORT:
                return new Escort(name);
            case EXECUTIONER:
                return new Executioner(name);
            case FRAMER:
                return new Framer(name);
            case GODFATHER:
                return new Godfather(name);
            case INVESTIGATOR:
                return new Investigator(name);
            case JESTER:
                return new Jester(name);
            case JAILOR:
                return new Jailor(name);
            case LOOKOUT:
                return new Lookout(name);
            case MAFIOSO:
                return new Mafioso(name);
            case MEDIUM:
                return new Medium(name);
            case SERIALKILLER:
                return new SerialKiller(name);
            case SHERIFF:
                return new Sheriff(name);
            case VETERAN:
                return new Veteran(name);
            case VIGILANTE:
                return new Vigilante(name);
            case TOWNKILLING:
                return (new Random().nextInt(1) == 0) ? new Vigilante(name) : new Veteran(name);
        }
        return null;
    }
}
