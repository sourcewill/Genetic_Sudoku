package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Arguments {
	
	public static void readArguments(String filePath) throws IOException {
		
		FileInputStream stream = new FileInputStream(filePath);
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader br = new BufferedReader(reader);
        
        String line = br.readLine();
        while(line != null) {
        	String[] lineSplit = line.split(" ");
        	
        	switch(lineSplit[0]) {
        		case "DIFFICULTY_LEVEL":
        			DIFFICULTY_LEVEL = Integer.parseInt(lineSplit[1]);
        			break;
        		case "POPULATION_SIZE":
        			POPULATION_SIZE = Integer.parseInt(lineSplit[1]);
        			break;
        		case "SELECTED_POPULATION_SIZE":
        			SELECTED_POPULATION_SIZE = Integer.parseInt(lineSplit[1]);
        			break;
        		case "ELITE_POPULATION":
        			ELITE_POPULATION = Integer.parseInt(lineSplit[1]);
        			break;
        		case "NUMBER_GENERATIONS":
        			NUMBER_GENERATIONS = Integer.parseInt(lineSplit[1]);
        			break;
        		case "MUTATION_RATE":
        			MUTATION_RATE = Integer.parseInt(lineSplit[1]);
        			break;
        		case "MUTATION_SIZE":
        			MUTATION_SIZE = Integer.parseInt(lineSplit[1]);
        			break;
        		default:
        			System.out.println("[Error] Invalid argument.");
        			System.exit(-1);
        	}
            line = br.readLine();
        }
	}
	
	public static int DIFFICULTY_LEVEL; // 1 = EASY | 2 = MEDIUM | 3 = HARD
	public static int POPULATION_SIZE;	
	public static int SELECTED_POPULATION_SIZE;
	public static int ELITE_POPULATION;
	public static int NUMBER_GENERATIONS;
	public static int MUTATION_RATE; // Between 0 and 100
	public static int MUTATION_SIZE; // Number of cells altered in the mutation

}
