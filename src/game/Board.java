package game;

public class Board {
	
	public Double fitness = 0.0;
	
	public int conflicts = 0;

	public int[][] grid = { 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }
			};
	
	public boolean[][] editable = {
			{true, true, true, true, true, true, true, true, true},
			{true, true, true, true, true, true, true, true, true},
			{true, true, true, true, true, true, true, true, true},
			{true, true, true, true, true, true, true, true, true},
			{true, true, true, true, true, true, true, true, true},
			{true, true, true, true, true, true, true, true, true},
			{true, true, true, true, true, true, true, true, true},
			{true, true, true, true, true, true, true, true, true},
			{true, true, true, true, true, true, true, true, true}
	};
	
	


	public void printBoard() {
		System.out.println();
		System.out.println(" ------+-------+------");
		for (int i = 0; i < 9; i++) {
			if(i == 3 || i == 6 ) {
				System.out.println(" ------+-------+------");
			}
			for (int j = 0; j < 9; j++) {
				if(j == 3 || j == 6 ) {
					System.out.print(" |");
				}
				System.out.print(" " + grid[i][j]);
			}
			System.out.println();
		}
		System.out.println(" ------+-------+------");
		System.out.println();
	}
	
	

}
