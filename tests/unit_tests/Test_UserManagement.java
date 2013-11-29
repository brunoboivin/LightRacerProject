package unit_tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import UserPkg.*;

public class Test_UserManagement {
	
	User loggedInUser;
	
	// Create some accounts before tests
	@Before
	public void setUp() {
		
		UserManagement.registerUser("Demo01", "Dem@Us3R01");
		UserManagement.registerUser("Demo02", "Dem@Us3R02");
		UserManagement.registerUser("Demo03", "Dem@Us3R03");
		UserManagement.registerUser("Demo04", "Dem@Us3R04");
		UserManagement.registerUser("Demo05", "Dem@Us3R05");
		
		// For logout
		UserManagement.registerUser("Demo06", "Dem@Us3R06");
		UserManagement.login(1, "Demo06", "Dem@Us3R06");
		loggedInUser = UserManagement.user1;
	}

	// Test of the password check when registering a user. This tests invalid passwords. Should fail
	@Test
	public void test_invalid_passwords() {
		
		ArrayList<String> passwords = new ArrayList<String>(
				Arrays.asList(
						"",
						" ", 
						"        ",
						".", 
						"a",
						"1",
						"12345678",
						"aaaAaaaAaa",
						"aA1.000"
						));
		
		for(String password : passwords){
			assertFalse(UserManagement.checkPasswordRequirements(password));
		}
	}

	// Test of password check when registering a user. This tests valid passwords. Should return Success
	@Test
	public void test_valid_passwords() {
		
		ArrayList<String> passwords = new ArrayList<String>(
				Arrays.asList(
						".....,.Aa9",
						"123.Aabb",
						"123]Bruno",
						"ABCDEGEGRRJEIabcdefghijklmnopqrstuvwxyz1234567890.,;;]/'.[/,"
						));
		
		for(String password : passwords){
			assertTrue(UserManagement.checkPasswordRequirements(password));
		}
	}
	
	// Test that users can register properly. Should return Success
	@Test
	public void test_successful_registration() {
		
		Random rand = new Random();

		int n = rand.nextInt(1000) + 1;
		
		UserRegistrationStatus user1RegStat = UserManagement.registerUser("test_user_" + Integer.toString(n), "aA12345!");
		n = rand.nextInt(1000) + 1;
		UserRegistrationStatus user2RegStat = UserManagement.registerUser("test_user_" + Integer.toString(n), "bB12345@");
		
		assertTrue((user1RegStat == UserRegistrationStatus.Success) && 
				(user2RegStat == UserRegistrationStatus.Success));
	}
	
	// Test that UsernameTaken condition is found. Should return UsernameTaken
	@ Test
	public void test_usernameTaken_registration() {
		
		UserRegistrationStatus regStat = UserManagement.registerUser("Demo01", "Dem@Us3R01");
		
		assertTrue(regStat == UserRegistrationStatus.UsernameTaken);
	}
	
	// Test that BadPassword condition is met.
	@Test
	public void test_badPassword_registration() {
		
		Random rand = new Random();

		int n = rand.nextInt(1000) + 1;
		
		UserRegistrationStatus regStat = UserManagement.registerUser(Integer.toString(n), "aaaaaaa1");
		
		assertTrue(regStat == UserRegistrationStatus.BadPassword);			
	}
	
	// Test that a user can log in properly
	@Test
	public void test_successful_login_1_user() {
		
		UserLoginStatus loginStat = UserManagement.login(1, "Demo01", "Dem@Us3R01");
		
		assertTrue(loginStat == UserLoginStatus.Success);
	}
	
	// Test that 2 users can log in properly
	@Test
	public void test_successful_login_2_users() {
		
		UserLoginStatus user1LoginStatus, user2LoginStatus;
		
		user1LoginStatus = UserManagement.login(1, "Demo02", "Dem@Us3R02");
		user2LoginStatus = UserManagement.login(2, "Demo03", "Dem@Us3R03");
		
		assertTrue((user1LoginStatus == UserLoginStatus.Success) &&
				(user2LoginStatus == UserLoginStatus.Success));
	}
	
	// Test that two users cannot log in with the same username (user 2)
	@Test
	public void test_username1_logged_in() {
		
		UserLoginStatus user1LoginStatus = UserManagement.login(1, "Demo04", "Dem@Us3R04");
		
		UserLoginStatus user2LoginStatus = UserManagement.login(2, "Demo04", "Dem@Us3R04");
		
		assertTrue((user1LoginStatus == UserLoginStatus.Success) && 
				(user2LoginStatus == UserLoginStatus.UserLoggedIn));
	}
	
	// Test that two users cannot log in with the same username (user 1)
	@Test
	public void test_username2_logged_in() {
		
		UserLoginStatus user2LoginStatus = UserManagement.login(2, "Demo05", "Dem@Us3R05");
		
		UserLoginStatus user1LoginStatus = UserManagement.login(1, "Demo05", "Dem@Us3R05");
		
		assertTrue((user1LoginStatus == UserLoginStatus.UserLoggedIn) && 
				(user2LoginStatus == UserLoginStatus.Success));
	}
	
	// Test that user cannot log in with wrong password
	@Test
	public void test_wrongPassword() {
		
		UserLoginStatus loginStat = UserManagement.login(1, "Demo01", "invalid_password");
		
		assertTrue(loginStat == UserLoginStatus.WrongPassword);
	}
	
	// Test that the user cannot log in if the username is not found
	@Test
	public void test_usernameNotFound() {
		
		UserLoginStatus loginStat = UserManagement.login(1, "not_registered_user", "any_password");
		
		assertTrue(loginStat == UserLoginStatus.UsernameNotFound);
	}
	
	// Test that a user can log out
	@Test
	public void test_successful_logout() {
		UserManagement.logout(loggedInUser);
		
		assert(UserManagement.user1 == null);
	}

}
