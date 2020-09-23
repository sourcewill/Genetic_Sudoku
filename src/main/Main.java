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
		genetic.populationFitness();
		genetic.populattionProbability();
		genetic.rouletteSelection();
		
		
		for(Board b : genetic.selectedPopulation) {
			b.printBoard();
			System.out.println("FIT: " + b.fitness);
			System.out.println("Conflicts: " + b.conflicts);
			System.out.println(b.rouletteStart);
			System.out.println(b.rouletteEnd);
		}
		
	}

}
