package gj.quoridor.player.orvieto;

interface cellInter{
	void setDirection(int [] direction);
	void setDirectionNull(int i);
}

public class Cell implements cellInter{
	public Cell() {									//costruttore per creare la cella con i 4 valori di default
		for ( int i = 0; i < 4; i++) {
			direction[i] = 1;
		}
		direction[4] = 0;
	}
	
	private int x;
	private int y;
	private int [] direction = new int [5];			//direzioni 1 up 2 back 3 left 4 right
	public Cell parent;
	
	public void setParent( Cell c) {
		this.parent = c;
	}
	
	public int[] getDirection() {
		return this.direction;
	}
	
	public int getDirection(int i) {
		return this.direction[i];
	}
	
	public void setX(int i) {
		this.x=i;
	}
	
	public void setY(int i) {
		this.y=i;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setDirection(int[] direction) {
		this.direction = direction;
	}
	
	public void setDirectionNull(int i) {
		this.direction[i] = 0;
	}
	
	public void setDirectionOpen(int i) {
		this.direction[i] = 1;
	}
}
