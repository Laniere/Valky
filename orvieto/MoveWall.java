package gj.quoridor.player.orvieto;

public abstract class MoveWall extends Move{

	public MoveWall ( int i, int y ) {
		super ( i, y );
	}
	abstract public int calcJ(Board b, Cell a, int id);
	
	public int[] evalMove( int[] PossibleWallsIndex, int id, Board b, int xa, int ya, int xp, int yp){	
		int moveWall[] = new int[2];
		Cell a = new Cell();
		Cell me = new Cell();
		a = b.getCell(xa, ya, b);
		me = b.getCell(xp, yp, b);
		moveWall[0] = 1;
		moveWall[1] = evalIndex( PossibleWallsIndex, a, me, b, id);
		return moveWall;
	}

	public int evalIndex( int[] PossibleWallsIndex, Cell a, Cell me, Board b, int id) {
		int j = 0;
		j = calcJ( b, a, id);
		boolean checkisOk;
		boolean isCorrectDir;
		isCorrectDir = isCorrectDir1( a, id );
		checkisOk = isOk(PossibleWallsIndex, b, a, me, id,j);
		if ( checkisOk && isCorrectDir)
			return j;
		else
			j = j - 1;
			isCorrectDir = isCorrectDir2( a , id);
			checkisOk = isOk(PossibleWallsIndex, b, a, me, id,j);
			if ( checkisOk && isCorrectDir)
				return j;
			else
				j = -1;
				return j;
	}

	
	/*
	 * Il metodo restituisce se il muro scelto rispetta tutte le regole di correttezza
	 */
		
		public boolean isCorrectDir2 ( Cell a, int id) {
			if (id == 1 && a.getX() == 0)
				return false;
			else if ( id == -1 && a.getX() == 0)
				return false;
			return true;
		}
		
		public boolean isCorrectDir1 ( Cell a, int id) {
			if (id == 1 && a.getX() == 8)
				return false;
			else if ( id == -1 && a.getX() == 8)
				return false;
			return true;
		}
		
		public boolean isOk( int[] PossibleWallsIndex,Board b, Cell a, Cell me, int id, int j) {
			boolean path = false;
			boolean avaiable = false;
			avaiable = checkArrayIndexWall( PossibleWallsIndex, j);
			if ( avaiable ) {
				path = checkIfThereIsPath( b, id, a, me, j);
				if( path )
					return true;
				else
					return false;
			}
			return false;
		}
	
	abstract boolean checkIfThereIsPath( Board b, int id, Cell a, Cell me, int j);

	public int evalPathLength ( int id, Board b, int xp, int yp, int xp1, int yp1) {
		MoveMinPath adj1 = new MoveMinPath(0,0);
		int avaiableMove[] = new int[1];
		avaiableMove = b.getDirectionB(xp, yp, b); 
		adj1.evalMove(avaiableMove, id, b, xp, yp, xp, yp);
		return adj1.getLength ();
	}
	
	public boolean checkArrayIndexWall( int [] PossibleWallsIndex, int j) {			// controllo se il muro è disponibile
		if ( PossibleWallsIndex[j] == -1)
			return false;
		return true;
	}
}
