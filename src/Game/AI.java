/*package Game;

import java.util.*;


public class AI {
	private int difficulty; // 1 easiest 3 hardest
	public AI(int i) {
		difficulty = i;
	}
	public int makeAIMove(BoardMechanics b) {
		int move = -1;
		switch (difficulty) {
		case 0: move = makeBongoMove(b); break;
		case 1: move = makeEasyMove(b); break;
		}
		
		return move;
	}
	private int makeBongoMove(BoardMechanics b) {
		int move = (int) Math.ceil(Math.random()* 7);
		while (!b.checkMoveValid(move)) move = (int) Math.ceil(Math.random()* 7);
		return move;
	}
	private int makeEasyMove(BoardMechanics b) {
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
	private LinkedList<Integer> checkImpendingVictory(BoardMechanics b) {
		LinkedList<Integer> winningColumns = new LinkedList<Integer>();
		// copy board
		// check if victory occurs by adding into each column
		return winningColumns;
	}
}*/
package Game;

import java.util.*;


public class AI {
	private int difficulty; // 1 easiest 3 hardest
	public AI(int i) {
		difficulty = i;
	}
	public int makeAIMove(BoardMechanics bm) {
		int move = -1;
		switch (difficulty) {
		case 0: move = makeBongoMove(bm); break;
		case 1: move = makeEasyMove(bm); break;
		case 2: move = makeHardMove(bm); break;
		}
		
		return move;
	}
	private int makeBongoMove(BoardMechanics bm) {
		int move = (int) Math.ceil(Math.random()* 7);
		while (!bm.checkMoveValid(move)) move = (int) Math.ceil(Math.random()* 7);
		return move;
	}
	private int makeEasyMove(BoardMechanics bm) {
		LinkedList<Integer> cols = getEasyColWeights(bm);
		int currentWeight = 0;
		int col = -1;
		int i;
		
		for (i = 0; i < 7; i ++) {
			if (currentWeight < cols.get(i)) {
				currentWeight = cols.get(i);
				col = i;
			}
		}
		if (currentWeight == 1) {
			if (bm.getBoard().get(3).get(5).getValue() == 0) {
				return 3;
			}
			col = makeBongoMove(bm);
		}
		return col;
	}
	private LinkedList<Integer> getEasyColWeights(BoardMechanics bm) {
		LinkedList<Integer> cols = new LinkedList<Integer>();
		BoardMechanics copy;
		int i;
		int player = bm.getCurrentPlayer();
		int otherPlayer = 1; if (player == 1) otherPlayer = 2;
		
		for (i = 0; i < 7; i ++) {
			copy = bm;
			copy.dropToken(i);
			if (copy.checkForWin()) {
				cols.add(10);
				continue;
			}
			copy = bm;
			copy.switchCurrentPlayer();
			copy.dropToken(i);
			if (copy.checkForWin()) {
				cols.add(9);
				continue;
			}
			cols.add(1);
		}
		// copy board
		// check if victory occurs by adding into each column
		return cols;
	}
	
	private int makeHardMove(BoardMechanics bm) {
		int currentMax = 0;
		int currentCol = -1;
		int c = 0;
		for (ArrayList<Integer> al : calculateHeuristics(bm)) {
			for (Integer h : al) {
				while (h == 0) {
					continue;
				}
				if (h > currentMax) {
					currentMax = h;
					currentCol = c;
				}
			}
			c ++;
		}
		return currentCol;
	}
	/**
	 * Returns ArrayList<ArrayList<Int>> where for each cell
	 * if winning move											value = 100
	 * if opponents winning move 								value = 99
	 * counts number sets of 2tokens of either player + 2empty spaces that can be made in all directions
	 * + 7-howfarfromthemiddlerow it is + no of surrounding token 
	 * 															value = total number + column value	
	 * if winning move above it									value = 1		
	 * if filled												value = 0
	 * @param b
	 * @param player
	 * @return
	 */
	private ArrayList<ArrayList<Integer>> calculateHeuristics(BoardMechanics bm) {
		ArrayList<ArrayList<Integer>> heuristics = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Cell>> boardCopy = bm.getBoard();
		BoardMechanics copy;
		int player = bm.getCurrentPlayer();
		int otherPlayer = 1; if (player == 1) otherPlayer = 2;
		int row,col,trow,tcol,countPlayer,countEmpty, storeValue;
		Queue<Integer> tokenStore;
		
		for (row = 0; row < 6; row ++) {
			for (col = 0; col < 7; col ++) {
				// check if filled
				if (boardCopy.get(row).get(col).getValue() != 0) {
					heuristics.get(row).set(col,0);
					continue;
				}
				
				// check if winning
				copy = bm;
				copy.customDropToken(col, row, player);
				if (copy.checkForWin()) {
					heuristics.get(row).set(col,100);
					continue;
				}
				copy.customDropToken(col, row, otherPlayer);
				if (copy.checkForWin()) {
					heuristics.get(row).set(col,99);
					continue;
				}
				
				
				// check if leads to winning
				if (row < 5) {
					copy = bm;
					copy.customDropToken(col,row+1,player);
					if (copy.checkForWin()) {
						heuristics.get(row).set(col,1);
						continue;
					}
					copy.customDropToken(col, row+1, otherPlayer);
					if (copy.checkForWin()) {
						heuristics.get(row).set(col,1);
						continue;
					}
				}
				
				// universal check
				// vertical
				countPlayer = 0;
				countEmpty = 0;
				tokenStore = null;
				storeValue = 0;
				for (trow = 5; trow > 1; trow --) {
					tokenStore.add(boardCopy.get(trow).get(col).getValue());
				}
				for (trow = 1; trow >= 0; trow --) {
					for(Integer c : tokenStore) {
						if (c == 0) countEmpty ++;
						else if (c == 1) countPlayer ++;
					}
					if (countEmpty == 2 && countPlayer == 2 && trow <= row) storeValue ++;
					tokenStore.remove();
					tokenStore.add(boardCopy.get(trow).get(col).getValue());
				}
				for(Integer c : tokenStore) {
					if (c == 0) countEmpty ++;
					else if (c == 1) countPlayer ++;
				}
				if (countEmpty == 2 && countPlayer == 2 && trow <= row) storeValue ++;
				
				// horizontal
				tokenStore.clear();
				for (tcol = 6; tcol > 2; tcol --) {
					tokenStore.add(boardCopy.get(row).get(tcol).getValue());
				}
				for (tcol = 2; tcol >= 0; tcol --) {
					for(Integer c : tokenStore) {
						if (c == 0) countEmpty ++;
						else if (c == 1) countPlayer ++;
					}
					if (countEmpty == 2 && countPlayer == 2 && tcol <= col) storeValue ++;
					tokenStore.remove();
					tokenStore.add(boardCopy.get(row).get(tcol).getValue());
				}
				for(Integer c : tokenStore) {
					if (c == 0) countEmpty ++;
					else if (c == 1) countPlayer ++;
				}
				if (countEmpty == 2 && countPlayer == 2 && tcol <= col) storeValue ++;
				
				// positive gradient diagonal
				trow = row + (col-6);
				tcol = 6;
				while (trow > 5) {
					tcol --;
					trow --;
				}
				tokenStore.clear();
				for (tcol = tcol ; tcol > 0; tcol --) {
					tokenStore.add(boardCopy.get(row).get(col).getValue());
					if (tokenStore.size() == 4) break;
					trow --;
				}
				if (tokenStore.size() == 4) {
					for (tcol = tcol; tcol >= 0; tcol --) {
						if (trow == 0) break;
						for(Integer c : tokenStore) {
							if (c == 0) countEmpty ++;
							else if (c == 1) countPlayer ++;
						}
						if (countEmpty == 2 && countPlayer == 2 && tcol <= col && trow - 4 <= row) storeValue ++;
						tokenStore.remove();
						tokenStore.add(boardCopy.get(trow).get(tcol).getValue());
						trow --;
					}
					for(Integer c : tokenStore) {
						if (c == 0) countEmpty ++;
						else if (c == 1) countPlayer ++;
					}
					if (countEmpty == 2 && countPlayer == 2 && tcol <= col) storeValue ++;
				}
				
				// negative gradient diagonal
				trow = row - (col-6);
				tcol = 0;
				while (trow > 5) {
					tcol ++;
					trow --;
				}
				tokenStore.clear();
				for (tcol = tcol ; tcol > 0; tcol --) {
					tokenStore.add(boardCopy.get(row).get(col).getValue());
					if (tokenStore.size() == 4) break;
					trow ++;
				}
				if (tokenStore.size() == 4) {
					for (tcol = tcol; tcol >= 0; tcol --) {
						if (trow == 0) break;
						for(Integer c : tokenStore) {
							if (c == 0) countEmpty ++;
							else if (c == 1) countPlayer ++;
						}
						if (countEmpty == 2 && countPlayer == 2 && tcol <= col && trow <= row) storeValue ++;
						tokenStore.remove();
						tokenStore.add(boardCopy.get(trow).get(tcol).getValue());
						trow ++;
					}
					for(Integer c : tokenStore) {
						if (c == 0) countEmpty ++;
						else if (c == 1) countPlayer ++;
					}
					if (countEmpty == 2 && countPlayer == 2 && tcol <= col) storeValue ++;
				}
				
				// count surrounding token number
				if (boardCopy.get(trow + 1).get(tcol + 1).getValue() == player) storeValue ++;
				if (boardCopy.get(trow).get(tcol + 1).getValue() == player) storeValue ++;
				if (boardCopy.get(trow - 1).get(tcol + 1).getValue() == player) storeValue ++;
				if (boardCopy.get(trow - 1).get(tcol).getValue() == player) storeValue ++;
				if (boardCopy.get(trow - 1).get(tcol - 1).getValue() == player) storeValue ++;
				if (boardCopy.get(trow).get(tcol - 1).getValue() == player) storeValue ++;
				if (boardCopy.get(trow + 1).get(tcol - 1).getValue() == player) storeValue ++;
				
				//
				storeValue += Math.abs(3 - col);
				
				heuristics.get(row).set(col,storeValue);
			}
		}
		return heuristics;
	}
}
