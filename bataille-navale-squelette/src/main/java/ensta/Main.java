package ensta;

import ensta.controller.Game;
import ensta.model.Board;
import ensta.model.Coords;
import ensta.model.Orientation;
import ensta.model.ship.BattleShip;
import ensta.model.ship.Carrier;
import ensta.model.ship.Destroyer;

public class Main {

	public static void main(String args[]) 
  {
    Board board1 = new Board("Alexis");
    board1.print();
    BattleShip battleShip = new BattleShip(Orientation.EAST);
    Coords coords = new Coords(4,4);
    board1.putShip(battleShip, coords);
    
    board1.putShip(new Carrier(Orientation.NORTH), new Coords(5,5));
    board1.print();
    board1.putShip(new Destroyer(Orientation.NORTH), new Coords(7,7));
    board1.print();
    board1.putShip(new Destroyer(Orientation.EAST), new Coords(10,10));
    board1.print();

  }

}
