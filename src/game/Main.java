package game;

import util.CompletedGrids;

public class Main {

	public static void main(String[] args) {

		Board board = Game.generateBoard();
		
		board.grid = CompletedGrids.getCompletedGrid();
		board.printBoard();
		System.out.println("Board conflicts: " + Game.verifyBoardConflicts(board));
		if(Game.verifyBoardSolved(board)) {
			System.out.println("Solved!");
		}

	}

}
