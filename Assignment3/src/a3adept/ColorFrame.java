package a3adept;

import java.util.Arrays;
import java.util.Iterator;

public class ColorFrame implements Frame {

	private int width;
	private int height;
	private ColorPixel[][] pixArray;
		
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
	
	public Iterator<Pixel> iterator() {		
		return new PixelIterator(this);
	}
}
