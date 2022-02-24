package ensta.model.ship;

import ensta.model.Orientation;

public class Destroyer extends AbstractShip {

    public Destroyer(){
        this(Orientation.EAST);
    }
    
    public Destroyer(Orientation orientation){
        super("D","Destroyer",3,orientation);
    }
    
}
