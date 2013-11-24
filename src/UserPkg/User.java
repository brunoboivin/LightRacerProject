package UserPkg;

import java.io.Serializable;

public class User implements Serializable
{
	public String username;
	public String password;
	public boolean loggedIn;
	
	public User(String username,String password)
	{
		this.username = username;
		this.password = password;
	}
	
	public void logIn (User user) {
		user.loggedIn = true;
	}
	
	public void logOut (User user) {
		user.loggedIn = false;
	}

	public boolean isLoggedIn(User user) {
		return user.loggedIn;
	}
	
	public String getUsername() 
	{
		return username;
	}

	public String getPassword() 
	{
		return password;
	}



}
