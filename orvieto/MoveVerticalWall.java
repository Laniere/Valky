package gj.quoridor.player.orvieto;

public class MoveVerticalWall extends MoveWall {

	public MoveVerticalWall ( int i, int y ) {
		super ( i, y );
	}

	@Override
	public int calcJ ( Board b, Cell a, int id ) {
		int j = 0;
		if ( id == 1 ) {
			j = (2*a.getY()+1)*8+a.getX()-9;
		}
		else {
			j = (2*a.getY()+1)*8+a.getX()-24;
		}
		return j;
	}

	public boolean pathIsLonger ( int pathBeforeWallLength, int pathAfterWallLength, int id ) {
		if (pathBeforeWallLength == pathAfterWallLength && id == 1)
			return true;
		else if (pathBeforeWallLength  != pathAfterWallLength  && id == -1)
			return true;
		return false;
	}
	
	public boolean checkIfThereIsPath( Board b, int id, Cell a, Cell me, int j) {   
		int pathBeforeWallLength = evalPathLength( id, b, a.getX(), a.getY(), a.getX(), a.getY());
		Wall tryWall = new Wall (j);
		b.updateboard(tryWall, b);
		int pathAfterWallLength = evalPathLength( id, b, a.getX(), a.getY(), a.getX(), a.getY());
		Cell d1 = new Cell();
		Cell d2 = new Cell();
		d1 = bfgEval(a.getDirection(), -id, b, a.getX(), a.getY());
		d2 = bfgEval(me.getDirection(), id, b, me.getX(), me.getY());
		boolean pathIsLonger = pathIsLonger( pathBeforeWallLength, pathAfterWallLength, id);
		if ((d1.getDirection(4) == 2 && d2.getDirection(4) == 1 ) && id == 1 && pathIsLonger ) {
			b.revertUpdate(tryWall, b);
			b.setParentNull();
			return true;
		}
		else if ((d1.getDirection(4) == 1 && d2.getDirection(4) == 2 ) && id == -1 && pathIsLonger) {
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
