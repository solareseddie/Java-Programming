/*
 * Converts a number of images into inverted versions and saves output
 *
 * @author: Eddie Solares
*/

import edu.duke.*;
import java.io.*;

public class BatchInversions {
	//Uses inImage and returns inverted version
	public ImageResource makeInversion(ImageResource inImage) {
		//Makes a blank image the same size of inImage
		ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
		//Loop over every pixel in outImage
		for (Pixel pixel: outImage.pixels()) {
			//Look at corresponding pixel in inImage
			Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
			//Calculate inversion parameter conversion
			int redInv = 255 - inPixel.getRed();
			int greenInv = 255 - inPixel.getGreen();
			int blueInv = 255 - inPixel.getBlue();
			//Set pixels to average value
			pixel.setRed(redInv);
			pixel.setGreen(greenInv);
			pixel.setBlue(blueInv);
		}
		//Returns the outImage
		return outImage;
	}
	
	//Processes batch of images to be inverted and saves file
	public void selectBatch() {
		DirectoryResource dr = new DirectoryResource();
		for (File f: dr.selectedFiles()) {
			ImageResource inImage = new ImageResource(f);
			ImageResource inv = makeInversion(inImage);
			String fname = inImage.getFileName();
			String newName = "inverted-" + fname;
			inv.setFileName(newName);
			inv.draw();
			inv.save();
		}
	}
}