package genetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import game.Board;
import game.Game;

public class Genetic {

	public List<Board> population = new ArrayList<>();
	public List<Board> selectedPopulation;
	public List<Board> elitePopulation = new ArrayList<>();
	private Board boardToSolve;
	private Double populationFitnessSum = 0.0;
	public int generation = 1;

	public Board bestSolution = new Board();

	public Genetic(Board boardToSolve) {
		this.boardToSolve = boardToSolve;
	}

	public void initializePopulation() {

		for (int i = 0; i < util.Arguments.POPULATION_SIZE; i++) {
			Board newBoard = initializeRandomBoard();
			population.add(newBoard);
		}

		bestSolution.conflicts = Integer.MAX_VALUE;

	}

	private Board initializeRandomBoard() {

		Random randomGenerator = new Random();
		Board newBoard = new Board();

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				newBoard.grid[i][j] = boardToSolve.grid[i][j];
			}
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				newBoard.editable[i][j] = boardToSolve.editable[i][j];
			}
		}

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

	public void populationFitness() {
		populationFitnessSum = 0.0;
		for (Board board : population) {

			Double fitness = calculateFitness(board);
			board.fitness = fitness;
			populationFitnessSum += fitness;
		}
		Collections.sort(population); // Orders the population based on fitness
		elitePopulation = new ArrayList<Board>();
		for (int i = 0; i < util.Arguments.ELITE_POPULATION; i++) {
			elitePopulation.add(population.get(i));
		}
	}

	private Double calculateFitness(Board board) {
		int conflicts = Game.verifyBoardConflicts(board);
		board.conflicts = conflicts;
		if (conflicts < bestSolution.conflicts) {
			bestSolution = board;
		}
		return (double) (1.0 - (conflicts /  ((81.0 - boardToSolve.displayedCells) * 3.0)) );
	}

	private Double calculateProbability(Board board) {
		return board.fitness / populationFitnessSum;
	}

	public Double calculatePopulationFitnessAverage() {
		return populationFitnessSum / (double) util.Arguments.POPULATION_SIZE;
	}

	public void populattionProbability() {
		Double probability = 0.0;
		for (Board board : population) {
			board.rouletteStart = probability;
			probability += calculateProbability(board);
			board.rouletteEnd = probability;
		}
	}

	private Board spinRoulette() {

		Random randomGenerator = new Random();
		Double random = randomGenerator.nextDouble();
		for (Board board : population) {
			if ((random >= board.rouletteStart) && (random < board.rouletteEnd)) {
				return board;
			}
		}
		return null;
	}

	public void rouletteSelection() {

		Set<Board> set = new HashSet<Board>();
		set.addAll(elitePopulation);

		while (set.size() < util.Arguments.SELECTED_POPULATION_SIZE) {
			Board board = spinRoulette();
			if (board != null) {
				set.add(board);
			}
		}
		selectedPopulation = new ArrayList<Board>();
		selectedPopulation.addAll(set);
	}

	public Board crossover(Board board1, Board board2) {

		Random randomGenerator = new Random();
		int cutType, cutPosition;

		cutType = randomGenerator.nextInt(2); // Cut in line or cut in column
		cutPosition = randomGenerator.nextInt(8) + 1; // Random between 1 and 8
		Board board = new Board();
		
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				board.editable[i][j] = boardToSolve.editable[i][j];
			}
		}

		switch (cutType) {

		case 0:
			for (int i = 0; i < cutPosition; i++) {
				for (int j = 0; j < 9; j++) {
					board.grid[i][j] = board1.grid[i][j];
				}
			}
			for (int i = cutPosition; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					board.grid[i][j] = board2.grid[i][j];
				}
			}
			break;

		case 1:
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < cutPosition; j++) {
					board.grid[i][j] = board1.grid[i][j];
				}
			}
			for (int i = 0; i < 9; i++) {
				for (int j = cutPosition; j < 9; j++) {
					board.grid[i][j] = board2.grid[i][j];
				}
			}
			break;
		}

		return board;
	}

	public boolean mutation(Board board) {

		Random randomGenerator = new Random();
		int i, j, number;

		i = randomGenerator.nextInt(9);
		j = randomGenerator.nextInt(9);
		number = randomGenerator.nextInt(9) + 1;

		if (board.editable[i][j]) {
			board.grid[i][j] = number;
			return true;
		}
		return false;
	}

	public void newGeneration() {

		this.generation++;
		this.population = new ArrayList<Board>();
		
		//population.addAll(elitePopulation);
		
		Random randomGenerator = new Random();

		while (population.size() < util.Arguments.POPULATION_SIZE) {

			int i = randomGenerator.nextInt(util.Arguments.SELECTED_POPULATION_SIZE);
			int j = randomGenerator.nextInt(util.Arguments.SELECTED_POPULATION_SIZE);
			if (i == j) {
				continue;
			}

			Board board = crossover(selectedPopulation.get(i), selectedPopulation.get(j));

			Double mutation = randomGenerator.nextDouble();
			if (mutation <= util.Arguments.MUTATION_RATE / 100) {
				int mutationCounter = 0;
				while(mutationCounter < util.Arguments.MUTATION_SIZE){
					if(mutation(board)) {
						mutationCounter++;
					}
				}
			}

			population.add(board);
		}
	}

	public void printPopulation() {
		for (Board board : population) {
			board.printBoard();
			System.out.println(board.conflicts);
			System.out.println(board.fitness);
		}
	}

	public void printSelectedPopulation() {
		for (Board board : selectedPopulation) {
			board.printBoard();
			System.out.println(board.conflicts);
			System.out.println(board.fitness);
		}
	}

}
