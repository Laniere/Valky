package gj.quoridor.player.orvieto;

public class MoveRandom extends Move {
	public MoveRandom(int i, int j){
		super( i, j );
	}
	
	public int[] evalMove( int[] avaiableMove, int id, Board b, int xp, int yp,int x, int y){				
		int move[] = new int[2];
		int rnd;
		rnd = random( avaiableMove );
		rnd = red(rnd, id);
		move[0] = 0;
		move[1] = rnd;
		return move;
	}
	
	private int random( int [] avaiableMove ) {
		int r;
		r = (int) (Math.random() * (4));
		if ( avaiableMove[r] == 0)
			return random( avaiableMove );
		else
			return r;
	}
	
	private int red( int rnd, int id) {
		if (id == 1) {
			if (rnd == 0) {
				rnd = 1;
				return rnd;
			}
			else if(rnd == 1) {
				rnd = 0;
				return rnd;
			}
			else if(rnd == 2) {
				rnd = 3;
				return rnd;
				}
			else if(rnd == 3) {
				rnd = 2;
				return rnd;
			}
			return rnd;
			}
		else return rnd;
	}
}
