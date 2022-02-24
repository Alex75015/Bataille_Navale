package ensta.model;

import ensta.model.ship.AbstractShip;
import ensta.util.ColorUtil;

public class Tile {
    private Boolean struck;
    private AbstractShip ship;
    private Coords coords;

    public Tile(){
        this.struck = null;
        this.ship = null;
        this.coords = new Coords();
    }

    public Tile(Boolean struck){
        this.struck = null;
        this.ship = null;
        this.coords = new Coords();
    }

    public String toString(){ 
        if(this.struck == Boolean.FALSE)
		    return "x";
        if(this.struck == Boolean.TRUE)
		    return ColorUtil.colorize("x", ColorUtil.Color.RED);
        if(this.struck == null && this.ship==null)
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

    public void addStrike(){
        struck = true;
    }

    public Boolean isStruck(){
        return struck;
    }


}
