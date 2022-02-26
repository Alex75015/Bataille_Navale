package ensta.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import ensta.ai.PlayerAI;
import ensta.model.Board;
import ensta.model.Coords;
import ensta.model.Hit;
import ensta.model.Player;
import ensta.model.ship.AbstractShip;
import ensta.model.ship.BattleShip;
import ensta.model.ship.Carrier;
import ensta.model.ship.Destroyer;
import ensta.model.ship.Submarine;
import ensta.util.ColorUtil;

public class Game {

	/*
	 * *** Constante
	 */
	public static final File SAVE_FILE = new File("savegame.dat");

	/*
	 * *** Attributs
	 */
	private Player player1;
	private Player player2;
	private Scanner sin;
	private boolean viewAiBoard;

	/*
	 * *** Constructeurs
	 */
	public Game() {
	}

	public Game init() {
		if (!loadSave()) {
			System.out.println("Quel est votre nom : ");
            sin = new Scanner(System.in);
			String board_name = sin.nextLine();

			System.out.println("Quelle taille de tableau souhaitez-vous : ");
            int size = sin.nextInt();

			Scanner sin2 = new Scanner(System.in);
			System.out.println("Désirez-vous voir le jeu de votre adversaire? (O/N) : ");
            String view = sin2.nextLine();

			if(view.equals("O"))
				viewAiBoard = true;
			else viewAiBoard = false;

            Board p1_board = new Board(board_name, size);
            Board p2_board = new Board("AI", size);

            List<AbstractShip> p1_ships = createDefaultShips();
            List<AbstractShip> p2_ships = createDefaultShips();

            this.player1 = new Player(p1_board, p2_board, p1_ships);
            this.player2 = new PlayerAI(p2_board, p1_board, p2_ships);

            p1_board.print();

            player1.putShips();
            player2.putShips();
			if(viewAiBoard) p2_board.print();
		}
		return this;
	}

	/*
	 * *** Méthodes
	 */
	public void run() {
		Coords coords = new Coords();
		Board b1 = player1.getBoard();
		Board b2 = player2.getBoard();
		Hit hit;

		// main loop
		boolean done;
		do {
			hit = player1.sendHit(coords); // player1 send a hit
			boolean strike = hit != Hit.MISS; // TODO set this hit on his board (b1)

			done = updateScore();
			System.out.println(makeHitMessage(false /* outgoing hit */, coords, hit));
			System.out.println();
			//b1.print();

			// save();

			if (!done && !strike) {
				do {
					hit = player2.sendHit(coords); // player2 send a hit.

					strike = hit != Hit.MISS;
					if (strike) {
						b1.print();
					}
					System.out.println(makeHitMessage(true /* incoming hit */, coords, hit));
					System.out.println();
					done = updateScore();
					if(viewAiBoard) b2.print();

					if (!done) {
//						save();
					}
				} while (strike && !done);
			}

		} while (!done);

		SAVE_FILE.delete();
		System.out.println(String.format("Le joueur %d gagne", player1.isLose() ? 2 : 1));
		System.out.println();
		sin.close();
	}

	private void save() {
//		try {
//			// TODO bonus 2 : uncomment
//			// if (!SAVE_FILE.exists()) {
//			// SAVE_FILE.getAbsoluteFile().getParentFile().mkdirs();
//			// }
//
//			// TODO bonus 2 : serialize players
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	private boolean loadSave() {
//		if (SAVE_FILE.exists()) {
//			try {
//				// TODO bonus 2 : deserialize players
//
//				return true;
//			} catch (IOException | ClassNotFoundException e) {
//				e.printStackTrace();
//			}
//		}
		return false;
	}

	private boolean updateScore() {
		for (Player player : new Player[] { player1, player2 }) {
			int destroyed = 0;
			for (AbstractShip ship : player.getShips()) {
				if (ship.isSunk()) {
					destroyed++;
				}
			}

			player.setDestroyedCount(destroyed);
			player.setLose(destroyed == player.getShips().length);
			if (player.isLose()) {
				return true;
			}
		}
		return false;
	}

	private String makeHitMessage(boolean incoming, Coords coords, Hit hit) {
		String msg;
		ColorUtil.Color color = ColorUtil.Color.RESET;
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
		msg = String.format("%s Frappe en %c%d : %s", incoming ? "<=" : "=>", ((char) ('A' + coords.getX())),
				(coords.getY() + 1), msg);
		return ColorUtil.colorize(msg, color);
	}

	private static List<AbstractShip> createDefaultShips() {
		return Arrays.asList(new AbstractShip[] { new Destroyer(), new Submarine(), new Submarine(), new BattleShip(),
				new Carrier() });
	}
}
