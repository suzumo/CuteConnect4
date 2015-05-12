package Game;

import java.util.*;


public class AI {
	private int difficulty; // 1 easiest 3 hardest
	public AI(int i) {
		difficulty = i;
	}
	public int makeAIMove(Board b) {
		int move = -1;
		switch (difficulty) {
		case 0: move = makeBongoMove(b); break;
		case 1: move = makeEasyMove(b); break;
		}
		
		return move;
	}
	private int makeBongoMove(Board b) {
		int move = (int) Math.ceil(Math.random()* 7);
		while (!b.checkMoveValid(move)) move = (int) Math.ceil(Math.random()* 7);
		return move;
	}
	private int makeEasyMove(Board b) {
		LinkedList<Integer> avoidCols = checkImpendingVictory(b);
		int move = (int) Math.ceil(Math.random()* 7);
		int i = 0;
		while (!b.checkMoveValid(move) || avoidCols.contains(move)) {
			move = (int) Math.ceil(Math.random()* 7);
			i += 1;
			if (i == 10) break;
		}
		return move;
	}
	private LinkedList<Integer> checkImpendingVictory(Board b) {
		LinkedList<Integer> winningColumns = new LinkedList<Integer>();
		// copy board
		// check if victory occurs by adding into each column
		return winningColumns;
	}
}
