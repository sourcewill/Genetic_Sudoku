package util;

import game.Board;

public class GridsToSolve {
	
	public static Board getEasyBoard() {
		
		int[][] grid = { 
				{ 1,5,6,3,0,4,2,0,0 },
				{ 9,0,0,1,8,0,0,7,0 },
				{ 8,0,0,0,6,0,1,4,5 },
				{ 0,8,3,0,9,6,5,0,7 },
				{ 0,0,4,0,0,0,0,0,0 },
				{ 0,1,9,7,0,3,8,0,4 },
				{ 4,0,0,5,0,1,7,6,2 },
				{ 7,0,0,0,0,0,0,0,0 },
				{ 3,0,0,0,0,7,0,0,9 }
				};
		
		boolean[][] editable = {
				{ false,false,false,false,true,false,false,true,true },
				{ false,true,true,false,false,true,true,false,true },
				{ false,true,true,true,false,true,false,false,false },
				{ true,false,false,true,false,false,false,true,false },
				{ true,true,false,true,true,true,true,true,true },
				{ true,false,false,false,true,false,false,true,false },
				{ false,true,true,false,true,false,false,false,false },
				{ false,true,true,true,true,true,true,true,true },
				{ false,true,true,true,true,false,true,true,false }
				};
		
		Board board = new Board();
		board.grid = grid;
		board.editable = editable;
		board.displayedCells = 38;
		
		return board;
		
	}
	
	public static Board getMediumBoard() {
			
			int[][] grid = { 
					{ 0,0,0,7,2,0,3,1,9 },
					{ 3,0,0,5,0,0,0,0,0 },
					{ 1,0,0,0,8,6,4,0,0 },
					{ 0,0,5,0,0,0,0,0,0 },
					{ 7,3,0,0,0,5,2,0,0 },
					{ 0,1,9,0,0,0,0,0,0 },
					{ 0,0,0,0,7,8,5,0,3 },
					{ 0,5,1,9,0,3,0,0,7 },
					{ 0,0,0,0,5,0,0,9,8 }					
					};
			
			boolean[][] editable = { 
					{ true,true,true,false,false,true,false,false,false, },
					{ false,true,true,false,true,true,true,true,true, },
					{ false,true,true,true,false,false,false,true,true, },
					{ true,true,false,true,true,true,true,true,true, },
					{ false,false,true,true,true,false,false,true,true, },
					{ true,false,false,true,true,true,true,true,true, },
					{ true,true,true,true,false,false,false,true,false, },
					{ true,false,false,false,true,false,true,true,false, },
					{ true,true,true,true,false,true,true,false,false, }					
					};
			
			Board board = new Board();
			board.grid = grid;
			board.editable = editable;
			board.displayedCells = 30;
			
			return board;
			
	}

	public static Board getHardBoard() {
		
		int[][] grid = { 
				{ 8,0,0,0,0,9,0,0,5 },
				{ 0,4,0,0,0,0,7,2,6 },
				{ 0,0,0,0,3,0,8,0,0 },
				{ 0,0,0,0,4,7,0,0,0 },
				{ 0,3,0,0,5,0,0,0,0 },
				{ 0,0,4,0,9,0,5,3,0 },
				{ 0,0,0,0,7,0,0,0,0 },
				{ 9,1,0,0,2,0,0,8,0 },
				{ 0,2,0,0,0,5,9,0,0 }					
				};
		
		boolean[][] editable = { 
				{ false,true,true,true,true,false,true,true,false, },
				{ true,false,true,true,true,true,false,false,false, },
				{ true,true,true,true,false,true,false,true,true, },
				{ true,true,true,true,false,false,true,true,true, },
				{ true,false,true,true,false,true,true,true,true, },
				{ true,true,false,true,false,true,false,false,true, },
				{ true,true,true,true,false,true,true,true,true, },
				{ false,false,true,true,false,true,true,false,true, },
				{ true,false,true,true,true,false,false,true,true, }					
				};
		
		Board board = new Board();
		board.grid = grid;
		board.editable = editable;
		board.displayedCells = 25;
		
		return board;
		
	}
	
public static Board getHardBoard2() {
		
		int[][] grid = { 
				{ 0,4,0,0,8,0,0,2,0 },
				{ 6,0,0,0,0,4,8,0,1 },
				{ 0,0,0,6,0,5,4,0,0 },
				{ 3,0,0,0,0,0,0,0,0 },
				{ 0,8,0,0,0,0,3,4,2 },
				{ 0,5,0,0,6,8,0,0,0 },
				{ 9,0,4,2,0,0,6,0,0 },
				{ 0,6,0,0,0,9,7,0,4 },
				{ 0,0,0,0,7,6,0,0,0 },
				};
		
		boolean[][] editable = { 
				{ true,false,true,true,false,true,true,false,true },
				{ false,true,true,true,true,false,false,true,false },
				{ true,true,true,false,true,false,false,true,true },
				{ false,true,true,true,true,true,true,true,true },
				{ true,false,true,true,true,true,false,false,false },
				{ true,false,true,true,false,false,true,true,true },
				{ false,true,false,false,true,true,false,true,true },
				{ true,false,true,true,true,false,false,true,false },
				{ true,true,true,true,false,false,true,true,true }
				};
		
		Board board = new Board();
		board.grid = grid;
		board.editable = editable;
		board.displayedCells = 28;
		
		return board;
		
	}



}
