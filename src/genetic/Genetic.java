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
	private Board boardToSolve;
	private Double populationFitnessSum = 0.0;

	public Genetic(Board boardToSolve) {
		this.boardToSolve = boardToSolve;
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
	}

	public Double calculateFitness(Board board) {
		int conflicts = Game.verifyBoardConflicts(board);
		board.conflicts = conflicts;
		return (double) (1 - (conflicts / util.Arguments.LIMIT_CONFLICTS_FITNESS));
	}

	public Double calculateProbability(Board board) {
		Double probability;
		probability = (100.0 * board.fitness / populationFitnessSum) / 100.0;
		return probability;
	}

	public void populattionProbability() {
		Double probability = 0.0;
		for (Board board : population) {
			board.rouletteStart = probability;
			probability += calculateProbability(board);
			board.rouletteEnd = probability;
		}
	}

	public Board spinRoulette() {

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

		Collections.sort(population); // Orders the population based on fitness
		for (int i = 0; i < util.Arguments.ELITE_POPULATION; i++) {
			set.add(population.get(i));
		}

		while (set.size() < util.Arguments.SELECTED_POPULATION) {
			set.add(spinRoulette());
		}
		selectedPopulation = new ArrayList<Board>();
		selectedPopulation.addAll(set);
	}

}
