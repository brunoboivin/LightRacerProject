package UserPkg;

import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
import CSVPkg.CSVHandler;

/** Class used for managing user accounts.
 * 
 * @author Anita Szilagyi <anita.szilagyi@mail.mcgill.ca>
 * @version 1.0
 * @since 2013-11-08
 */
public class UserManagement 
{
	public static User user1;
	public static User user2;
	
	/**
	 * Checks if the password introduced by a user attempting
	 * to register respects the requirements.
	 * 
	 * @param password the password to be checked
	 * @return boolean the result of the password check (true = password meets requirements; false = password does not meet requirements)
	 */
	public static boolean checkPasswordRequirements(String password) {
	
		String numbers = "0123456789";
		String specialChars = "~!@#$%^&*()_+=-.,<>?{}[];";
		boolean hasUpperCase = false, hasLowerCase = false, hasDigit = false, hasNonAlphanumeric = false, hasProperLength = false;
		if (password.length() >= 8) {
			hasProperLength = true;
		}
		if (StringUtils.containsAny(password, numbers)) {
			hasDigit = true;
		}
		if (StringUtils.containsAny(password, specialChars)) {
			hasNonAlphanumeric = true;
		}
		
		if ((!(StringUtils.upperCase(password)).equals(password)) && (!(StringUtils.lowerCase(password)).equals(password))) {
			hasUpperCase = true;
			hasLowerCase = true;
		}
		return (hasUpperCase && hasLowerCase && hasDigit && hasNonAlphanumeric && hasProperLength);
	}
	
	/**
	 * Logically logs in the user that has entered his/her credentials.
	 * 
	 * @param userNumber integer used to identify which one of the two players to instantiate (i.e. left or right)
	 * 		  username the username of the user attempting to log in
	 *        password the password of the user attempting to log in
	 * @return number representing the result of the login (0 = success; 1 = wrong password; 2 = username not found; 3 = user already logged in)
	 */
	public static int login(int userNumber, String username, String password) {

		ArrayList<User> users = new ArrayList<User>();
		users = (ArrayList<User>) CSVHandler.read("csv/user_data.csv");
		for (User user : users) {
			if ((user.getUsername()).equals(username)) {
				if ((user.getPassword()).equals(password)) {
					if (userNumber == 1) {
						if ((user1 != null) && ((user1.getUsername())).equals(username)) {
							return 3;
						}
						user1 = new User(username, password);
					}
					if (userNumber == 2) {
						if ((user2 != null) && ((user2.getUsername())).equals(username)) {
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

	/**
	 * Logically logs out a user
	 * 
	 * @param user the user that has clicked on the "Logout" button in the GUI
	 */
	public static void logout (User user) {
		if (user1 != null) {
			user1 = null;
		} else {
			user2 = null;
		}
	}

	/**
	 * Registers a user into the system
	 * 
	 * @param username the username of choice
	 * 		  password the password or the user
	 * @return number representing the result of the register (0 = success; 1 = username taken; 2 = password requirements not met; 3 = error in creating user)
	 */
	public static int registerUser(String username, String password) {
		
		// read the list first to make sure the username isn't taken
		ArrayList<User> users = new ArrayList<User>();
		users = (ArrayList<User>) CSVHandler.read("csv/user_data.csv");
		for (User user : users) {
			if ((user.getUsername()).equals(username)) {
				return 1; // username taken
			}
		}
		if (!checkPasswordRequirements(password)) {
			return 2; // password requirements do not match
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
				return 3; 
			}
		}
		return 0;
	}
}