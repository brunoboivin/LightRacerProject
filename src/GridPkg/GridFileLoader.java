package GridPkg;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader; 
import java.io.InputStream;

public class GridFileLoader {
	
	public static void main (String[] args) throws IOException{
		GridFileLoader test = new GridFileLoader();
		test.readFile();
		
	}
	
	public static void readFile () throws IOException {
		
		String mapFilePath = "maps/test.txt";
		
		BufferedReader fileReader = new BufferedReader(new FileReader(mapFilePath));
	    try {
	    	
	        StringBuilder stringBuilder = new StringBuilder();
	        String line = fileReader.readLine();

	        while (line != null) {
	        	System.out.println(stringBuilder.append(line));
//	        	stringBuilder.append('\n');
	            line = fileReader.readLine();
	        }
	        String everything = stringBuilder.toString();
	    } finally {
	    	fileReader.close();
	    }
	
	}

}
