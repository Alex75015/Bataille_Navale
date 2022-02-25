package ensta.model.ship;

import ensta.model.Orientation;

public class Carrier extends AbstractShip {

    public Carrier(){
        this(Orientation.EAST);
    }
    
    public Carrier(Orientation orientation){
        super("C","Carrier",5,orientation);
    }
    
}
