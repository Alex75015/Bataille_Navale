package ensta;

import java.util.ArrayList;
import java.util.List;

import ensta.ai.BattleShipsAI;
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
    /* Board board1 = new Board("Alexis");
    Board board2 = new Board("IA");

    List<AbstractShip> ships = new ArrayList<AbstractShip>();
    ships.add(new Destroyer()); */
    /* ships.add(new Submarine());
    ships.add(new Submarine());
    ships.add(new BattleShip());
    ships.add(new Carrier()); */

    /* AbstractShip[] shipsAI = new AbstractShip[1];
    shipsAI[0] = new Destroyer();

    Player Alexis = new Player(board1, board2, ships);
    BattleShipsAI AI = new BattleShipsAI(board2, board1);
    Alexis.putShips();
    AI.putShips(shipsAI);
    Alexis.sendHit();
    AI.sendHit();
    Alexis.sendHit();
    AI.sendHit(); */

    /* Board b1 = new Board("Board 1");
    Board b2 = new Board("Board 2");

    BattleShipsAI ai1 = new BattleShipsAI(b1, b2);
    BattleShipsAI ai2 = new BattleShipsAI(b2, b1);

    AbstractShip[] shipList = new AbstractShip[] { new Destroyer(), new Submarine(),
            new Submarine(), new BattleShip(), new Carrier() };
    ai1.putShips(shipList);
    ai2.putShips(shipList);

    int shipCount1 = shipList.length ,shipCount2 = shipList.length;
    int size = b1.getSize();

    while (shipCount1 == shipList.length && shipCount2 == shipList.length) {
        Hit h;
        
        Coords c = Coords.randomCoords(size);
        do {
            h = ai1.sendHit(c);

            if (h != Hit.MISS && h != Hit.STRIKE)
                shipCount2--;

        } while(h != Hit.MISS);
        b1.print();

        if (shipCount2 == 0)
            break;

        do {
            h = ai2.sendHit(c);

            if (h != Hit.MISS && h != Hit.STRIKE)
                shipCount1--;


        } while(h != Hit.MISS);
        b2.print();
    }

    if (shipCount2 == 0)
        System.out.println("Joeur 1 a gagné");
    else
        System.out.println("Joeur 2 a gagné");*/
        Board board = new Board("AI");
        board.print();

        List<AbstractShip> ships = new ArrayList<AbstractShip>();
        ships.add(new Destroyer());
        ships.add(new Submarine());
        ships.add(new Submarine());
        ships.add(new BattleShip());
        ships.add(new Carrier());

        BattleShipsAI AI = new BattleShipsAI(board, board);

        AI.putShips(ships.toArray(new AbstractShip[ships.size()]));

        int destroyedShips = 0;

        Coords coords = new Coords();

        Hit hit;

        do {

            hit = AI.sendHit(coords);
            //System.out.println("Cords"+ coords.getX()+""+coords.getY());

            if( board.hasShip(coords) && board.isSunk(coords)==Boolean.TRUE)
                destroyedShips++;

            String msg;
            ColorUtil.Color color = ColorUtil.Color.RESET;
            if(hit == null){
                msg = "There is a hit here!";
            }else{
                switch (hit) {
                case MISS:
                    msg = hit.toString();
                    break;
                case STRIKE:
                    msg = hit.toString();
                    color = ColorUtil.Color.RED;
                    break;
                default:
                    msg = hit.toString() + " coulé";
                    color = ColorUtil.Color.RED;
                }
                msg = String.format("Frappe en %c%d : %s : ", ((char) (64 + coords.getX()+1)),
                        (coords.getY()+1), msg);
            }
            System.out.println(ColorUtil.colorize(msg, color));


            board.print();

            sleep(400);

        }while (destroyedShips != ships.size());

        //board.print();
    }


    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    

    } 



}

