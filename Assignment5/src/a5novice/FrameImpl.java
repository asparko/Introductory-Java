package a5novice;

import java.util.Iterator;

abstract public class FrameImpl implements Frame{

	protected Pixel pixels[][];
	private int width;
	private int height;
	private String title;
	
//Constructor
	public FrameImpl(int width, int height, Pixel init_color, String title) {
		if (width < 1 || height < 1) {
			throw new RuntimeException("Illegal dimensions.");
		}

		this.width = width;
		this.height = height;
		this.title = title;

		if (init_color == null) {
			throw new RuntimeException("Illegal initial pixel: null");
		}
		
		if (title == null) {
			throw new RuntimeException("Illegal initial title: null");
		}
				
		pixels = new Pixel[height][width];
		for (int y = 0; y<height; y++) {
			for (int x = 0; x<width; x++) {
				pixels[y][x] = init_color;
			}
		}
	}

	
//Methods
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Pixel getPixel(int x, int y) {
		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
			throw new RuntimeException("x,y coordinates out of bounds");
		}

		return pixels[y][x];
	}


	public void setPixel(int x, int y, Pixel p){
		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
			throw new RuntimeException("x,y coordinates out of bounds");
		}	
		pixels[y][x] = p;
	}
	
	
	public String getTitle() {
		return title;
	}


	public void setTitle(String new_title) {
		if (new_title == null){
			throw new RuntimeException("Cannot set title as null");
		} else {
			title = new_title;
		}	
	}
	
	public String toString(){
		return("Frame: " + title + " (" + width + " x " + height + ")");
	}
	
	abstract public GrayFrame[] separate(); //defined differently for GrayFrames and ColorFrames

	public boolean equals(Frame f) {
		if (f.getWidth()!=width || f.getHeight()!=height || comparePixels(f)==false){
			return false;
		} else {
			return true;
		}
	}
	
	//compare all pixel brightness levels of two frames:
	private boolean comparePixels(Frame f){ 
		
		//Create iterators for both frames
		PixelIterator piThis = (PixelIterator)iterator();
		PixelIterator piOther = (PixelIterator)f.iterator();
		boolean result = false;
		
		//if frames are not same dimensions, result should be false
		if(height!=f.getHeight() || width!=f.getWidth()){
			result = false;
		} else { //if frames are the same dimensions, check to see if pixels have same brightness
			while (piThis.hasNext()){
				if(piThis.next().equals(piOther.next())){
					result = true;
				} else {
					result = false;
					break; //once one pixel doesn't match, break out of loop
				}
			}
		}
		return result;
	}
	
	public ColorPixel getAverage(){
		double avgRed = 0;
		double avgBlue = 0;
		double avgGreen = 0;
		int entries = height*width;
		
		if (this instanceof GrayFrame){
			//For GrayFrame, rgb values are all equal, so can just average the red values
			//and assign this avg to each of the r, g, b values for the new ColorPixel
			
			for(int x=0; x<width; x++){
				for(int y=0; y<height; y++){
					avgRed += getPixel(x,y).getRed();
				}
			}
			avgRed = avgRed/entries;
			return new ColorPixel(avgRed, avgRed, avgRed);
		} else {
			//For ColorFrame, need to get avg of r, g, and b separately
			
			for(int x=0; x<width; x++){
				for(int y=0; y<height; y++){
					avgRed += getPixel(x,y).getRed();
					avgGreen += getPixel(x,y).getGreen();
					avgBlue += getPixel(x,y).getBlue();
				}
			}
			avgRed = avgRed/entries;
			avgGreen = avgGreen/entries;
			avgBlue = avgBlue/entries;
			return new ColorPixel(avgRed, avgGreen, avgBlue);
		}
	}
	
	public String render(){
		//Create 2D char array that has same dims as Frame
		char[][] renderArray = new char[height][width];
		
		//Create string builder to turn char array into strings
		StringBuilder str = new StringBuilder();
		
		//Fill 2D char array with appropriate chars
		for(int x=0; x<width; x++){
			for(int y=0; y<height; y++){
				renderArray[y][x] = getPixel(x,y).asChar();
			}
		}
		
		//for each row in char array, append it to the string builder and then add a new line char
		for(char[] c: renderArray){
			str.append(c);
			str.append('\n');
		}
		str.setLength(str.length() - 1); //remove final newline char
		return str.toString();
		
	}
	
	//Include an iterator so we can easily compare corresponding pixels in two frames
	public Iterator<Pixel> iterator() {		
		return new PixelIterator(this);
	}
}
