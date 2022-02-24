package ensta.model;

import ensta.model.ship.AbstractShip;

public class Board implements IBoard {

	private static final int DEFAULT_SIZE = 10;
	private int size;
	private Tile[][] grid;
	private String nom;
	
	public Board() {
		this("Navires", 8);
	}

	public Board(String nom, int size) {
		this.nom = nom;
		this.size = size;
		this.grid = new Tile [size][size];
		for(int i = 0; i < this.size; i++){
			for(int j = 0; j < this.size; j++){
				this.grid[i][j] = new Tile();
			}
		}
	}

	public Board(String nom) {
		this.nom = nom;
		this.size = DEFAULT_SIZE;
		this.grid = new Tile [size][size];
		for(int i = 0; i < this.size; i++){
			for(int j = 0; j < this.size; j++){
				this.grid[i][j] = new Tile();
			}
		}
	}

	public void print() {
		System.out.println(this.nom + " :");
		int aMajuscule = 65;
		int longueurDecalageInitial = 2*Integer.toString(size).length();
		System.out.print(" ".repeat(longueurDecalageInitial));
		for(int j=aMajuscule; j<(aMajuscule + size);j++){
			System.out.print((char) j + " ");
		}
		System.out.println("");
		for(int i=0; i<size;i++){
			System.out.print(i+1 + " ".repeat(longueurDecalageInitial-Integer.toString(i+1).length()));
			for(int j=0; j<size;j++){
				System.out.print(grid[i][j].toString() + " ");
			}
			System.out.println();
		}
		System.out.println();
		/* System.out.println("print");
		int i = 97;
		System.out.println((char) i); */
	}

	public boolean canPutShip(AbstractShip ship, Coords coords) {
		Orientation o = ship.getOrientation();
		int dx = 0, dy = 0;
		if (o == Orientation.EAST) {
			if (coords.getX() + ship.getLength() >= this.size) {
				return false;
			}
			dx = 1;
		} else if (o == Orientation.SOUTH) {
			if (coords.getY() + ship.getLength() >= this.size) {
				return false;
			}
			dy = 1;
		} else if (o == Orientation.NORTH) {
			if (coords.getY() + 1 - ship.getLength() < 0) {
				return false;
			}
			dy = -1;
		} else if (o == Orientation.WEST) {
			if (coords.getX() + 1 - ship.getLength() < 0) {
				return false;
			}
			dx = -1;
		}

		Coords iCoords = new Coords(coords);

		for (int i = 0; i < ship.getLength(); ++i) {
			if (this.hasShip(iCoords)) {
				return false;
			}
			iCoords.setX(iCoords.getX() + dx);
			iCoords.setY(iCoords.getY() + dy);
		}

		return true;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean putShip(AbstractShip ship, ensta.model.Coords coords) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasShip(ensta.model.Coords coords) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setHit(boolean hit, ensta.model.Coords coords) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean getHit(ensta.model.Coords coords) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Hit sendHit(ensta.model.Coords res) {
		// TODO Auto-generated method stub
		return null;
	}

}
