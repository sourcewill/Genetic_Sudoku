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
	public List<Board> selectedPopulation = new ArrayList<>();
	public List<Board> elitePopulation = new ArrayList<>();
	private Board boardToSolve;
	private Double populationFitnessSum = 0.0;
	public int generation = 1;

	public Genetic(Board boardToSolve) {
		this.boardToSolve = boardToSolve;
	}

	public void initializePopulation() {

		for (int i = 0; i < util.Arguments.POPULATION_SIZE - 1; i++) {
			Board newBoard = initializeRandomBoard();
			population.add(newBoard);
		}
		Board test = new Board();
		test.grid = util.CompletedGrids.getCompletedGrid();
		population.add(test);

	}

	private Board initializeRandomBoard() {

		Random randomGenerator = new Random();
		Board newBoard = new Board();

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
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

	public void populationFitness() {
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
		return (double) (1 - (conflicts / util.Arguments.LIMIT_CONFLICTS_FITNESS));
	}

	private Double calculateProbability(Board board) {
		return board.fitness / populationFitnessSum;
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
			set.add(spinRoulette());
		}
		selectedPopulation = new ArrayList<Board>();
		selectedPopulation.addAll(set);
	}

	public Board crossover(Board board1, Board board2) {

		Random randomGenerator = new Random();
		int lineOrColumn, cut;

		lineOrColumn = randomGenerator.nextInt(2); // Cut in line or cut in column
		cut = randomGenerator.nextInt(8) + 1; // Random between 1 and 8
		Board board = new Board();
		board.editable = board1.editable;

		System.out.println(lineOrColumn + "--> Cut: " + cut);

		switch (lineOrColumn) {

		case 0:
			for (int i = 0; i < cut; i++) {
				for (int j = 0; j < 9; j++) {
					board.grid[i][j] = board1.grid[i][j];
				}
			}
			for (int i = cut; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					board.grid[i][j] = board2.grid[i][j];
				}
			}
			break;

		case 1:
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < cut; j++) {
					board.grid[i][j] = board1.grid[i][j];
				}
			}
			for (int i = 0; i < 9; i++) {
				for (int j = cut; j < 9; j++) {
					board.grid[i][j] = board2.grid[i][j];
				}
			}
			break;
		}

		return board;
	}

	public Board mutation(Board board) {

		Random randomGenerator = new Random();
		int i, j, number;

		i = randomGenerator.nextInt(9);
		j = randomGenerator.nextInt(9);
		number = randomGenerator.nextInt(9) + 1;

		board.grid[i][j] = number;
		return board;
	}

	public void newGeneration() {

		this.generation++;
		this.population = new ArrayList<Board>();
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
				mutation(board);
			}
			population.add(board);
		}
	}

}
