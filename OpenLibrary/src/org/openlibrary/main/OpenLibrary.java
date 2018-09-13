package org.openlibrary.main;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.openlibrary.main.tools.LoggingTools;
import org.openlibrary.main.tools.NetworkTools;
import org.openlibrary.main.tools.QRTools;
import org.openlibrary.main.tools.WebcamTools;

//Main Class
public class OpenLibrary {
	
	public static LoggingTools logger = new LoggingTools();
	public static NetworkTools network = new NetworkTools();
	public static WebcamTools webcam = new WebcamTools();
	public static QRTools qrTools = new QRTools();
	public static List<String> books = new ArrayList<String>();
	public static List<String> authors = new ArrayList<String>();
	public static List<String> prices = new ArrayList<String>();
	public static List<Integer> bookIds = new ArrayList<Integer>();
	private static int currentId = 0;
	
	public static void main(String[] args) {
		try {
			FileReader bookReader = new FileReader("Book.ol.data");	
			FileReader authorReader = new FileReader("Author.ol.data");
			FileReader priceReader = new FileReader("Price.ol.data");
			FileReader currIdReader = new FileReader("CurrentId.ol.data");
			FileReader idReader = new FileReader("Id.ol.data");
			BufferedReader buffBookReader = new BufferedReader(bookReader);
			BufferedReader buffAuthorReader =  new BufferedReader(authorReader);
			BufferedReader buffPriceReader = new BufferedReader(priceReader);
			BufferedReader buffCurrIdReader = new BufferedReader(currIdReader);
			BufferedReader buffIdReader = new BufferedReader(idReader);
			
			String bookLine;
			String authorLine;
			String priceLine;
			String currIdLine;
			String idLine;
			
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
		JFrame frame = new JFrame();

	    Icon blueIcon = new ImageIcon("openlibrary.svg");
	    Object stringArray[] = { "Add Book", "Close", "Reset Library"};
	    int response = JOptionPane.showOptionDialog(frame, "Welcome to OpenLibary", "Select an Option",
	        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, blueIcon, stringArray,
	        stringArray[0]);
	    
	    if (response == JOptionPane.NO_OPTION) {
	    	try {
	    		FileWriter bookWriter = new FileWriter("Book.ol.data");
	    		for (String book : books) {
	    			bookWriter.write(book);
	    			bookWriter.write("\r\n");
	    		}
	    		bookWriter.close();
	    	} catch (IOException e) {
	    		logger.logError(e.getStackTrace().toString());		
	    	}
	    	
	    	try {
	    		FileWriter authorWriter = new FileWriter("Author.ol.data");
	    		for (String author : authors) {
	    			authorWriter.write(author);
	    			authorWriter.write("\r\n");
	    		}
	    		authorWriter.close();
	    	} catch (IOException e) {
	    		logger.logError(e.getStackTrace().toString());		
	    	}
	    	
	    	try {
	    		FileWriter priceWriter = new FileWriter("Price.ol.data");
	    		for (String price : prices) {
	    			priceWriter.write(price);
	    			priceWriter.write("\r\n");
	    		}
	    		priceWriter.close();
	    	} catch (IOException e) {
	    		logger.logError(e.getStackTrace().toString());		
	    	}
	    	
	    	try {
	    		FileWriter currIdWriter = new FileWriter("CurrentId.ol.data");
	    		currIdWriter.write(String.valueOf(currentId));
	    		currIdWriter.close();
	    	} catch (IOException e) {
	    		logger.logError(e.getStackTrace().toString());
	    	}
	    	
	    	try {
	    		FileWriter idWriter = new FileWriter("Id.ol.data");
	    		for (int bookId : bookIds) {
	    			idWriter.write(String.valueOf(bookId));
	    			idWriter.write("\r\n");
	    		}
	    		idWriter.close();
	    		return;
	    	} catch (IOException e) {
	    		logger.logError(e.getStackTrace().toString());		
	    	}
	    } else if (response == JOptionPane.YES_OPTION) {
	    	  String bookName = JOptionPane.showInputDialog("Enter book name");
	    	  String bookAuthor = JOptionPane.showInputDialog("Enter book author");
	    	  String bookPrice = JOptionPane.showInputDialog("Enter book price");
	    	  books.add(bookName);
	    	  authors.add(bookAuthor);
	    	  prices.add(bookPrice);
	    	  bookIds.add(currentId);
	    	  qrTools.generateQRCodeImage(String.valueOf(currentId), 180, 180, books.get(books.indexOf(bookName)) + ".jpg");
	    	  currentId++;
	    	  logger.logMessage("Current ID: "+ currentId);
	    } else if (response == JOptionPane.CANCEL_OPTION) {
	    	try {
	    		FileWriter bookWriter = new FileWriter("Book.ol.data");
	    		bookWriter.close();
	    	} catch (IOException e) {
	    		logger.logError(e.getStackTrace().toString());		
	    	}
	    	
	    	try {
	    		FileWriter authorWriter = new FileWriter("Author.ol.data");
	    		authorWriter.close();
	    	} catch (IOException e) {
	    		logger.logError(e.getStackTrace().toString());		
	    	}
	    	
	    	try {
	    		FileWriter priceWriter = new FileWriter("Price.ol.data");
	    		priceWriter.close();
	    	} catch (IOException e) {
	    		logger.logError(e.getStackTrace().toString());		
	    	}
	    	
	    	try {
	    		FileWriter currIdWriter = new FileWriter("CurrentId.ol.data");
	    		currIdWriter.close();
	    	} catch (IOException e) {
	    		logger.logError(e.getStackTrace().toString());
	    	}
	    	
	    	try {
	    		FileWriter idWriter = new FileWriter("Id.ol.data");
	    		idWriter.close();
	    		return;
	    	} catch (IOException e) {
	    		logger.logError(e.getStackTrace().toString());		
	    	}
	    }
		while(true) {
			JFrame frame2 = new JFrame();
			
		    Object stringArray2[] = { "Add Another Book", "Close" };
		    int response2 = JOptionPane.showOptionDialog(frame2, "Welcome to OpenLibary", "Select an Option",
		        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, stringArray2,
		        stringArray2[0]);
		    
		    if (response2 == JOptionPane.NO_OPTION) {
		    	try {
		    		FileWriter bookWriter = new FileWriter("Book.ol.data");
		    		for (String book : books) {
		    			bookWriter.write(book);
		    			bookWriter.write("\r\n");
		    		}
		    		bookWriter.close();
		    	} catch (IOException e) {
		    		logger.logError(e.getStackTrace().toString());		
		    	}
		    	
		    	try {
		    		FileWriter authorWriter = new FileWriter("Author.ol.data");
		    		for (String author : authors) {
		    			authorWriter.write(author);
		    			authorWriter.write("\r\n");
		    		}
		    		authorWriter.close();
		    	} catch (IOException e) {
		    		logger.logError(e.getStackTrace().toString());		
		    	}
		    	
		    	try {
		    		FileWriter priceWriter = new FileWriter("Price.ol.data");
		    		for (String price : prices) {
		    			priceWriter.write(price);
		    			priceWriter.write("\r\n");
		    		}
		    		priceWriter.close();
		    	} catch (IOException e) {
		    		logger.logError(e.getStackTrace().toString());		
		    	}
		    	
		    	try {
		    		FileWriter currIdWriter = new FileWriter("CurrentId.ol.data");
		    		currIdWriter.write(String.valueOf(currentId));
		    		currIdWriter.close();
		    	} catch (IOException e) {
		    		logger.logError(e.getStackTrace().toString());
		    	}
		    	
		    	try {
		    		FileWriter idWriter = new FileWriter("Id.ol.data");
		    		for (int bookId : bookIds) {
		    			idWriter.write(String.valueOf(bookId));
		    			idWriter.write("\r\n");
		    		}
		    		idWriter.close();
		    		return;
		    	} catch (IOException e) {
		    		logger.logError(e.getStackTrace().toString());		
		    	}	
		      } else if (response2 == JOptionPane.YES_OPTION) {
		    	  String bookName = JOptionPane.showInputDialog("Enter book name");
		    	  String bookAuthor = JOptionPane.showInputDialog("Enter book author");
		    	  String bookPrice = JOptionPane.showInputDialog("Enter book price");
		    	  books.add(bookName);
		    	  authors.add(bookAuthor);
		    	  prices.add(bookPrice);
		    	  bookIds.add(currentId);
		    	  qrTools.generateQRCodeImage(String.valueOf(currentId), 180, 180, books.get(books.indexOf(bookName)) + ".jpg");
		    	  currentId++;
		    	  logger.logMessage("Current ID: "+ currentId);
		      }
		}
	}
	
}
