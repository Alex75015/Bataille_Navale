package ensta.model.ship;

import ensta.model.Orientation;

public abstract class AbstractShip {
    
    private String label;
    private String nom;
    private Orientation orientation;
    private int length;
    private int strikeCount;

    public AbstractShip(String label, String nom, int length, Orientation orientation){
        this.label = label;
        this.nom = nom;
        this.length = length;
        this.orientation = orientation;
    }


    public String getLabel() {
        return label;
    }


    public void setLabel(String label) {
        this.label = label;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }


    public Orientation getOrientation(){
        return orientation;
    }

    public int getLength(){
        return length;
    }

    public String getName() {
        return this.nom;
    }

    public void addStrike(){
        this.strikeCount++;
    }

    public boolean isSunk(){
        if(this.strikeCount == this.length){
            return true;
        }
        return false;
    }
    
}
