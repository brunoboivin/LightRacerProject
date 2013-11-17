package UserPkg;

import java.io.IOException;
import java.util.ArrayList;

import RacerPkg.*;
import StatisticsPkg.PairRecord;
import CSVPkg.CSVHandler;
import GameControllerPkg.UIController;
import GridPkg.*;

public class UserManagement 
{

	//  public myUIController;

	public boolean login( String username, String password) 
	{
		User newUser = new User(username, password);

		ArrayList<User> users = new ArrayList<User>();
		try {
			users = (ArrayList<User>) CSVHandler.read("csv/user_data.csv");
		} catch (IOException e) {
			return false;
		}
		//users.add(newUser);
		for (User user : users) {
			if ((user.getUsername()).equals(username)) {
				if (user.getPassword().equals(password)) {
					UIController.setUser(newUser);
				}
			}
		}
		return true;
	}

	public void logout( String username) 
	{
	}

	public boolean registerUser() 
	{
		// read the csv to get the array list
		User newUser = new User(username, password);
		ArrayList<User> users = new ArrayList<User>();
		// update that

		// write the array list back

		return false;
	}
}