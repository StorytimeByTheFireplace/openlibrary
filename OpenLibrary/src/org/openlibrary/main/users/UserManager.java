package org.openlibrary.main.users;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openlibrary.main.OpenLibrary;

public class UserManager {

	public static String currentUser;
	public static List<String> users = new ArrayList<String>();
	public boolean loggedIn;
	
	public static void createUser(String username, String password) {
		try {
    		FileWriter userWriter = new FileWriter(username + ".ol.user");
    		userWriter.write(password);
    		users.add(username);
    		userWriter.close();
    	} catch (IOException e) {
    		OpenLibrary.logger.logError(e.getStackTrace().toString());		
    	}
	}
	
	public void loginUser(String username, String password) {
		try {
			FileReader loginReader = new FileReader(username + ".ol.user");
			BufferedReader buffLoginReader = new BufferedReader(loginReader);
			if (password == buffLoginReader.readLine()) {
				currentUser = username;
				loggedIn = true;
			}
		} catch (IOException e) {
			OpenLibrary.logger.logWarn("User does not exist.");
		}
	}
	
	public void logoutUser() {
		currentUser = "";
		loggedIn = false;
	}
	
}
