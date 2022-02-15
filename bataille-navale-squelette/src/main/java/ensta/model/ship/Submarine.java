package ensta.model.ship;

import ensta.model.Orientation;

public class Submarine extends AbstractShip{

    public Submarine(){
        this(Orientation.EAST);
    }
    
    public Submarine(Orientation orientation){
        super('S',"Submarine",2,orientation);
    }
    
}
