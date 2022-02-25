package ensta.model.ship;

import ensta.model.Orientation;

public class BattleShip extends AbstractShip{

    public BattleShip(){
        this(Orientation.EAST);
    }
    
    public BattleShip(Orientation orientation){
        super("B","BattleShip",4,orientation);
    }
    
}
