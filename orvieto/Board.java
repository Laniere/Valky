package gj.quoridor.player.orvieto;

import java.util.LinkedList;

public class Board{
	public Board() {
		setCenter();
		setCorner();
		setSide();
		setCoord();
	}

	private Cell [][] board = new Cell [9][9];
	
	public int [] getDirectionB(int x, int y, Board b) {
		return board[x][y].getDirection();                    
	}
	
	public Cell getCell(int x, int y, Board b) {
		return board[x][y];                    
	}
	
	public void setParentNull() {
		for ( int i = 0; i < 9; i++) {
			for ( int j = 0; j < 9; j++) {
				board [i][j].setParent(null);
			}
		}
	}
	
	public LinkedList<Cell> getAdjCell( Cell c, LinkedList <Cell> l, int id) {		//ritorna le celle adiacenti
		if( id == 1) {
			if (c.getDirection(0) == 1)
				 l.add ( board[c.getX()][c.getY()-1] );
			 if (c.getDirection(1) == 1)
				 l.add ( board[c.getX()][c.getY()+1] );
			 if (c.getDirection(2) == 1)
				 l.add ( board[c.getX()-1][c.getY()] );
			 if (c.getDirection(3) == 1)
				 l.add ( board[c.getX()+1][c.getY()] );
			 return l;
		}
		else {
			if (c.getDirection(1) == 1)
				 l.add ( board[c.getX()][c.getY()+1] );
			 if (c.getDirection(0) == 1)
				 l.add ( board[c.getX()][c.getY()-1] );
			 if (c.getDirection(3) == 1)
				 l.add ( board[c.getX()+1][c.getY()] );
			 if (c.getDirection(2) == 1)
				 l.add ( board[c.getX()-1][c.getY()] );
			 return l;
		}
	}	
	
	
/*
 *  Metodi per inizializzare la board
 */
	
	public void setCenter() {
		for ( int i = 0; i < 9; i++) {
			for ( int j = 0; j < 9; j++) {
				board [i][j] = new Cell();
			}
		}
	}
	
	public void setCoord() {
		for ( int i = 0; i < 9; i++) {
			for ( int j = 0; j < 9; j++) {
				board[j][i].setY(i);	
			}
		}
		for ( int i = 0; i < 9; i++) {
			for ( int j = 0; j < 9; j++) {
				board[i][j].setX(i);
			}
		}
	}
	
	public void setCorner() {							
		int [] c0 = {0, 1, 0, 1, 2};
		int [] c1 = {0, 1, 1, 0, 2};
		int [] c2 =	{1, 0, 0, 1, 1};
		int [] c3 = {1, 0, 1, 0, 1};
		board[0][0].setDirection(c0);
		board[8][0].setDirection(c1);
		board[0][8].setDirection(c2);
		board[8][8].setDirection(c3);
	}
	
	public void setSide() {						
		setlowrow();
		setuprow();
		setleftrow();
		setrightrow();
	}
	
	public void setlowrow() {
		for (int c = 1; c < 8; c++) {
			int [] c0 = {1, 0, 1, 1, 1};
			board[c][8].setDirection(c0);
		}
	}
	
	public void setuprow() {
		for (int c = 1; c < 8; c++) {
			int [] c0 = {0, 1, 1, 1, 2};
			board[c][0].setDirection(c0);
		}
	}
	
	public void setleftrow() {
		for (int c = 1; c < 8; c++) {
			int [] c0 = {1, 1, 0, 1, 0};
			board[0][c].setDirection(c0);
		}
	}
	
	public void setrightrow() {
		for (int c = 1; c < 8; c++) {
			int [] c0 = {1, 1, 1, 0, 0};
			board[8][c].setDirection(c0);
		}
	}
	
/*
*  Metodi per modificare le direzioni delle celle della board in base al muro ricevuto
*/
	
	public void updateboard(Wall w, Board b) {						
		if ( w.getDirectionW() == 1 ) 
			updateV( w, b ); 		
		else																					
			updateH( w, b );		
	}
	
	public void updateV ( Wall w, Board b ) {								
		board[ w.getX() ][ w.getY() ].setDirectionNull(3);       
		board[ w.getX() ][ w.getY()+1 ].setDirectionNull(3);
		board[ w.getX()+1 ][ w.getY() ].setDirectionNull(2);
		board[ w.getX()+1 ][ w.getY()+1 ].setDirectionNull(2);
	}
	
	public void updateH ( Wall w, Board b ) {								
		board[ w.getX() ][ w.getY() ].setDirectionNull(1);
		board[ w.getX()+1 ][ w.getY() ].setDirectionNull(1);
		board[ w.getX() ][ w.getY()+1 ].setDirectionNull(0);	
		board[ w.getX()+1][ w.getY()+1 ].setDirectionNull(0);
	}
	
	public void revertUpdate( Wall w, Board b ) {
		if (w.getDirectionW() == 1)
			revertV( w, b );
		else
			revertH( w, b );
	}
	
	public void revertV ( Wall w, Board b) {
		board[ w.getX() ][ w.getY() ].setDirectionOpen(3);       
		board[ w.getX() ][ w.getY()+1 ].setDirectionOpen(3);
		board[ w.getX()+1 ][ w.getY() ].setDirectionOpen(2);
		board[ w.getX()+1 ][ w.getY()+1 ].setDirectionOpen(2);
	}
	
	public void revertH ( Wall w, Board b) {
		board[ w.getX() ][ w.getY() ].setDirectionOpen(1);
		board[ w.getX()+1 ][ w.getY() ].setDirectionOpen(1);
		board[ w.getX() ][ w.getY()+1 ].setDirectionOpen(0);
		board[ w.getX()+1][ w.getY()+1 ].setDirectionOpen(0);
	}
	
	
}
