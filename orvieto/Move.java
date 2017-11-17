package gj.quoridor.player.orvieto;

import java.util.LinkedList;
import java.util.Queue;


public abstract class Move {
	public Move(int i, int y) {
		this.myMove[0] = i;
		this.myMove[1] = y;
	}

	private int myMove[] = new int[2];
	
	public abstract int[] evalMove( int[] avaiableMove, int id, Board b, int xa, int ya, int xp, int yp);
	
	/*
	 * Visita in ampiezza per determinare il percorso più
	 * breve verso la vittoria e utilizzato anche per verificare se esiste
	 * un path che porti alla vittoria nel caso venisse deciso di inserire un muro
	 */
	
	public Cell bfgEval( int[] avaiableMove, int id, Board b, int x, int y ) {
		boolean finish = false;
		Queue<Cell> queue = new LinkedList<Cell>();
		LinkedList<Cell> visited = new LinkedList<Cell>();
		LinkedList<Cell> adj = new LinkedList<Cell>();
		Cell current = b.getCell(x, y, b);
		queue.add(current);
		visited.add(current);
		adj = b.getAdjCell(current, adj,id);
		while (!queue.isEmpty() && !finish) {
			current = queue.remove();
			adj = b.getAdjCell(current, adj,id);
			for (Cell c : adj) {				
				if( !visited.contains(c)) {
					c.parent = current;					
					visited.add(c);
					queue.add(c);
					if( finishP (c, finish, id)) {
						current = c;
						finish = true;
						break;
					}
				}
			}
		}
		return current;
	}
	
	public boolean finishP(Cell c, boolean finish, int id) {							// ritorna se è una cella vincente
		if ( id == 1 && c.getDirection(4) == 1)
			return true;
		else if ( id == -1 && c.getDirection(4) == 2)
			return true;
		else 
			return false;			
	}
	
}
