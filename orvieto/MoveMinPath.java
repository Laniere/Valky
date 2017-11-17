package gj.quoridor.player.orvieto;

public class MoveMinPath extends Move{
	public MoveMinPath(int i, int j){
		super( i, j );
	}
	
	private int length = 1;
	
	public int getLength() {
		return this.length;
	}
/*
 * Il metodo risale attraverso la cella data dalla BFG al nodo padre per scegliere la direzione
 * dello spostamento da effettuare
 */
	public int[] evalMove( int[] avaiableMove, int id, Board b, int xp, int yp, int x, int y){				
		Cell d = bfgEval(avaiableMove, id, b, xp, yp);
		if ( d.parent != null){
			while ( d.parent.parent != null) {
				d = d.parent;
				this.length = this.length + 1;
			}
		}
		int move[] = new int[2];
		move[0] = 0;
		move[1] = evalParent(d, id);
		b.setParentNull();
		return move;
	}
	
	public int evalParent(Cell d, int id) {					//sceglie la mossa in base all'id confrontando con quello del padre
		if ( id == 1)
			return evalParent1(d);
		else
			return evalParent2(d);
	}
	
	public int evalParent1(Cell d) {
		if ( d.getX() > d.parent.getX())
			return 2;
		else if ( d.getX() < d.parent.getX())
			return 3;
		else if ( d.getY() > d.parent.getY())
			return 0;
		else if ( d.getY() < d.parent.getY())
			return 1;
		return -1;
	}
	
	public int evalParent2(Cell d) {
		if ( d.getX() > d.parent.getX())
			return 3;
		else if ( d.getX() < d.parent.getX())
			return 2;
		else if ( d.getY() > d.parent.getY())
			return 1;
		else if ( d.getY() < d.parent.getY())
			return 0;
		return -1;
	}
	
}
