package a3novice;

import java.util.Arrays;

public class ColorFrame implements Frame {

	private int width;
	private int height;
	private ColorPixel[][] pixArray;
	
	/*
	 * Your class implementation should encapsulate a 2D array of ColorPixel objects. 
	 * For the getPixel() and setPixel() method you should check the x and y values passed 
	 * in as parameters for being valid and throw a runtime exception if they are not. 
	 * You should also check to make sure the the pixel passed in to setPixel() is not null. 
	 * You may assume that the Pixel object reference is a reference to a ColorPixel object. 
	 * Because your ColorPicture frame will encapsulate a 2D array of ColorPixel objects, 
	 * you will need to explicitly "cast" the reference to a ColorPixel type. I will be 
	 * demonstrating this idea in class if you are unfamiliar with it.
	 */
	
	public ColorFrame(int width, int height, ColorPixel init_color){
		this.width = width;
		this.height = height;
		
		pixArray = new ColorPixel[width][height]; //allocate appropriate space for the pixel array
		
		//Set all pixels in array to be same as init_color:
		for (ColorPixel[] row : pixArray){
		    Arrays.fill(row, init_color);
		}
	}
	public ColorFrame (int width, int height){
		this(width,height,new ColorPixel(0.5,0.5,0.5));
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public Pixel getPixel(int x, int y){
		if (x>=width | y>=height) {
			throw new RuntimeException("Pixel Out of Frame");
		} else {
			return pixArray[x][y];
		}
	}
	
	public void setPixel(int x, int y, Pixel p){
		if (p == null){
			throw new RuntimeException("Null Pixel");
		} else if (x>=width | y>=height) {
			throw new RuntimeException("Pixel Out of Frame");
		} else {
			pixArray[x][y] = (ColorPixel)p;
		}
	}
}
