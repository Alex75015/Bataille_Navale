package ensta;

import ensta.controller.Game;
import ensta.model.Board;

public class Main {

	public static void main(String args[]) {
        Board board1 = new Board("Navires");
		Board board2 = new Board("Frappes");
		board1.print();
        board2.print();
    }

}
