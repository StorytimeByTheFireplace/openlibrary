package org.openlibrary.main.tools;

public class LoggingTools {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	
	public void logDebug(String log) {
		System.out.println("[OpenLibrary:debug] " + log);
	}
	
	public void logError(String log) {
		System.out.println("[OpenLibrary:error] " + log);
	}
	
	public void logWarn(String log) {
		System.out.println("[OpenLibrary:warn] " + log);
	}
	
	public void logSuccess(String log) {
		System.out.println("[OpenLibrary:success] " + log);
	}
	
	public void logMessage(String log) {
		System.out.println("[OpenLibrary] " + log);
	}
	
}
