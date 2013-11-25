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
	
		// Declare and initialize a String containing all digits
		String numbers = "0123456789";
		
		// Declare and initialize a String containing all allowable special characters
		String specialChars = "~!@#$%^&*()_+=-.,<>?{}[];";
		
		boolean hasUpperCase = false, hasLowerCase = false, hasDigit = false, hasNonAlphanumeric = false, hasProperLength = false;
		
		// First check, if length requirements are met
		if (password.length() >= 8) {
			hasProperLength = true;
		}
		
		// Second check, if the password contains at least one digit
		if (StringUtils.containsAny(password, numbers)) {
			hasDigit = true;
		}
		
		// Third check, if the password contains at least one special character
		if (StringUtils.containsAny(password, specialChars)) {
			hasNonAlphanumeric = true;
		}
		
		// Fourth check, if the password contains at least one upper case and at least one lower case letter
		if ((!(StringUtils.upperCase(password)).equals(password)) && (!(StringUtils.lowerCase(password)).equals(password))) {
			hasUpperCase = true;
			hasLowerCase = true;
		}
		
		// If all checks pass, function will return 'true'
		return (hasUpperCase && hasLowerCase && hasDigit && hasNonAlphanumeric && hasProperLength);
	}
	
	/**
	 * Logically logs in the user that has entered his/her credentials.
	 * 
	 * @param userNumber integer used to identify which one of the two players to instantiate (i.e. left or right)
	 * 		  username the username of the user attempting to log in
	 *        password the password of the user attempting to log in
	 * @return number representing the result of the login (success; wrong password; username not found; user already logged in)
	 */
	public static UserLoginStatus login(int userNumber, String username, String password) {

		// Read the list of registered users from the user_data.csv file
		ArrayList<User> users = new ArrayList<User>();
		users = (ArrayList<User>) CSVHandler.read("csv/user_data.csv");
		
		/* Iterate through the list of users to check if the username-password combination
		 * entered by the user exists 
		 */
		for (User user : users) {
			if ((user.getUsername()).equals(username)) {
				if ((user.getPassword()).equals(password)) {
					if (userNumber == 1) {
						
						// Check to see if the user is already logged in
						if ((user2 != null) && ((user2.getUsername())).equals(username)) {
							return UserLoginStatus.UserLoggedIn; 
						}
						// If this part is reached, a new user can be created
						user1 = new User(username, password);
					}
					// Same check is done as above in case user 2 is trying to log in
					if (userNumber == 2) {
						if ((user1 != null) && ((user1.getUsername())).equals(username)) {
							return UserLoginStatus.UserLoggedIn;
						}
						user2 = new User(username, password);					
					}
					// Return success at this point
					return UserLoginStatus.Success;
				// Return wrong password status
				} else {
					return UserLoginStatus.WrongPassword; 
				}
			}
		}
		/* If this point is reached, it means that the username was not found
		 * in the list of registered users, therefore the appropriate username not
		 * found status is returned 
		 */
		return UserLoginStatus.UsernameNotFound; 
	}

	/**
	 * Logically logs out a user
	 * 
	 * @param user the user that has clicked on the "Logout" button in the GUI
	 */
	public static void logout (User user) {
		// User1 not being null signals a logged in user, who, therefore can be logged out
		if ((user1 != null) && (user1.equals(user))) {
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
	 * @return registration status representing the result of the register (success; username taken; password requirements not met; error in creating user)
	 */
	public static UserRegistrationStatus registerUser(String username, String password) {
		
		/* The list of registered users is first read from the user_data.csv file
		 * in order to ensure that the username is not taken
		 */
		ArrayList<User> users = new ArrayList<User>();
		users = (ArrayList<User>) CSVHandler.read("csv/user_data.csv");
		
		/* If, when iterating through the list of existing users, the username introduced in the GUI is found,
		 * return the appropriate UsernameTaken status 
		 */
		for (User user : users) {
			if ((user.getUsername()).equals(username)) {
				return UserRegistrationStatus.UsernameTaken;
			}
		}
		// If the password provided does not meet requirements, return appropriate BadPassword status
		if (!checkPasswordRequirements(password)) {
			return UserRegistrationStatus.BadPassword; 
		}
		
		/* If both checks above pass, create the user with the introduced username and password
		 * and add him/her to the previously read list
		 */
		User newUser = new User(username, password);
		users.add(newUser);

		// Write the array list back into the user_data.csv file
		CSVHandler.write(users, "csv/user_data.csv");
		
		// Check if user was added correctly
		users = null;
		users = (ArrayList<User>) CSVHandler.read("csv/user_data.csv");
		for (User user : users) {
			if ((user.getUsername()).equals(username)) {
				return UserRegistrationStatus.Success; 
			}
		}
		
		/* If this point is reached it means that the recently created user was not found
		 * in the user_data.csv file
		 */
		return UserRegistrationStatus.FileError;
	}
}