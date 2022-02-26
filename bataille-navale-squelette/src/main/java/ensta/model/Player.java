package ensta.model;

import java.util.List;

import ensta.model.ship.AbstractShip;
import ensta.view.InputHelper;

public class Player {
	/*
	 * ** Attributs
	 */
	private Board board;
	protected Board opponentBoard;
	private int destroyedCount;
	protected AbstractShip[] ships;
	private boolean lose;

	/*
	 * ** Constructeur
	 */
	public Player(Board board, Board opponentBoard, List<AbstractShip> ships) {
		this.setBoard(board);
		this.ships = ships.toArray(new AbstractShip[0]);
		this.opponentBoard = opponentBoard;
	}

	/*
	 * ** Méthodes
	 */

	/**
	 * Read keyboard input to get ships coordinates. Place ships on given
	 * coodrinates.
	 */
	public void putShips() {
		boolean done = false;
		int i = 0;
		/* board.print(); */
		System.out.println("--------------------------------------------------");
		System.out.println(this.board.getNom() + " c'est à vous de placer vos bateaux !");
		System.out.println();

		do {
			AbstractShip ship = ships[i];
			String msg = String.format("placer %d : %s(%d)", i + 1, ship.getName(), ship.getLength());
			System.out.println(msg);
			InputHelper.ShipInput res = InputHelper.readShipInput();
			// TODO set ship orientation
			// TODO put ship at given position
			// TODO when ship placement successful
			
			
			boolean donePutShip = false;
			do {

				switch(res.orientation)
				{
					case "east":
						ship.setOrientation(Orientation.EAST);
						break;
					case "south":
						ship.setOrientation(Orientation.SOUTH);
						break;
					case "north":
						ship.setOrientation(Orientation.NORTH);
						break;
					case "west":
						ship.setOrientation(Orientation.WEST);
						break;
				}
				Coords coords = new Coords(res.x+1,res.y);
				if(coords.isInBoard(board.getSize())){
					if(board.canPutShip(ship, coords)){
							board.putShip(ship, coords);
							donePutShip = true;
					}
					else if(board.isOut(ship, coords)){
						System.err.println("Le bateau sera hors de la grille, veuillez saisir une nouvelle position !");
						res = InputHelper.readShipInput();
					}
					else { // (board.hasShip(ship, coords))
							System.err.println("Il y a déjà un bateau à cette position, veuillez en saisir une nouvelle !");
							res = InputHelper.readShipInput();
					}
				}
				else{
					System.err.println("Les coordonnées du bateau doivent être comprises entre A1 et J10, veuillez réessayer !");
					res = InputHelper.readShipInput();
				}
			} while (!donePutShip);
			
			++i;
			done = i == 5;

			board.print();
		} while (!done);
	}
	// public Hit sendHit(Coords coords) { à la base c'était ça
	public Hit sendHit(Coords coords) {
		boolean done = false;
		Hit hit = null;
		System.out.println(this.board.getNom() + " c'est votre tour. Où voulez-vous frapper ?");

		do {

			InputHelper.CoordInput hitInput = InputHelper.readCoordInput();
			// TODO call sendHit on this.opponentBoard
			coords.setX(hitInput.x);
			coords.setY(hitInput.y);

			if(coords.isInBoard(board.getSize())){
				if (board.isStruck(coords) == null) 
					{
						hit = opponentBoard.sendHit(new Coords(coords.getX()+1,coords.getY()+1));
						
						if(hit == Hit.MISS){
							board.setStruck(false, coords);
						}
						else{
							board.setStruck(true, coords);
						}
						done = true;
					}
				else {
					System.err.println("Vous avez déjà frappé à cette position, veuillez en saisir une nouvelle !");
					/* hitInput = InputHelper.readCoordInput(); */
				}
				// TODO : Game expects sendHit to return BOTH hit result & hit coords.
				// return hit is obvious. But how to return coords at the same time ?
			}
			else{
				System.err.println("Les coordonnées de la frappe doivent être comprises entre A1 et J10, veuillez réessayer !");
			}
		} while (!done);
		board.print();
		/* System.out.println(hit.toString()); */
		System.out.println();
		return hit;
	}

	public AbstractShip[] getShips() {
		return ships;
	}

	public void setShips(AbstractShip[] ships) {
		this.ships = ships;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public int getDestroyedCount() {
		return destroyedCount;
	}

	public void setDestroyedCount(int destroyedCount) {
		this.destroyedCount = destroyedCount;
	}

	public boolean isLose() {
		return lose;
	}

	public void setLose(boolean lose) {
		this.lose = lose;
	}
}
