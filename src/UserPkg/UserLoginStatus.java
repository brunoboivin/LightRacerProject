package UserPkg;

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
	 * Unsuccessful login: there is a user already logged in with entered username.
	 */
	UserLoggedIn

}
