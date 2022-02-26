package ensta;

import java.util.ArrayList;
import java.util.List;

import ensta.ai.BattleShipsAI;
import ensta.controller.Game;
import ensta.model.Board;
import ensta.model.Coords;
import ensta.model.Hit;
import ensta.model.ship.AbstractShip;
import ensta.model.ship.BattleShip;
import ensta.model.ship.Carrier;
import ensta.model.ship.Destroyer;
import ensta.model.ship.Submarine;
import ensta.util.ColorUtil;

public class Main {

    public static void main(String args[]) 
    {
        Game jeu = new Game();
        jeu.init();
        jeu.run();
        System.out.println("Fin de la partie");
    } 


}

