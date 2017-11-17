package gj.quoridor.player.orvieto;

import gj.quoridor.player.*;
import gj.quoridor.engine.*;
import gj.quoridor.exception.*;
import java.util.*;
import java.awt.List;

@SuppressWarnings("unused")
public class OrvietoPlayer implements Player {
	private int yp,ya,xp,xa;					
	private int spost;							// variabile locale determinata dal boolean isFirst
	private Board b;
	private int[] PossibleWallsIndex;
	private int id;
	private int wallsNumber;

	@Override
	public int[] move() { 							
		int mossa[] = new int[2];
		int avaiableMove[] = new int[1];
		avaiableMove = b.getDirectionB(xp, yp, b);  
		mossa = evaluateMoveType(avaiableMove, mossa);		
		return mossa;		
	}
	
	public int[] evaluateMoveType( int [] avaiableMove, int [] mossa) {
		MoveMinPath adj1 = new MoveMinPath(0,0);
		MoveMinPath adj2 = new MoveMinPath(0,0);
		adj1.evalMove(avaiableMove, id, b, xp, yp, xp, yp);
		adj2.evalMove(avaiableMove, -id, b, xa, ya, xp, yp);
		if ( adj1.getLength() > adj2.getLength() && wallsNumber > 0)
			mossa = wallMoveChoosen ( avaiableMove, mossa);
		else
			mossa = moveChoosen( avaiableMove, mossa );
		return mossa;
	}
	
	public int[] wallMoveChoosen( int [] avaiableMove, int [] mossa ) {
		Move placeWall = new MoveHorizWall(1,0);
		mossa = placeWall.evalMove( PossibleWallsIndex, id, b, xa, ya, xp, yp );
		if ( mossa[1] != -1) {
			Wall w = new Wall(mossa[1]);
			b.updateboard(w, b);
			updatearray(w);
			wallsNumber = wallsNumber - 1;
			return mossa;
		}
		else mossa = moveVerticalWall( avaiableMove,mossa );
		return mossa;
	}
	
	public int [ ] moveVerticalWall ( int [] avaiableMove, int [] mossa ) {
		Move placeWall = new MoveVerticalWall(1,0);
		mossa = placeWall.evalMove( PossibleWallsIndex, id, b, xa, ya, xp, yp );
		if ( mossa[1] != -1) {
			Wall w = new Wall(mossa[1]);
			b.updateboard(w, b);
			updatearray(w);
			wallsNumber = wallsNumber - 1;
			return mossa;
		}
		else 
			mossa = moveChoosen( avaiableMove, mossa );
		return mossa;
	}
	
	
	public int[] moveChoosen( int [] avaiableMove, int [] mossa ) {
//		Move rnd = new MoveRandom(0,0);									//rimuovere slash per random move
//		mossa = rnd.evalMove( avaiableMove, id, b, xa, ya, xp, yp);
		Move m = new MoveMinPath(0,0);
		mossa = m.evalMove( avaiableMove, id, b, xp, yp, xp, yp );		
		updatePlayerCoord(mossa);
		return mossa;
	}
	
	@Override
	public void start(boolean isFirst) {			
		b = new Board();
		wallsNumber = 10;
		initArrayWall();
		if (isFirst == true) 											
			init1();	
		else 
			init2();
	}
	
	public void initArrayWall() {			
		PossibleWallsIndex = new int[129];	
		PossibleWallsIndex[128] = -1;
		for (int i=0;i<128;i++) {				
			PossibleWallsIndex[i]=i;
		}
	}
	
	public void init1() {
		id = 1; spost = 1; yp = 0; ya = 8; xa = 4; xp = 4;
	}
	
	public void init2() {
		id = -1; spost = -1;	yp = 8; ya = 0; xa = 4; xp = 4;
	}
	
	@Override
	public void tellMove(int[] move) { 					// riceve mossa dall'avversario
		if (move[0] == 0) 					 
			updateEnemyCoord(move);
		else { 								
			int j;
			j = move[1];
			Wall w = new Wall(j);
			b.updateboard(w, b);
			updatearray(w);
		}
	}
/*
 *  Metodi per l'update delle coordinate della board e dell'array dei muri possibili
 */
	
	public void updatePlayerCoord(int[] move) {				
		if (move[1] == 0) 
			yp = yp + 1 * spost;
		if (move[1] == 1) 
			yp = yp - 1 * spost;
		if (move[1] == 2) 
			xp = xp + 1 * spost;
		if (move[1] == 3) 
			xp = xp - 1 * spost;
	}
	public void updateEnemyCoord(int[] move) {	
		if (move[1] == 0) 
			ya = ya - 1 * spost;
		if (move[1] == 1) 
			ya = ya + 1 * spost;
		if (move[1] == 2) 
			xa = xa - 1 * spost;
		if (move[1] == 3) 
			xa = xa + 1 * spost;
	}
	
	public void updatearray(Wall w) {
		if ( w.getDirectionW() == 1 ) 
			updateArrayIndexVertical( w.getJ() ); 		
		else																					
			updateArrayIndexHoriz( w.getJ() );	
	}
	
	public void updateArrayIndexVertical(int j) {		
		PossibleWallsIndex[j]=-1;																
		if(j<111)
			PossibleWallsIndex[j+16]=-1;
		if (j<119)
			PossibleWallsIndex [j+8]=-1;
		if (j>16) 
			PossibleWallsIndex [j-16]=-1;
	}
	
	public void updateArrayIndexHoriz(int j) {	
		PossibleWallsIndex[j]=-1;																
		if(j<128)
			PossibleWallsIndex[j+1]=-1;
		if(j>0)
			PossibleWallsIndex [j-1]=-1;
		if(j>7)
			PossibleWallsIndex [j-8]=-1;
	}
	
}