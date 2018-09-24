package org.openlibrary.main.handlers;

import java.awt.BorderLayout;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.openlibrary.main.OpenLibrary;
import org.openlibrary.main.users.UserManager;

public class GuiHandler {
	
	public static void adminPanel() {
		JFrame frame = new JFrame();

	    Icon blueIcon = new ImageIcon("openlibrary.svg");
	    
	    JDialog.setDefaultLookAndFeelDecorated(true);
	    Object[] selectionValues = { "Add Book", "Add User", "List All", "List Checked Out", "Close", "Log Out", "Reset Library" };
	    String initialSelection = "Choose Option";
	    Object selection = JOptionPane.showInputDialog(null, "What task would you like to preform",
	        "OpenLibrary Administrator", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
	    
	    if (selection == "Close") {
	    	try {
	    		FileWriter bookWriter = new FileWriter("Book.ol.data");
	    		for (String book : OpenLibrary.books) {
	    			bookWriter.write(book);
	    			bookWriter.write("\r\n");
	    		}
	    		bookWriter.close();
	    	} catch (IOException e) {
	    		OpenLibrary.logger.logError(e.getStackTrace().toString());		
	    	}
	    	
	    	try {
	    		FileWriter authorWriter = new FileWriter("Author.ol.data");
	    		for (String author : OpenLibrary.authors) {
	    			authorWriter.write(author);
	    			authorWriter.write("\r\n");
	    		}
	    		authorWriter.close();
	    	} catch (IOException e) {
	    		OpenLibrary.logger.logError(e.getStackTrace().toString());		
	    	}
	    	
	    	try {
	    		FileWriter priceWriter = new FileWriter("Price.ol.data");
	    		for (String price : OpenLibrary.prices) {
	    			priceWriter.write(price);
	    			priceWriter.write("\r\n");
	    		}
	    		priceWriter.close();
	    	} catch (IOException e) {
	    		OpenLibrary.logger.logError(e.getStackTrace().toString());		
	    	}
	    	
	    	try {
	    		FileWriter currIdWriter = new FileWriter("CurrentId.ol.data");
	    		currIdWriter.write(String.valueOf(OpenLibrary.currentId));
	    		currIdWriter.close();
	    	} catch (IOException e) {
	    		OpenLibrary.logger.logError(e.getStackTrace().toString());
	    	}
	    	
	    	try {
	    		FileWriter idWriter = new FileWriter("Id.ol.data");
	    		for (int bookId : OpenLibrary.bookIds) {
	    			idWriter.write(String.valueOf(bookId));
	    			idWriter.write("\r\n");
	    		}
	    		idWriter.close();
	    	} catch (IOException e) {
	    		OpenLibrary.logger.logError(e.getStackTrace().toString());		
	    	}	
	    	try {
	    		FileWriter statusWriter = new FileWriter("BookStatus.ol.data");
	    		for (String status : OpenLibrary.checkedOut) {
	    			statusWriter.write(status);
	    			statusWriter.write("\r\n");
	    		}
	    		statusWriter.close();
	    	} catch (IOException e) {
	    		OpenLibrary.logger.logError(e.getStackTrace().toString());		
	    	}
	    	try {
	    		FileWriter usersWriter = new FileWriter("Users.ol.data");
	    		for (String user : UserManager.users) {
	    			usersWriter.write(user);
	    			usersWriter.write("\r\n");
	    		}
	    		usersWriter.close();
	    	} catch (IOException e) {
	    		OpenLibrary.logger.logError(e.getStackTrace().toString());		
	    	}
	    	System.exit(0);
	    } else if (selection == "Add Book") {
	    	  String bookName = JOptionPane.showInputDialog("Enter book name");
	    	  String bookAuthor = JOptionPane.showInputDialog("Enter book author");
	    	  String bookPrice = JOptionPane.showInputDialog("Enter book price");
	    	  OpenLibrary.books.add(bookName);
	    	  OpenLibrary.authors.add(bookAuthor);
	    	  OpenLibrary.prices.add(bookPrice);
	    	  OpenLibrary.bookIds.add(OpenLibrary.currentId);
	    	  OpenLibrary.qrTools.generateQRCodeImage(String.valueOf(OpenLibrary.currentId), 180, 180, OpenLibrary.books.get(OpenLibrary.books.indexOf(bookName)) + ".jpg");
	    	  OpenLibrary.currentId++;
	    	  OpenLibrary.logger.logMessage("Current ID: "+ OpenLibrary.currentId);
	    	  adminPanel2();
	    } else if (selection == "Reset Library") {
	    	try {
	    		FileWriter bookWriter = new FileWriter("Book.ol.data");
	    		bookWriter.close();
	    	} catch (IOException e) {
	    		OpenLibrary.logger.logError(e.getStackTrace().toString());		
	    	}
	    	
	    	try {
	    		FileWriter authorWriter = new FileWriter("Author.ol.data");
	    		authorWriter.close();
	    	} catch (IOException e) {
	    		OpenLibrary.logger.logError(e.getStackTrace().toString());		
	    	}
	    	
	    	try {
	    		FileWriter priceWriter = new FileWriter("Price.ol.data");
	    		priceWriter.close();
	    	} catch (IOException e) {
	    		OpenLibrary.logger.logError(e.getStackTrace().toString());		
	    	}
	    	
	    	try {
	    		FileWriter currIdWriter = new FileWriter("CurrentId.ol.data");
	    		currIdWriter.close();
	    	} catch (IOException e) {
	    		OpenLibrary.logger.logError(e.getStackTrace().toString());
	    	}
	    	
	    	try {
	    		FileWriter idWriter = new FileWriter("Id.ol.data");
	    		idWriter.close();
	    		
	    	} catch (IOException e) {
	    		OpenLibrary.logger.logError(e.getStackTrace().toString());		
	    	}
	    	
	    	try {
	    		FileWriter statusWriter = new FileWriter("BookStatus.ol.data");
	    		statusWriter.close();
	    		
	    	} catch (IOException e) {
	    		OpenLibrary.logger.logError(e.getStackTrace().toString());		
	    	}
	    	
	    	System.exit(0);
	    } else if (selection == "Add User") {
	    	  String username = JOptionPane.showInputDialog(null, "Enter New User's Username", "OpenLibrary Administrator");
	    	  String password = JOptionPane.showInputDialog(null, "Enter New User's Password", "OpenLibrary Administrator");
	    	  UserManager.createUser(username, password);
	    	  JOptionPane.showMessageDialog(null, "User " + username + " Created.", "OpenLibrary Administror", JOptionPane.INFORMATION_MESSAGE);
	    	  adminPanel2();
	    } else if (selection == "Log Out") {
	    	OpenLibrary.users.logoutUser();
	    	userPanel();
	    } else if (selection == "List All") {
	    	
	    }
	}
	
	public static void adminPanel2() {
		JFrame frame2 = new JFrame();
		
	    Object stringArray2[] = { "Add Another Book", "Close" };
	    /** int response2 = JOptionPane.showOptionDialog(frame2, "Welcome to OpenLibary", "Select an Option",
	        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, stringArray2,
	        stringArray2[0]); **/
	    
	    JDialog.setDefaultLookAndFeelDecorated(true);
	    Object[] selectionValues = { "Add Another Book", "Add User", "Close", "Log Out" };
	    String initialSelection = "Add Another Book";
	    Object selection = JOptionPane.showInputDialog(null, "What task would you like to preform",
	        "OpenLibrary Administrator", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
	    
	    if (selection == "Close") {
	    	try {
	    		FileWriter bookWriter = new FileWriter("Book.ol.data");
	    		for (String book : OpenLibrary.books) {
	    			bookWriter.write(book);
	    			bookWriter.write("\r\n");
	    		}
	    		bookWriter.close();
	    	} catch (IOException e) {
	    		OpenLibrary.logger.logError(e.getStackTrace().toString());		
	    	}
	    	
	    	try {
	    		FileWriter authorWriter = new FileWriter("Author.ol.data");
	    		for (String author : OpenLibrary.authors) {
	    			authorWriter.write(author);
	    			authorWriter.write("\r\n");
	    		}
	    		authorWriter.close();
	    	} catch (IOException e) {
	    		OpenLibrary.logger.logError(e.getStackTrace().toString());		
	    	}
	    	
	    	try {
	    		FileWriter priceWriter = new FileWriter("Price.ol.data");
	    		for (String price : OpenLibrary.prices) {
	    			priceWriter.write(price);
	    			priceWriter.write("\r\n");
	    		}
	    		priceWriter.close();
	    	} catch (IOException e) {
	    		OpenLibrary.logger.logError(e.getStackTrace().toString());		
	    	}
	    	
	    	try {
	    		FileWriter currIdWriter = new FileWriter("CurrentId.ol.data");
	    		currIdWriter.write(String.valueOf(OpenLibrary.currentId));
	    		currIdWriter.close();
	    	} catch (IOException e) {
	    		OpenLibrary.logger.logError(e.getStackTrace().toString());
	    	}
	    	
	    	try {
	    		FileWriter idWriter = new FileWriter("Id.ol.data");
	    		for (int bookId : OpenLibrary.bookIds) {
	    			idWriter.write(String.valueOf(bookId));
	    			idWriter.write("\r\n");
	    		}
	    		idWriter.close();
	    	} catch (IOException e) {
	    		OpenLibrary.logger.logError(e.getStackTrace().toString());		
	    	}	
	    	try {
	    		FileWriter statusWriter = new FileWriter("BookStatus.ol.data");
	    		for (String status : OpenLibrary.checkedOut) {
	    			statusWriter.write(status);
	    			statusWriter.write("\r\n");
	    		}
	    		statusWriter.close();
	    	} catch (IOException e) {
	    		OpenLibrary.logger.logError(e.getStackTrace().toString());		
	    	}
	    	
	    	System.exit(0);
	      } else if (selection == "Add Another Book") {
	    	  String bookName = JOptionPane.showInputDialog("Enter book name");
	    	  String bookAuthor = JOptionPane.showInputDialog("Enter book author");
	    	  String bookPrice = JOptionPane.showInputDialog("Enter book price");
	    	  OpenLibrary. books.add(bookName);
	    	  OpenLibrary.authors.add(bookAuthor);
	    	  OpenLibrary.prices.add(bookPrice);
	    	  OpenLibrary.bookIds.add(OpenLibrary.currentId);
	    	  OpenLibrary.qrTools.generateQRCodeImage(String.valueOf(OpenLibrary.currentId), 180, 180, OpenLibrary.books.get(OpenLibrary.books.indexOf(bookName)) + ".jpg");
	    	  OpenLibrary.currentId++;
	    	  OpenLibrary.logger.logMessage("Current ID: "+ OpenLibrary.currentId);
	    	  adminPanel2();
	      } else if (selection == "Add User") {
	    	  String username = JOptionPane.showInputDialog(null, "Enter New User's Username", "OpenLibrary Administrator");
	    	  String password = JOptionPane.showInputDialog(null, "Enter New User's Password", "OpenLibrary Administrator");
	    	  UserManager.createUser(username, password);
	    	  JOptionPane.showMessageDialog(null, "User " + username + " Created.", "OpenLibrary Administror", JOptionPane.INFORMATION_MESSAGE);
	      } else if (selection == "Log Out") {
	    	  OpenLibrary.users.logoutUser();
	    	  userPanel();
	      }
	}
	
	public static void technitionPanel() {
		
	}
	
	public static void userPanel() {
	    int response = JOptionPane.showConfirmDialog(null, "", "Confirm",
	        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	    if (response == JOptionPane.NO_OPTION) {
	      System.out.println("No button clicked");
	    } else if (response == JOptionPane.YES_OPTION) {
	      System.out.println("Yes button clicked");
	    } else if (response == JOptionPane.CANCEL_OPTION) {
	      System.out.println("JOptionPane closed");
	    }
	}
	
	private static void listBooks() {
		JFrame bookList = new JFrame();

        List<String> columns = new ArrayList<String>();
        List<String[]> values = new ArrayList<String[]>();

        columns.add("Id");
        columns.add("Name");
        columns.add("Author");
        columns.add("Price");

        for (int id : OpenLibrary.bookIds) {
            values.add(new String[] {String.valueOf(id), OpenLibrary.books.get(id), OpenLibrary.authors.get(id), OpenLibrary.prices.get(id)});
        }

        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
        JTable table = new JTable(tableModel);
        bookList.setLayout(new BorderLayout());
        bookList.setTitle("Books in OpenLibrary");
        bookList.add(new JScrollPane(table), BorderLayout.CENTER);

        bookList.add(table.getTableHeader(), BorderLayout.NORTH);

        bookList.setVisible(true);
        bookList.setSize(800, 600);
	}
}
