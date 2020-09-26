package main;

import game.Board;
import game.Game;
import genetic.Genetic;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {

		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		stage.setTitle("Sudoku - Genetic Algorithm");

		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Generation");
		yAxis.setLabel("Fitness");

		final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);

		lineChart.setTitle("Generation x Fitness");

		XYChart.Series bestFitnessLine = new XYChart.Series();
		bestFitnessLine.setName("Best Fitness");
		XYChart.Series averagrFitnessLine = new XYChart.Series();
		averagrFitnessLine.setName("Average fitness");

		Board board = Game.generateBoard();
		System.out.println("INITIAL BOARD TO SOLVE:");
		board.printBoard();

		Genetic genetic = new Genetic(board);
		genetic.initializePopulation();

		while (genetic.generation < util.Arguments.NUMBER_GENERATIONS) {
			genetic.populationFitness();
			genetic.populattionProbability();

			System.out.println("Generation: " + genetic.generation + "\nBest fitness: " + genetic.bestSolution.fitness
					+ "\nFitness average: " + genetic.calculatePopulationFitnessAverage() + "\nConflicts: "
					+ genetic.bestSolution.conflicts + "\n");

			bestFitnessLine.getData().add(new XYChart.Data(genetic.generation, genetic.bestSolution.fitness));
			averagrFitnessLine.getData()
					.add(new XYChart.Data(genetic.generation, genetic.calculatePopulationFitnessAverage()));

			if (genetic.bestSolution.conflicts == 0) {
				System.out.println("SOLVED!");
				break;
			}
			genetic.rouletteSelection();
			genetic.newGeneration();

		}

		System.out.println("BEST SOLUTION");
		genetic.bestSolution.printBoard();

		Scene scene = new Scene(lineChart, 800, 600);
		lineChart.getData().addAll(bestFitnessLine, averagrFitnessLine);

		stage.setScene(scene);
		stage.show();

	}

}
