package genetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import game.Board;
import game.Game;

public class Genetic {

	private List<Board> population = new ArrayList<>();
	private Board boardToSolve;

	public Genetic(Board boardToSolve) {
		this.boardToSolve = boardToSolve;
	}

	public List<Board> getPopulation() {
		return population;
	}

	public void initializePopulation() {

		for (int i = 0; i < util.Arguments.POPULATION_SIZE; i++) {
			Board newBoard = initializeRandomBoard();
			population.add(newBoard);
		}
		
	}

	private Board initializeRandomBoard() {

		Random randomGenerator = new Random();
		Board newBoard = new Board();
		
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				newBoard.grid[i][j] = boardToSolve.grid[i][j];
			}
		}		
		newBoard.editable = boardToSolve.editable;
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (newBoard.editable[i][j]) {
					int random = randomGenerator.nextInt(9) + 1;
					newBoard.grid[i][j] = random;
				}
			}
		}
		return newBoard;
	}

	public void PopulationFitness() {
		for (Board board : population) {
			board.fitness = calculateFitness(board);
		}
	}

	public Double calculateFitness(Board board) {
		int conflicts = Game.verifyBoardConflicts(board);
		board.conflicts = conflicts;
		return (double) (1 - (conflicts / util.Arguments.LIMIT_CONFLICTS_FITNESS));
	}

}
