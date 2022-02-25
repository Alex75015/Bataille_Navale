package ensta;

import java.util.ArrayList;
import java.util.List;

import ensta.controller.Game;
import ensta.model.Board;
import ensta.model.Coords;
import ensta.model.Orientation;
import ensta.model.Player;
import ensta.model.ship.AbstractShip;
import ensta.model.ship.BattleShip;
import ensta.model.ship.Carrier;
import ensta.model.ship.Destroyer;
import ensta.model.ship.Submarine;

public class Main {

	public static void main(String args[]) 
  {
    Board board1 = new Board("Alexis");
    Board board2 = new Board("Marcelo");
    /* board1.print();
    BattleShip battleShip = new BattleShip(Orientation.EAST);
    Coords coords = new Coords(4,4);
    board1.putShip(battleShip, coords);
    
    board1.putShip(new Carrier(Orientation.NORTH), new Coords(5,5));
    board1.print();
    board1.putShip(new Destroyer(Orientation.NORTH), new Coords(7,7));
    board1.print();
    board1.putShip(new Destroyer(Orientation.EAST), new Coords(10,10));
    board1.print(); */
    List<AbstractShip> shipsAlexis = new ArrayList<AbstractShip>();
    shipsAlexis.add(new Destroyer());
    /* shipsAlexis.add(new Submarine());
    shipsAlexis.add(new Submarine());
    shipsAlexis.add(new BattleShip());
    shipsAlexis.add(new Carrier()); */
    List<AbstractShip> shipsMarcelo = new ArrayList<AbstractShip>();
    shipsMarcelo.add(new Destroyer());
    /* shipsMarcelo.add(new Submarine());
    shipsMarcelo.add(new Submarine());
    shipsMarcelo.add(new BattleShip());
    shipsMarcelo.add(new Carrier()); */
    Player Alexis = new Player(board1, board2, shipsAlexis);
    Player Marcelo = new Player(board2,board1,shipsMarcelo);
    Alexis.putShips();
    Marcelo.putShips();
    Alexis.sendHit();
    Marcelo.sendHit();
    Alexis.sendHit();
    Marcelo.sendHit();

  }

}
