package gj.quoridor.player.orvieto;

public class Wall {
	public Wall(int j) {
		this.j = j;
		setDirectionW(dirWall(j));
		getCoord();
	}
	private int j;
	private int x;
	private int y;
	private int direction;
	
	public void getCoord() {									//metodo per determinare la cella da modificare
		if (getDirectionW() == 1) {
			x=( j % 8);	
			y=( j / 16);
		}
		else {
			x=( j % 8);	
			y=( j / 16);
		}
	}
	
	public int dirWall(int j) {									//metodo per determinare la direzione del muro (V/H)
		for(int i = 0; i < 8; i++) {
			for(int j1 = 0; j1 < 8; j1++) {
				int v1 = 0;
				v1 = 2*i*8+j1;
				if (v1 == j)
					return 1;										 //direzione muro verticale
			}
		} return 0; 												//direzione muro orizzontale
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getDirectionW() {
		return direction;
	}

	public void setDirectionW(int direction) {
		this.direction = direction;
	}

	public int getJ() {
		return j;
	}
}
