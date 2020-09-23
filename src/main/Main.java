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
		genetic.PopulationFitness();
		for(Board b : genetic.getPopulation()) {
			b.printBoard();
			System.out.println(b.fitness);
			System.out.println(b.conflicts);
		}
	}

}
