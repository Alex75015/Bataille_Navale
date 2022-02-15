package ensta.model.ship;

import ensta.model.Orientation;

public abstract class AbstractShip {
    
    private char label;
    private String nom;
    private Orientation orientation;
    private int length;

    public AbstractShip(char label, String nom, int length, Orientation orientation){
        this.label = label;
        this.nom = nom;
        this.length = length;
        this.orientation = orientation;
    }


    public char getLabel() {
        return label;
    }


    public void setLabel(char label) {
        this.label = label;
    }


    public String getNom() {
        return nom;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }


    public Orientation getOrientation(){
        return orientation;
    }

    public int getLength(){
        return length;
    }

    public boolean isSunk() {
        return false;
    }

    public Object getName() {
        return null;
    }
    
}
