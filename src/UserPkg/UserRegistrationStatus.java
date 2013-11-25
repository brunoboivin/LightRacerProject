package UserPkg;

public enum UserRegistrationStatus {
	
	/**
	 * Successful registration.
	 */
	Success,
	
	/**
	 * Unsuccessful registration: username taken.
	 */
	UsernameTaken,
	
	/**
	 * Unsuccessful registration: password does not meet requirements.
	 */
	BadPassword,
	
	/**
	 * Error writing/reading to/from the user_data.csv file.
	 */
	FileError

}
