package ensta.model;

import ensta.model.ship.AbstractShip;

public class Tile {
    private boolean isHit;
    private AbstractShip ship;
    private Coords coords;

    public Tile(){
        this.isHit = false;
        this.ship = null;
        this.coords = new Coords();
    }

    public Tile(boolean isHit){
        this.isHit = false;
        this.ship = null;
        this.coords = new Coords();
    }

    public String toString(){ 
        if(this.isHit)
		    return "x";
        if(!this.isHit && this.ship==null)
            return ".";
        if(this.ship != null)
            return ship.getLabel();
        return ".";
        
	} 

    public void putShip(AbstractShip ship)
    {
        this.ship = ship;
    }

    public boolean hasShip()
    {
        if(this.ship != null){
            return true;
        }
        return false;
    }


}
