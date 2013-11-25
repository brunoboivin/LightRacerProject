package UserPkg;

import java.io.Serializable;

/** Class used for implementing a game user.
 * 
 * @author Anita Szilagyi <anita.szilagyi@mail.mcgill.ca>
 * @version 1.0
 * @since 2013-11-08
 */
public class User implements Serializable
{
	public String username;
	public String password;
	
	// Constructor
	/**
	 * Constructor
	 * @param username username of user
	 * @param password password of user
	 */
	public User(String username,String password)
	{
		this.username = username;
		this.password = password;
	}

	// Getters
	/**
	 * @return username the username of the player
	 */
	public String getUsername() 
	{
		return username;
	}

	/**
	 * @return password the password of the player
	 */
	public String getPassword() 
	{
		return password;
	}
}

