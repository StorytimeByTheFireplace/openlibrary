package org.openlibrary.main.tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openlibrary.main.OpenLibrary;

import com.github.sarxos.webcam.Webcam;

public class WebcamTools {
	public void takeImage() {
	Webcam webcam = Webcam.getDefault();
	webcam.open();
	BufferedImage image = webcam.getImage();
	try {
		ImageIO.write(image, "JPG", new File("test.jpg"));
		OpenLibrary.logger.logDebug("Image taken by webcam: " + webcam.getName());
	} catch (IOException e) {
		OpenLibrary.logger.logError("Could not take image with webcam: " + webcam.getName());
		e.printStackTrace();
	}
	}
}
