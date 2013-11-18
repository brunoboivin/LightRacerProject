package UserPkg;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import CSVPkg.CSVHandler;
import GameControllerPkg.UIController;

public class UserManagement 
{
	public static User user1;
	public static User user2;
	
	public static int login(int userNumber, String username, String password) {

		ArrayList<User> users = new ArrayList<User>();
		users = (ArrayList<User>) CSVHandler.read("csv/user_data.csv");
		for (User user : users) {
			if ((user.getUsername()).equals(username)) {
				if ((user.getPassword()).equals(password)) {
					// Uncomment below when login is ready to be connected to the game
					/*
					UIController.setUser(newUser);
					if (((UIController.userA).equals(username)) || ((UIController.userB).equals(username))) {
						return true;
					}
					*/ 
					if (userNumber == 1) {
						if ((user2 != null) && ((user2.getUsername())).equals(username)) {
							return 3;
						}
						user1 = new User(username, password);
					}
					if (userNumber == 2) {
						if ((user1 != null) && ((user1.getUsername())).equals(username)) {
							return 3;
						}
						user2 = new User(username, password);					
					}
					return 0; //success
					
				} else {
					return 1; // wrong password
				}
			}
		}
		return 2; // username not found
	}

	public void logout (User user) {
//		if ((UIController.userA).equals(user)) {
//			UIController.userA = null;
//		} else {
//			UIController.userB = null;
//		}
	}

	public static int registerUser(String username, String password) {
		
		// read the list first to make sure the username isn't taken
		ArrayList<User> users = new ArrayList<User>();
		users = (ArrayList<User>) CSVHandler.read("csv/user_data.csv");
		for (User user : users) {
			if ((user.getUsername()).equals(username)) {
				return 1; // username taken
			}
		}
		
		User newUser = new User(username, password);
		users.add(newUser);

		// write the array list back
		CSVHandler.write(users, "csv/user_data.csv");
		
		// Check if user was added correctly
		users = null;
		users = (ArrayList<User>) CSVHandler.read("csv/user_data.csv");
		for (User user : users) {
			if ((user.getUsername()).equals(username)) {
				return 2;
			}
		}
		return 0;
	}
}