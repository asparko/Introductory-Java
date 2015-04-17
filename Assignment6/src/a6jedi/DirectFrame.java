package a6jedi;

import java.util.Iterator;

public class DirectFrame extends FrameImpl {

	protected Pixel pixels[][];
	
//CONSTRUCTOR

	public DirectFrame(int width, int height, Pixel init_color, String title){
		super(width, height, title);

		if (init_color == null) {
			throw new RuntimeException("Illegal initial pixel: null");
		}
		
		pixels = new Pixel[getHeight()][getWidth()];
		for (int y = 0; y<getHeight(); y++) {
			for (int x = 0; x<getWidth(); x++) {
				pixels[y][x] = init_color;
			}
		}
	}
	
//METHODS

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
		notifyObservers(new FrameArea(x,y,x,y)); //tell observers a pixel changed
	}
	
	
	public String toString(){
		return("Frame: " + getTitle() + " (" + getWidth() + " x " + getHeight() + ")");
	}
	

	public boolean equals(Frame f) {
		//if not same dimensions or if compare pixels helper fcn returns false, they are not equal
		if (f.getWidth()!=getWidth() || f.getHeight()!=getHeight() || comparePixels(f)==false){
			return false;
		} else {
			return true;
		}
	}
	
	//Helper method to compare all pixel brightness levels of two frames:
	private boolean comparePixels(Frame f){ 
		
		//Create iterators for both frames
		PixelIterator piThis = (PixelIterator)iterator();
		PixelIterator piOther = (PixelIterator)f.iterator();
		boolean result = false;
		
		//if frames are not same dimensions, result should be false
		if(getHeight()!=f.getHeight() || getWidth() !=f.getWidth()){
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
		double totRed = 0;
		double totBlue = 0;
		double totGreen = 0;
		int entries = getHeight()*getWidth(); //total entries in indFrame
		
		if (this instanceof GrayFrame){
			//For GrayFrame, rgb values are all equal, so can just average the red values
			//and assign this avg to each of the r, g, b values for the new ColorPixel
	
			//Note: getPixel() already deals with offset, so don't need it here
			for(int x=0; x<getWidth(); x++){
				for(int y=0; y<getHeight(); y++){
					totRed += getPixel(x,y).getRed();
				}
			}
			double avgRed = totRed/entries; //total red sum/number entries = avg
			return new ColorPixel(avgRed, avgRed, avgRed);
		} else {
			//For ColorFrame, need to get avg of r, g, and b separately
			
			for(int x=0; x<getWidth(); x++){
				for(int y=0; y<getHeight(); y++){
					Pixel p = getPixel(x,y);
					totRed += p.getRed();
					totGreen += p.getGreen();
					totBlue += p.getBlue();
				}
			}
			double avgRed = totRed/entries; //total sum/# enries = avg
			double avgGreen = totGreen/entries;
			double avgBlue = totBlue/entries;
			return new ColorPixel(avgRed, avgGreen, avgBlue);
		}
	}
	
	public String render(){
		//Create 2D char array that has same dims as Frame
		char[][] renderArray = new char[getHeight()][getWidth()];
		
		//Create string builder to turn char array into strings
		StringBuilder str = new StringBuilder();
		
		//Fill 2D char array with appropriate chars
		for(int x=0; x<getWidth(); x++){
			for(int y=0; y<getHeight(); y++){
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
