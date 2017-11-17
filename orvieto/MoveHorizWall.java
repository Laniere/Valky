package gj.quoridor.player.orvieto;

public class MoveHorizWall extends MoveWall {
	public MoveHorizWall(int i, int j){
		super( i, j );
	}

	public boolean pathIsNotLonger ( int pathBeforeWallLength, int pathAfterWallLength ) {
		if (pathBeforeWallLength < pathAfterWallLength)
			return false;
		return true;
	}
	
	public int calcJ(Board b, Cell a, int id) {				// metodo per il calcolo dell'indice del muro
		int j = 0;
		if ( id == 1 ) {
			j = (2*a.getY()+1)*8+a.getX()-16;
		}
		else {
			j = (2*a.getY()+1)*8+a.getX();
		}
		return j;
	}
	
	public boolean checkIfThereIsPath( Board b, int id, Cell a, Cell me, int j) {   
		int pathBeforeWallLength = evalPathLength( id, b, me.getX(), me.getY(), me.getX(), me.getY());
		Wall tryWall = new Wall (j);
		b.updateboard(tryWall, b);
		int pathAfterWallLength = evalPathLength( id, b, me.getX(), me.getY(), me.getX(), me.getY());
		Cell d1 = new Cell();
		Cell d2 = new Cell();
		d1 = bfgEval(a.getDirection(), -id, b, a.getX(), a.getY());
		d2 = bfgEval(me.getDirection(), id, b, me.getX(), me.getY());
		boolean pathIsNotLonger = pathIsNotLonger( pathBeforeWallLength, pathAfterWallLength);
		if ((d1.getDirection(4) == 2 && d2.getDirection(4) == 1 ) && id == 1 && pathIsNotLonger ) {
			b.revertUpdate(tryWall, b);
			b.setParentNull();
			return true;
		}
		else if ((d1.getDirection(4) == 1 && d2.getDirection(4) == 2 ) && id == -1 && pathIsNotLonger) {
			b.revertUpdate(tryWall, b);
			b.setParentNull();
			return true;
		}
		else
			b.revertUpdate(tryWall, b);;
			b.setParentNull();
			return false;
	}
}
