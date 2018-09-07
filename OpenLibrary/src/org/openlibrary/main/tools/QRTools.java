package org.openlibrary.main.tools;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;

import org.openlibrary.main.OpenLibrary;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QRTools {

    public String decodeQRCode(File qrCodeimage) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(qrCodeimage);
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        try {
            Result result = new MultiFormatReader().decode(bitmap);
            return result.getText();
        } catch (NotFoundException e) {
            OpenLibrary.logger.logWarn("There is no QR code in the image");
            return null;
        }
    }
	
	public void generateQRCodeImage(String text, int width, int height, String filePath) {
    	try {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        OpenLibrary.logger.logDebug("Generated QR Code with text \"" + text + "\"");
    	} catch (WriterException e) {
    		OpenLibrary.logger.logError("Could not generate QR Code, WriterException :: " + e.getMessage());
    	} catch (IOException e) {
    		OpenLibrary.logger.logError("Could not generate QR Code, IOException :: " + e.getMessage());
    	}
    }
    
}
