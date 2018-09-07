package org.openlibrary.main;

import org.openlibrary.main.tools.*;

//Main Class
public class OpenLibrary {

	public static LoggingTools logger = new LoggingTools();
	public static NetworkTools network = new NetworkTools();
	
	public static void main(String[] args) {
		logger.logMessage("Started OpenLibrary");
		logger.logDebug("Attempting to Initialize NetworkTools");
		network.networkInit();
		logger.logSuccess("Running on: " + network.getHostName() + "@" + network.getIP());
		logger.logMessage("Welcome to OpenLibrary!");
	}
	
}
