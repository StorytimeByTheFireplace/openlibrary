package org.openlibrary.main;


import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.openlibrary.main.handlers.GuiHandler;
import org.openlibrary.main.tools.LoggingTools;
import org.openlibrary.main.tools.NetworkTools;
import org.openlibrary.main.tools.QRTools;
import org.openlibrary.main.tools.WebcamTools;
import org.openlibrary.main.users.UserManager;

//Main Class
public class OpenLibrary{
	
	public static LoggingTools logger = new LoggingTools();
	public static NetworkTools network = new NetworkTools();
	public static WebcamTools webcam = new WebcamTools();
	public static QRTools qrTools = new QRTools();
	public static UserManager users = new UserManager();
	public static List<String> books = new ArrayList<String>();
	public static List<String> authors = new ArrayList<String>();
	public static List<String> prices = new ArrayList<String>();
	public static List<Integer> bookIds = new ArrayList<Integer>();
	public static List<String> checkedOut = new ArrayList<String>();
	public static int currentId = 0;
	
	public static void main(String[] args) {
		try {
			FileReader bookReader = new FileReader("Book.ol.data");	
			FileReader authorReader = new FileReader("Author.ol.data");
			FileReader priceReader = new FileReader("Price.ol.data");
			FileReader currIdReader = new FileReader("CurrentId.ol.data");
			FileReader idReader = new FileReader("Id.ol.data");
			FileReader bookStatusReader = new FileReader("BookStatus.ol.data");
			FileReader usersReader = new FileReader("Users.ol.data");
			BufferedReader buffBookReader = new BufferedReader(bookReader);
			BufferedReader buffAuthorReader =  new BufferedReader(authorReader);
			BufferedReader buffPriceReader = new BufferedReader(priceReader);
			BufferedReader buffCurrIdReader = new BufferedReader(currIdReader);
			BufferedReader buffIdReader = new BufferedReader(idReader);
			BufferedReader buffBookStatusReader = new BufferedReader(bookStatusReader);
			BufferedReader buffUsersReader = new BufferedReader(usersReader);
			
			//My commits aren't working. 
			
			String bookLine;
			String authorLine;
			String priceLine;
			String currIdLine;
			String idLine;
			String statusLine;
			String usersLine;
			
			while ((bookLine = buffBookReader.readLine()) != null) {
                books.add(bookLine);
            }
            bookReader.close();
            
            while ((authorLine = buffAuthorReader.readLine()) != null) {
                authors.add(authorLine);
            }
            authorReader.close();
            
            while ((priceLine = buffPriceReader.readLine()) != null) {
                prices.add(priceLine);
            }
            priceReader.close();
            
            while ((currIdLine = buffCurrIdReader.readLine()) != null) {
                currentId = Integer.parseInt(currIdLine);
            }
            currIdReader.close();
            
            while ((idLine = buffIdReader.readLine()) != null) {
                bookIds.add(Integer.parseInt(idLine));
            }
            idReader.close();
            
            while ((statusLine = buffBookStatusReader.readLine()) != null) {
            	checkedOut.add(statusLine);
            }
            bookStatusReader.close();
            
            while ((usersLine = buffUsersReader.readLine()) != null) {
            	checkedOut.add(statusLine);
            }
            bookStatusReader.close();
		} catch (IOException e) {
			logger.logError(e.getStackTrace().toString());		
		}
		logger.logMessage("Started OpenLibrary");
		logger.logDebug("Attempting to Initialize NetworkTools");
		network.networkInit();
		logger.logSuccess("Running on: " + network.getHostName() + "@" + network.getIP());
		logger.logMessage("Welcome to OpenLibrary!");
		/**
		logger.logMessage("Testing Camera");
		webcam.takeImage("CameraTest");
		logger.logMessage("Testing QR Scan & Create functions");
		qrTools.generateQRCodeImage("Test Success!", 180, 180, "./ScanTest.png");
		webcam.scanExistingImageForQR("ScanTest.png");
		**/
		
		GuiHandler.adminPanel();
	}	
}
