/*
 * Converts a number of images into grayscale versions and saves output
 *
 * @author: Eddie Solares
*/

import edu.duke.*;
import java.io.*;

public class GrayScaleBatch {
	//Uses inImage and returns inverted version
	public ImageResource makeGray(ImageResource inImage) {
		//Makes a blank image the same size of inImage
		ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
		//Loop over every pixel in outImage
		for (Pixel pixel: outImage.pixels()) {
			//Look at corresponding pixel in inImage
			Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
			//Calculate the average for grayscale
			int avg= (inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue())/3;
			//Set pixels to average value
			pixel.setRed(avg);
			pixel.setGreen(avg);
			pixel.setBlue(avg);
		}
		//Returns the outImage
		return outImage;
	}
	
	//Processes batch of images to be grayscaled and saves file
	public void selectBatch() {
		DirectoryResource dr = new DirectoryResource();
		for (File f: dr.selectedFiles()) {
			ImageResource inImage = new ImageResource(f);
			ImageResource gray = makeGray(inImage);
			String fname = inImage.getFileName();
			String newName = "gray-" + fname;
			gray.setFileName(newName);
			gray.draw();
			gray.save();
		}
	}
}