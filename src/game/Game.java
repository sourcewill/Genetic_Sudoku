package game;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import util.CompletedGrids;

public class Game {

	public static Board generateBoard() {

		int gridCompleted[][] = CompletedGrids.getCompletedGrid();
		Board board = new Board();
		int counter = 0;
		int random1, random2;
		Random random = new Random();

		while (counter < util.Arguments.NUMBER_CELLS_DISPLAYED) {
			random1 = random.nextInt(9);
			random2 = random.nextInt(9);

			if (board.editable[random1][random2]) {
				board.grid[random1][random2] = gridCompleted[random1][random2];
				board.editable[random1][random2] = false;
				counter++;
			}
		}

		return board;
	}
	
public static boolean verifyBoardSolved(Board board) {
		
		int [][] grid = board.grid;
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(grid[i][j] == 0) return false;
			}
		}
		
		if(verifyBoardConflicts(board) > 0) return false;
		
		return true;
	}

	public static int verifyBoardConflicts(Board board) {

		int conflicts = 0;

		for (int i = 0; i < 9; i++) {
			conflicts += verifyRowConflicts(i, board);
			conflicts += verifyColumnConflicts(i, board);
		}
		conflicts += verifySubBoardConflicts(board);
		return conflicts;
	}

	public static int verifyRowConflicts(int rowNumber, Board board) {

		int cellNumber, conflicts = 0;
		Set<Integer> set = new HashSet<Integer>();

		for (int i = 0; i < 9; i++) {

			cellNumber = board.grid[rowNumber][i];
			if (cellNumber != 0) {
				if (!set.add(cellNumber))
					conflicts++;
			}

		}
		if(conflicts > 0) {
			return conflicts+1;
		}
		return 0;
	}

	public static int verifyColumnConflicts(int columnNumber, Board board) {

		int cellNumber, conflicts = 0;
		Set<Integer> set = new HashSet<Integer>();

		for (int i = 0; i < 9; i++) {

			cellNumber = board.grid[i][columnNumber];
			if (cellNumber != 0) {
				if (!set.add(cellNumber))
					conflicts++;
			}

		}
		if(conflicts > 0) {
			return conflicts+1;
		}
		return 0;
	}

	public static int verifySubBoardConflicts(Board board) {
		
		int conflicts = 0, subconflicts;
		int[][] grid = board.grid;
		
		for (int row = 0; row < 9; row = row + 3) {
			for (int col = 0; col < 9; col = col + 3) {

				Set<Integer> set = new HashSet<Integer>();
				subconflicts = conflicts;
				
				for (int r = row; r < row + 3; r++) {
					for (int c = col; c < col + 3; c++) {

						if (grid[r][c] != 0) {
							if (!set.add(grid[r][c])) {
								conflicts++;
							}
						}
					}
				}
				if(subconflicts != conflicts) {
					conflicts++;
				}
			}
		}
		return conflicts;
	}

}
