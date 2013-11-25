package GridPkg;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class GridFileLoader {
	
//	public static void main (String[] args) throws IOException{
//		GridFileLoader test = new GridFileLoader();
//		test.readFile();
//	}
	
	//Takes a map file path. Returns the data in the form of an array of int coordinates.
	public static int [] readFile ( String mapPath ) throws IOException {
		
		//e.g. "maps/map2.txt"		
		String gridFilePath = mapPath; 
		
		BufferedReader fileReader = new BufferedReader(new FileReader(gridFilePath));
	    try {
	    	
	        StringBuilder stringBuilder = new StringBuilder();
	        String line = fileReader.readLine();

	        while (line != null) {
	        	stringBuilder.append(line);
	            line = fileReader.readLine();
	        }
	        String dataFromFile = stringBuilder.toString();
	        return GridFileLoader.convertDataToCoords (dataFromFile);
	    } finally {
	    	fileReader.close();
	    }
	
	}
	
	//Converts raw data from a file into an array of int coordinates
	private static int [] convertDataToCoords ( String dataFromFile ) {
	
		String[] coordsFromFile;
		int obstacleCoords[];

		//Raw data from file is broken up into separate coordinates and moved into array
		dataFromFile = dataFromFile.replace("(", "");
		coordsFromFile = dataFromFile.split("(,)|(\\))");
		
		//Converts each String coordinate into an int coordinate
		obstacleCoords = new int[coordsFromFile.length];
		for (int i = 0; i < coordsFromFile.length; ++i) {
			obstacleCoords[i] = Integer.parseInt(coordsFromFile[i].trim());
		}
		
		return obstacleCoords;
	}	

}
