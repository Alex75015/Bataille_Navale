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

public class TestGame {
    public static void main(String args[]) 
    {
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
