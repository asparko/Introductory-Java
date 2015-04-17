package a3jedi;

import java.util.Arrays;
import java.util.Iterator;

public class GrayFrame implements Frame {

	private int width;
	private int height;
	private GrayPixel[][] pixArray;
		
	public GrayFrame(int width, int height, GrayPixel init_level){
		this.width = width;
		this.height = height;
		
		pixArray = new GrayPixel[width][height]; //allocate appropriate space for the pixel array
		
		//Set all pixels in array to be same as init_level:
		for (GrayPixel[] row : pixArray){
		    Arrays.fill(row, init_level);
		}
	}
	public GrayFrame (int width, int height){
		this(width,height,new GrayPixel(0.5));
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
			pixArray[x][y] = (GrayPixel)p;
		}
	}
	
	public Iterator<Pixel> iterator() {		
		return new PixelIterator(this);
	}
	
	public Iterator<Pixel> zigzag() {		
		return new ZigZagIterator(this);
	}
}
