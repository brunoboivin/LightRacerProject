package UserPkg;

/**
 * Enumeration for the status of the user's login attempt.
 * 
 * @author Anita Szilagyi <anita.szilagyi@mail.mcgill.ca>
 * @version 1.0
 * @since 2013-11-08
 */
public enum UserLoginStatus {

	/**
	 * Successful login.
	 */
	Success,

	/**
	 * Unsuccessful login: entered password not matched with entered username.
	 */
	WrongPassword,

	/**
	 * Unsuccessful login: username not found in the user_data.csv file.
	 */
	UsernameNotFound,

	/**
	 * Unsuccessful login: there is a user already logged in with entered
	 * username.
	 */
	UserLoggedIn

}
