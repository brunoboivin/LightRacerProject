package GridPkg;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class GridFileLoader {
	
//	public static void main (String[] args) throws IOException{
//		GridFileLoader test = new GridFileLoader();
//		test.readFile();
//	}
	
	public static String readFile ( String filePath ) throws IOException {
		
//		e.g. "maps/map2.txt"
		String gridFilePath = filePath; 
		
		BufferedReader fileReader = new BufferedReader(new FileReader(gridFilePath));
	    try {
	    	
	        StringBuilder stringBuilder = new StringBuilder();
	        String line = fileReader.readLine();

	        while (line != null) {
	        	stringBuilder.append(line);
	            line = fileReader.readLine();
	        }
	        String everything = stringBuilder.toString();
      		return everything;
	    } finally {
	    	fileReader.close();
	    }
	
	}

}
