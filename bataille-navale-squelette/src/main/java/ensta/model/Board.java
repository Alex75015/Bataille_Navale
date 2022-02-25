package ensta.model;

import ensta.model.ship.AbstractShip;

public class Board implements IBoard {

	private static final int DEFAULT_SIZE = 10;
	private int size;
	private Tile[][] gridShips;
	private Tile[][] gridHits;
	private String nom;
	
	public Board() {
		this("Navires", 8);
	}

	public Board(String nom, int size) {
		this.nom = nom;
		this.size = size;
		this.gridShips = new Tile [size][size];
		this.gridHits = new Tile [size][size];
		for(int i = 0; i < this.size; i++){
			for(int j = 0; j < this.size; j++){
				this.gridShips[i][j] = new Tile();
				this.gridHits[i][j] = new Tile();
			}
		}
	}

	public Board(String nom) {
		this.nom = nom;
		this.size = DEFAULT_SIZE;
		this.gridShips = new Tile [size][size];
		this.gridHits = new Tile [size][size];
		for(int i = 0; i < this.size; i++){
			for(int j = 0; j < this.size; j++){
				this.gridShips[i][j] = new Tile();
				this.gridHits[i][j] = new Tile();
			}
		}
	}

	public void print() {
		int longueurDecaInitial = 2*Integer.toString(size).length();
		String longueurDecalageInitial = " ".repeat(longueurDecaInitial);
		String ecartEntreDeuxPositionsBoards = " ".repeat(longueurDecaInitial+size+4);
		System.out.println();
		System.out.println("Board de "+this.nom+" :");
		System.out.println();
		System.out.println("Navires :" + ecartEntreDeuxPositionsBoards +"Frappes :");
		int aMajuscule = 65;
		
		System.out.print(longueurDecalageInitial);
		for(int j=aMajuscule; j<(aMajuscule + size);j++){
			System.out.print((char) j + " ");
		}
		System.out.print(" ".repeat(3));
		System.out.print(longueurDecalageInitial);
		for(int j=aMajuscule; j<(aMajuscule + size);j++){
			System.out.print((char) j + " ");
		}
		System.out.println("");
		for(int i=0; i<size;i++){
			System.out.print(i+1 + " ".repeat(longueurDecaInitial-Integer.toString(i+1).length()));
			for(int j=0; j<size;j++){
				System.out.print(gridShips[i][j].toString() + " ");
			}
			System.out.print(" ".repeat(3));
			System.out.print(i+1 + " ".repeat(longueurDecaInitial-Integer.toString(i+1).length()));
			for(int j=0; j<size;j++){
				System.out.print(gridHits[i][j].toString() + " ");
			}
			System.out.println();
		}
		System.out.println();
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
		return this.size;
	}

	@Override
	public boolean putShip(AbstractShip ship, ensta.model.Coords coords) {
		Orientation o = ship.getOrientation();
		int dx = 0, dy = 0;
		if(canPutShip(ship, coords))
		{
			switch(o)
			{
				case EAST:
					dx = 1;
					break;
				case SOUTH:
					dy = 1;
					break;
				case NORTH:
					dy = -1;
					break;
				case WEST:
					dx = -1;
					break;
			}
		
			for (int i = 0; i < ship.getLength(); ++i) 
			{
				int x = coords.getX();
				int y = coords.getY();
				gridShips[y+dy*i][x+dx*i].putShip(ship);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean hasShip(ensta.model.Coords coords) {
		int x = coords.getX();
		int y = coords.getY();
		return hasShip(x,y);
	}

	public boolean hasShip(int x, int y) {
		if(gridShips[y][x].hasShip()){
			return true;
		}
		return false;
	}

	@Override
	public void setHit(ensta.model.Coords coords) {
		int x = coords.getX();
		int y = coords.getY();
		setHit(x, y);
		
	}

	public void setHit(int x, int y) {
		gridShips[y][x].addStrike();
	}

	@Override
	public Boolean getHit(ensta.model.Coords coords) {
		return this.gridHits[coords.getY()][coords.getX()].isStruck();
	}

	@Override
	public Hit sendHit(ensta.model.Coords res) {
		int x = res.getX();
		int y = res.getY();
		return sendHit(x,y);
	}

	@Override
	public Hit sendHit(int x, int y) {
		setHit(x, y);
		if(!hasShip(x, y)){
			/* gridHits[y][x].setStruck(false); */
			return Hit.MISS;
		}
		else if(gridShips[y][x].isSunk()){
			/* gridHits[y][x].setStruck(true); */
			return Hit.fromInt(gridShips[y][x].getStrikeCount());
		}
		else{
/* 			gridHits[y][x].setStruck(true); */
			return Hit.STRIKE;
		}
	}

	@Override
	public void setHit(boolean hit, Coords coords) {
		// TODO Auto-generated method stub
		this.gridHits[coords.getY()][coords.getX()].setStruck(hit);
		
	}

	public Boolean isStruck(Coords coords){
		int x = coords.getX();
		int y = coords.getY();
		return isStruck(x, y);
	}

	public Boolean isStruck(int x, int y){
		return gridHits[y][x].isStruck();
	}

	public String getNom(){
		return this.nom;
	}

	public void setStruck(boolean struck, int x, int y){
		this.gridHits[y][x].setStruck(struck);
	}

	public Boolean isSunk(Coords coords){
		return this.gridShips[coords.getY()][coords.getX()].isSunk();
	}

}
