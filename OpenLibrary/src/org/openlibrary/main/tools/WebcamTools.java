package org.openlibrary.main.tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openlibrary.main.OpenLibrary;

import com.github.sarxos.webcam.Webcam;

public class WebcamTools {
	
	private static QRTools qrHandler = new QRTools();
	
	public void takeImage(String saveTo) {
		Webcam webcam = Webcam.getDefault();
		webcam.open();
		BufferedImage image = webcam.getImage();
		try {
			ImageIO.write(image, "JPG", new File(saveTo + ".jpg"));
			OpenLibrary.logger.logDebug("Image taken by webcam: " + webcam.getName());
		} catch (IOException e) {
			OpenLibrary.logger.logError("Could not take image with webcam: " + webcam.getName());
			e.printStackTrace();
		}
	}
	
	public void takeImageAndScanForQR() {
		Webcam webcam = Webcam.getDefault();
		webcam.open();
		BufferedImage image = webcam.getImage();
		try {
			ImageIO.write(image, "JPG", new File("QRCode.jpg"));
			OpenLibrary.logger.logDebug("Image taken by webcam: " + webcam.getName());
	        try {
	            File file = new File("QRCode.png");
	            String decodedText = qrHandler.decodeQRCode(file);
	            if(decodedText == null) {
	                OpenLibrary.logger.logWarn("No QR Code found in the image");
	            } else {
	                OpenLibrary.logger.logQRResult(decodedText);
	            }
	        } catch (IOException e) {
	            System.out.println("Could not decode QR Code, IOException :: " + e.getMessage());
	        }
		} catch (IOException e) {
			OpenLibrary.logger.logError("Could not take image with webcam: " + webcam.getName());
			e.printStackTrace();
		}
	}
	
	public void scanExistingImageForQR(String filename) {
		try {
            File file = new File(filename);
            String decodedText = qrHandler.decodeQRCode(file);
            if(decodedText == null) {
                OpenLibrary.logger.logWarn("No QR Code found in the image");
            } else {
                OpenLibrary.logger.logQRResult(decodedText);
            }
        } catch (IOException e) {
            OpenLibrary.logger.logError("Could not decode QR Code, IOException :: " + e.getMessage());
        }
	}
}
