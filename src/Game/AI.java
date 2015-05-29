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
	
	/**
	 * Make an AI move
	 * @param bm current board mechanics
	 * @return integer of column
	 */
	public int makeAIMove(BoardMechanics bm) {
		int move = -1;
		switch (difficulty) {
		case 0: move = makeBogoMove(bm); break;
		case 1: move = makeEasyMove(bm); break;
		case 2: move = makeNormMove(bm); break;
		case 3: move = makeHardMove(bm); break;
		}
		bm.resetWin();
		//bm.dropToken(move);
		return move;
	}
	
	/**
	 * Makes stupid random move
	 * @param bm current board mechanics
	 * @return random column
	 */
	private int makeBogoMove(BoardMechanics bm) {
		int move = (int) Math.floor(Math.random()* 7);
		while (bm.getBoard().get(0).get(move).getValue() != 0) move = (int) Math.floor(Math.random()* 7);
		return move;
	}
	
	/**
	 * Makes slightly less stupid move
	 * @param bm current board mechanics
	 * @return slightly calculated column
	 */
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
			if (bm.getBoard().get(0).get(3).getValue() == 0) {
				return 3;
			}
			col = makeBogoMove(bm);
		}
		return col;
	}
	
	/**
	 * Calculates for the easy move function
	 * @param bm current board mechanics
	 * @return list of weights corresponding to columns
	 */
	private LinkedList<Integer> getEasyColWeights(BoardMechanics bm) {
		LinkedList<Integer> cols = new LinkedList<Integer>();
		BoardMechanics copy;
		int i;
		int player = bm.getCurrentPlayer();
		//int otherPlayer = 1; if (player == 1) otherPlayer = 2;
		
		for (i = 0; i < 7; i ++) {
			copy = bm;
			if (bm.getBoard().get(0).get(i).getValue() == 0) {
				copy.dropToken(i);
				if (copy.checkForWin(true)) {
					cols.add(10);
					copy.undoDropToken(i);
					continue;
				} else {
					copy.undoDropToken(i);
				}
				copy.dropToken(i);
				if (copy.checkForWin(true)) {
					cols.add(9);
					copy.undoDropToken(i);
					continue;
				} else {
					copy.undoDropToken(i);
				}
			}
			cols.add(1);
		}
		bm.checkForWin(true);
		// copy board
		// check if victory occurs by adding into each column
		if (bm.getCurrentPlayer() != player) bm.nextPlayer();
		return cols;
	}
	
	/**
	 * Makes an educated move
	 * @param bm current board mechanics
	 * @return column to drop token
	 */
	private int makeNormMove(BoardMechanics bm) {
		int currentMax = 0;
		int currentCol = -1;
		int c, r;
		ArrayList<ArrayList<Integer>> hValues = calculateHeuristics(bm);
		for (c = 0; c < 7 ; c ++) {
			r = 0;
			//System.out.println(r + " " + c);
			while (r < 5 && hValues.get(r+1).get(c) != 0) {
				//System.out.println(r + " " + c);
				r ++;
			}
			if (hValues.get(r).get(c) > currentMax) {
				currentMax = hValues.get(r).get(c);
				currentCol = c;
			}
			//System.out.println("HI");
		}
		return currentCol;
	}
	
	/**
	 * Similar to makeNormMove but will can aggressively force a win
	 * @param bm board position
	 * @return column integer
	 */
	private int makeHardMove(BoardMechanics bm) {
		int currentMax = 0;
		int currentCol = -1;
		int forceWinCol = -1;
		int c, r, winPositionsInCol;
		ArrayList<ArrayList<Integer>> hValues = calculateHeuristics(bm);
		for (c = 0; c < 7 ; c ++) {
			r = 0;
			winPositionsInCol = 0;
			//System.out.println(r + " " + c);
			while (r < 5 && hValues.get(r+1).get(c) != 0) {
				if (r < 5) {
					if (hValues.get(r).get(c) == 1000 &&
							hValues.get(r + 1).get(c) == 1000)	
						winPositionsInCol += 1;
				}
				if (hValues.get(r).get(c) == 999) winPositionsInCol = 0;
				r ++;
			}
			if (winPositionsInCol >= 2) forceWinCol = c;
			if (hValues.get(r).get(c) > currentMax) {
				currentMax = hValues.get(r).get(c);
				currentCol = c;
			}
			//System.out.println("HI");
		}
		if (currentMax < 10 && forceWinCol != -1) return forceWinCol;
		return currentCol;
	}

	/**
	 *  Calculates a heuristics for a board
	 * if winning move											value = 1000
	 * if opponents winning move 								value = 999
	 * counts number sets of 2tokens of either player + 2empty spaces that can be made in all directions
	 * + 7-howfarfromthemiddlerow it is + no of surrounding token 
	 * 															value = total number + column value	
	 * if winning move above it									value = 1		
	 * if filled												value = 0
	 * @param bm current board mechanics
	 * @return a board of heuristic values
	 */
	private ArrayList<ArrayList<Integer>> calculateHeuristics(BoardMechanics bm) {
		ArrayList<ArrayList<Integer>> heuristics = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Cell>> boardCopy = bm.getBoard();
		BoardMechanics copy;
		int player = bm.getCurrentPlayer();
		int otherPlayer = 1; if (player == 1) otherPlayer = 2;
		int row,col,trow,tcol,countPlayer, countOpponent,countEmpty, storeValue;
		LinkedList<Integer> tokenStore;
		
		for(int i = 0; i < 6; i++){
			heuristics.add(new ArrayList<Integer>());
		}
		
		System.out.println("board input");
		for (ArrayList<Cell> ac: boardCopy) {
			for (Cell ce : ac) {
				System.out.print(ce.getValue() + " ");
			}
			System.out.println("");
		}
		
		for (row = 0; row < 6; row ++) {
			for (col = 0; col < 7; col ++) {
				/*
				System.out.println("row" + row + " col" + col);
				for (ArrayList<Cell> ac: boardCopy) {
					for (Cell ce : ac) {
						System.out.print(ce.getValue() + " ");
					}
					System.out.println("");
				}
				*/
				
				// check if filled
				if (boardCopy.get(row).get(col).getValue() != 0) {
					heuristics.get(row).add(0);
					continue;
				}
				
				// check if winning
				copy = bm;
				copy.customDropToken(row, col, player);
				if (copy.checkForWin(true)) {
					heuristics.get(row).add(1000);
					copy.customDropToken(row, col, 0);
					continue;
				}
				copy.customDropToken(row, col, otherPlayer);
				if (copy.checkForWin(true)) {
					heuristics.get(row).add(999);
					copy.customDropToken(row, col, 0);
					continue;
				}
				copy.customDropToken(row, col, 0);
				
				// check if leads to winning
				if (row > 0) {
					copy = bm;
					copy.customDropToken(row - 1, col, player);
					if (copy.checkForWin(true)) {
						heuristics.get(row).add(col,1);
						copy.customDropToken(row - 1, col, 0);
						continue;
					}
					copy.customDropToken(row - 1, col, otherPlayer);
					if (copy.checkForWin(true)) {
						heuristics.get(row).add(col,1);
						copy.customDropToken(row - 1, col, 0);
						continue;
					}
					copy.customDropToken(row - 1, col, 0);
				}
				
				// universal check
				// vertical
				countPlayer = 0;
				countEmpty = 0;
				countOpponent = 0;
				tokenStore = new LinkedList<Integer>();
				storeValue = 0;
				for (trow = 5; trow > 1; trow --) {
					tokenStore.add(boardCopy.get(trow).get(col).getValue());
				}
				for (trow = 1; trow >= 0; trow --) {
					countPlayer = 0;
					countEmpty = 0;
					countOpponent = 0;
					for(Integer c : tokenStore) {
						if (c == 0) countEmpty ++;
						else if (c == 1) countPlayer ++;
						else if (c == 2) countOpponent ++;
					}
					if (countEmpty == 2 && (countPlayer == 2 || countOpponent == 2) && trow <= row) storeValue ++;
					tokenStore.remove();
					tokenStore.add(boardCopy.get(trow).get(col).getValue());
				}
				countPlayer = 0;
				countEmpty = 0;
				countOpponent = 0;
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
					countPlayer = 0;
					countEmpty = 0;
					countOpponent = 0;
					for(Integer c : tokenStore) {
						if (c == 0) countEmpty ++;
						else if (c == 1) countPlayer ++;
						else if (c == 2) countOpponent ++;
					}
					//System.out.println(countEmpty + " " + countPlayer + " " + countOpponent);
					if (countEmpty == 2 && (countPlayer == 2 || countOpponent == 2) && tcol <= col && tcol + 4 >= col) storeValue ++;
					tokenStore.remove();
					tokenStore.add(boardCopy.get(row).get(tcol).getValue());
				}
				countPlayer = 0;
				countEmpty = 0;
				countOpponent = 0;
				for(Integer c : tokenStore) {
					if (c == 0) countEmpty ++;
					else if (c == 1) countPlayer ++;
					else if (c == 2) countOpponent ++;
				}
				if (countEmpty == 2 && (countPlayer == 2 || countOpponent == 2) && tcol <= col) storeValue ++;
				
				// positive gradient diagonal
				trow = row + (6-col);
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
						if (trow < 0 || tcol < 0) break;
						countPlayer = 0;
						countEmpty = 0;
						countOpponent = 0;
						for(Integer c : tokenStore) {
							if (c == 0) countEmpty ++;
							else if (c == 1) countPlayer ++;
							else if (c == 2) countOpponent ++;
						}
						if (countEmpty == 2 && (countPlayer == 2 || countOpponent == 2) && tcol <= col && trow - 4 <= row) storeValue ++;
						tokenStore.remove();
						//System.out.println(tcol + " " + trow);
						tokenStore.add(boardCopy.get(trow).get(tcol).getValue());
						trow --;
					}
					countPlayer = 0;
					countEmpty = 0;
					countOpponent = 0;
					for(Integer c : tokenStore) {
						if (c == 0) countEmpty ++;
						else if (c == 1) countPlayer ++;
					}
					if (countEmpty == 2 && (countPlayer == 2 || countOpponent == 2) && tcol <= col) storeValue ++;
				}
				
				// negative gradient diagonal
				trow = row - (col);
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
						countPlayer = 0;
						countEmpty = 0;
						countOpponent = 0;
						for(Integer c : tokenStore) {
							if (c == 0) countEmpty ++;
							else if (c == 1) countPlayer ++;
							else if (c == 2) countOpponent ++;
						}
						if (countEmpty == 2 && (countPlayer == 2 || countOpponent == 2) && tcol <= col && trow <= row) storeValue ++;
						tokenStore.remove();
						tokenStore.add(boardCopy.get(trow).get(tcol).getValue());
						trow ++;
					}
					countPlayer = 0;
					countEmpty = 0;
					countOpponent = 0;
					for(Integer c : tokenStore) {
						if (c == 0) countEmpty ++;
						else if (c == 1) countPlayer ++;
					}
					if (countEmpty == 2 && (countPlayer == 2 || countOpponent == 2) && tcol <= col) storeValue ++;
				}
				//System.out.print(storeValue);
				storeValue = storeValue * 3;
				
				// count surrounding token number
				if (difficulty >= 1) {
					if (col < 6) {
						if (row < 5)
							if (boardCopy.get(row + 1).get(col + 1).getValue() == player) storeValue ++;
						if (boardCopy.get(row).get(col + 1).getValue() == player) storeValue ++;
						if (row > 0)
							if (boardCopy.get(row - 1).get(col + 1).getValue() == player) storeValue ++;
					}
					if (row < 5)
						if (boardCopy.get(row + 1).get(col).getValue() == player) storeValue ++;
					if (row > 0)
						if (boardCopy.get(row - 1).get(col).getValue() == player) storeValue ++;
					if (col > 0) {
						if (row > 0)
							if (boardCopy.get(row - 1).get(col - 1).getValue() == player) storeValue ++;
						if (boardCopy.get(row).get(col - 1).getValue() == player) storeValue ++;
						if (row < 5)
							if (boardCopy.get(row + 1).get(col - 1).getValue() == player) storeValue ++;
					}
				}
				if (difficulty == 3) {
					if (col < 5) {
						if (row < 4)
							if (boardCopy.get(row + 1).get(col + 1).getValue() == player &&
									(boardCopy.get(row + 2).get(col + 2).getValue() == player ||
									boardCopy.get(row + 2).get(col + 2).getValue() == 0)) 
								storeValue ++;
						if (boardCopy.get(row).get(col + 1).getValue() == player && 
								(boardCopy.get(row).get(col + 2).getValue() == player)) 
							storeValue ++;
						if (row > 1)
							if (boardCopy.get(row - 1).get(col + 1).getValue() == player &&
									(boardCopy.get(row - 2).get(col + 2).getValue() == player ||
									boardCopy.get(row - 2).get(col + 2).getValue() == 0)) 
								storeValue ++;
					}
					if (row < 4)
						if (boardCopy.get(row + 1).get(col).getValue() == player && 
								(boardCopy.get(row + 2).get(col).getValue() == player ||
								boardCopy.get(row + 2).get(col).getValue() == 0)) 
							storeValue ++;
					if (row > 1)
						if (boardCopy.get(row - 1).get(col).getValue() == player && 
								(boardCopy.get(row - 2).get(col).getValue() == player ||
								boardCopy.get(row - 2).get(col).getValue() == 0)) 
							storeValue ++;
					if (col > 1) {
						if (row > 1)
							if (boardCopy.get(row - 1).get(col - 1).getValue() == player && 
									(boardCopy.get(row - 2).get(col - 2).getValue() == player || 
									boardCopy.get(row - 2).get(col - 2).getValue() == 0)) 
								storeValue ++;
						if (boardCopy.get(row).get(col - 1).getValue() == player && 
								(boardCopy.get(row).get(col - 2).getValue() == player ||
								boardCopy.get(row).get(col - 2).getValue() == 0)) 
							storeValue ++;
						if (row < 4)
							if (boardCopy.get(row + 1).get(col - 1).getValue() == player &&
									(boardCopy.get(row + 2).get(col - 2).getValue() == player ||
									boardCopy.get(row + 2).get(col - 2).getValue() == 0)) 
								storeValue ++;
					}
				}
				//
				storeValue += 4 - Math.abs(3 - col);
				bm.resetButtonPresses();
				heuristics.get(row).add(storeValue);
			}
		}
		System.out.println("heuristics");
		for (ArrayList<Integer> array: heuristics) {
			for (Integer i : array) {
				System.out.print(i + " ");
			}
			System.out.println("");
		}
		return heuristics;
	}
	
	/**
	 * Uses AI to get hint
	 * @param bm current board state
	 * @return column to drop token
	 */
	public int getHint(BoardMechanics bm) {
		int move = makeHardMove(bm);
		bm.resetWin();
		return move;
	}
}
