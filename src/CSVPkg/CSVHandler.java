package CSVPkg;

import java.io.*;

/** Class that handles operations on CSV files.
 * 
 * @author Bruno Boivin <bruno.boivin@mail.mcgill.ca>
 * @version 1.0
 * @since 2013-11-08
 */
public class CSVHandler {
	
	/**
	 * Method that writes serialized objects to a CSV file.
	 * @param object to be written to the file
	 * @param path to the file
	 */
	public static void write (Object object, String path){
		try{
			FileOutputStream fileOut = new FileOutputStream (path);
			ObjectOutputStream out = new ObjectOutputStream (fileOut);
			
			out.writeObject(object);
			out.close();
			fileOut.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Method that reads serialized objects from a CSV file.
	 * @param path to the file
	 * @return object read from CSV file
	 */
	public static Object read (String path){
		
		Object object = null;
		
		try{
			FileInputStream fileIn = new FileInputStream (path);
			ObjectInputStream in = new ObjectInputStream (fileIn);
			
			object = in.readObject();
			
			in.close();
			fileIn.close();
		}
		catch (IOException e){
			e.printStackTrace();
			
			/*Some exceptions that will be caught here:
			 * 1) java.io.FileNotFoundException
			 * 2) java.io.EOFException
			 */	
		}
		catch (ClassNotFoundException c){
			c.printStackTrace();
		}
		
		if (object != null)
			return object;
		else
			throw new NullPointerException();
	}
}
