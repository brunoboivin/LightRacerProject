package UserPkg;

import java.io.Serializable;

public class User implements Serializable
{
	public String username;
	public String password;
	
	public User(String username,String password)
	{
		this.username = username;
		this.password = password;
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
