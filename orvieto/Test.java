package gj.quoridor.player.orvieto;

import java.util.LinkedList;

public class Test {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		// test board
		
		Board b = new Board();
		
		// test per la creazione della lista delle celle adiacenti a quella corrente
		
		LinkedList<Cell> queue = new LinkedList<Cell>();
		int id = 1;
		int x=8;
		int y=0;
		Cell current = b.getCell(x, y, b);
		queue = b.getAdjCell(current, queue,id);
		
		
		//test creazione oggetto muro
		
		Wall w = new Wall(18);
		b.updateboard(w, b);
		Wall w1 = new Wall(1);
		b.updateboard(w, b);
		Wall w2 = new Wall(98);
		b.updateboard(w, b);
		Wall w3 = new Wall(77);
		b.updateboard(w, b);
		Wall w4 = new Wall(22);
		b.updateboard(w, b);
		
		// test mossa
		
		int mossa[] = new int[2];
		Move m = new MoveRandom(1,2);
		
	}

}
