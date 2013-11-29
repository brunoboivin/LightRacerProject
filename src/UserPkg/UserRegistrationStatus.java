package UserPkg;

/**
 * Enumeration for the status of the user's registration attempt.
 * 
 * @authors Anita Szilagyi, Bruno Boivin, Kaichen Wang, Salman Hashmi, Shahrzad Ti
 * @version 1.0
 * @since 2013-11-08
 */
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
