package org.openlibrary.main.tools;

import java.net.InetAddress;

import org.openlibrary.main.OpenLibrary;

public class NetworkTools {

	private InetAddress inetAddress;
	
	public void networkInit() {
		try {
		inetAddress = InetAddress.getLocalHost();
		} catch(Exception e) {
			OpenLibrary.logger.logError("Failed to Initialize NetworkTools");
			e.printStackTrace();
		}
	}
	
	public String getIP() {
		String address = inetAddress.getHostAddress();
		return address;
	}
	
	public String getHostName() {
		String hostname = inetAddress.getHostName();
		return hostname;
	}
}
