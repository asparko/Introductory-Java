package a5adept;

import java.util.Iterator;

public class IndirectFrame implements Frame {

	private int height;
	private int width;
	private int x_offset;
	private int y_offset;
	private Frame indFrame;
	
//Constructor
	
	public IndirectFrame(Frame source, int x_offset, int y_offset, int width, int height){
		//Check parameters are valid first!!!
		if(x_offset<0 || y_offset<0){
			throw new IllegalArgumentException("Crop corner out of bounds.");
		}
		if(width<1 || height<1){
			throw new IllegalArgumentException("Cropped Frame must have positive dimensions.");
		}
		if(x_offset+width > source.getWidth() | y_offset+height > source.getHeight()){
			throw new IllegalArgumentException("Crop dimensions extend beyond original Frame.");
		}
		
		//If parameters valid, create indirect frame
		this.height = height;
		this.width = width;
		this.x_offset = x_offset;
		this.y_offset = y_offset;
		indFrame = source;
	}
	
	
//Methods
	
	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public Pixel getPixel(int x, int y) {
		//Check x and y are valid parameters
		if (x < 0 || x >= width || y < 0 || y >= height) {
			throw new RuntimeException("x,y coordinates out of bounds");
		}
		return indFrame.getPixel(x+x_offset, y+y_offset);
	}

	@Override
	public String getTitle() {
		return indFrame.getTitle();
	}
	
	@Override
	public void setPixel(int x, int y, Pixel p) {
		//Check x and y are valid parameters
		if (x < 0 || x >= width || y < 0 || y >= height) {
			throw new RuntimeException("x,y coordinates out of bounds");
		}
		indFrame.setPixel(x+x_offset, y+y_offset, p);

	}

	@Override
	public String toString() {
		return ("Indirect Frame: " + indFrame.getTitle() + " (" + width + " x " + height + ") at (" 
				+ x_offset + ", " + y_offset + ")");
	}
	
	@Override
	public void setTitle(String new_title) {
		indFrame.setTitle(new_title);
	}

	@Override
	public boolean equals(Frame f) {
		//if not same dimensions or if compare pixels helper fcn returns false, they are not equal
		if (f.getWidth()!=width || f.getHeight()!=height || comparePixels(f)==false){
			return false;
		} else {
			return true;
		}
	}
	
	//Helper method to compare all pixel brightness levels of two frames:
	private boolean comparePixels(Frame f){ 
	
		//Assume not equal until proven otherwise:
		boolean result = false;
		
		//if frames are not same dimensions, result should be false
		if(height!=f.getHeight() || width!=f.getWidth()){
			result = false;
		} else { //if frames are the same dimensions, check to see if pixels have same brightness
			for(int x=0; x<width; x++){
				for(int y=0; y<height; y++){
					if(getPixel(x, y).equals(f.getPixel(x, y))){
						result = true;
					} else {
						result = false;
						break; //once one pixel doesn't match, break out of loop
					}
				}
			}
		}
		return result;
	}

	@Override
	public ColorPixel getAverage() { //can't delegate this function
		double totRed = 0;
		double totBlue = 0;
		double totGreen = 0;
		int entries = height*width; //total entries in indFrame
		
		if (indFrame instanceof GrayFrame){
			//For GrayFrame, rgb values are all equal, so can just average the red values
			//and assign this avg to each of the r, g, b values for the new ColorPixel
	
			//Note: getPixel() already deals with offset, so don't need it here
			for(int x=0; x<width; x++){
				for(int y=0; y<height; y++){
					totRed += getPixel(x,y).getRed();
				}
			}
			double avgRed = totRed/entries; //total red sum/number entries = avg
			return new ColorPixel(avgRed, avgRed, avgRed);
		} else {
			//For ColorFrame, need to get avg of r, g, and b separately
			
			for(int x=0; x<width; x++){
				for(int y=0; y<height; y++){
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


	@Override
	public String render() {
		//Create 2D char array that has same dims as Frame
		char[][] renderArray = new char[height][width];
		
		//Create string builder to turn char array into strings
		StringBuilder str = new StringBuilder();
		
		//Fill 2D char array with appropriate chars
		//Note: getPixel() already deals with the offset, so we don't need it here
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
		return str.toString();
	}

	
	public IndirectFrame crop(int x, int y, int width, int height){
		//Check for illegal values first:
		if(x<0 || y<0){
			throw new IllegalArgumentException("Crop corner out of bounds.");
		}
		if(width<1 || height<1){
			throw new IllegalArgumentException("Cropped Frame must have positive dimensions.");
		}
		if(x+width > getWidth() | y+height > getHeight()){
			throw new IllegalArgumentException("Crop dimensions extend beyond original Frame.");
		}
		
		//If parameters are okay, crop the frame:
		return new IndirectFrame(this, x, y, width, height);
	}
	
	@Override
	public Iterator<Pixel> iterator() {
		return new PixelIterator(this);
	}

}
