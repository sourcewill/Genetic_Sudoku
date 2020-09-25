package main;

import game.Board;
import game.Game;
import genetic.Genetic;

public class Main {

	public static void main(String[] args) {

		Board board = Game.generateBoard();
		System.out.println("INITIAL BOARD TO SOLVE:");
		board.printBoard();
		
		Genetic genetic = new Genetic(board);
		genetic.initializePopulation();
		
		while(genetic.generation < util.Arguments.NUMBER_GENERATIONS) {			
			genetic.populationFitness();
			genetic.populattionProbability();
			
			System.out.println("Generation: " + genetic.generation 
					+ "\nBest fitness: " + genetic.bestSolution.fitness 
					+ "\nFitness average: " + genetic.calculatePopulationFitnessAverage()
					+ "\nConflicts: " + genetic.bestSolution.conflicts + "\n");
			
			if(genetic.bestSolution.conflicts == 0) {
				System.out.println("SOLVED!");
				break;
			}
			genetic.rouletteSelection();
			genetic.newGeneration();
			
		}
		
		System.out.println("BEST SOLUTION");
		genetic.bestSolution.printBoard();
	}

}
