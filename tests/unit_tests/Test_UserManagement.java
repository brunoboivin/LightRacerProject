package unit_tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import UserPkg.User;
import UserPkg.UserManagement;

public class Test_UserManagement {

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

}
