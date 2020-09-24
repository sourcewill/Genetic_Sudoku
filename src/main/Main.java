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
			System.out.println("Gen: " + genetic.generation + " | Best conflicts: " + genetic.bestSolution.conflicts);
			genetic.rouletteSelection();
			genetic.newGeneration();
			
		}
		
		genetic.bestSolution.printBoard();
	}

}
