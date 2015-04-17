package a6adept;

import java.util.Iterator;


public class IndirectFrame extends FrameImpl implements FrameObserver {

	private int x_offset;
	private int y_offset;
	private Frame sourceFrame;
	private FrameArea interestArea;
	
//Constructor
	
	public IndirectFrame(Frame source, int x_offset, int y_offset, int width, int height){
		super(width, height, source.getTitle());
		
		//Check parameters are valid first!!!
		if(x_offset<0 || y_offset<0){
			throw new IllegalArgumentException("Crop corner out of bounds.");
		}

		if(x_offset+width > source.getWidth() | y_offset+height > source.getHeight()){
			throw new IllegalArgumentException("Crop dimensions extend beyond original Frame.");
		}
		
		//If parameters valid, create indirect frame
		this.x_offset = x_offset;
		this.y_offset = y_offset;
		sourceFrame = source;
		interestArea = new FrameArea(x_offset, y_offset, x_offset+width-1, y_offset+height-1);
		
		//Register this IndirectFrame to observe the source frame
		sourceFrame.registerFrameObserver(this);
	}
	
	
//Methods
	@Override
	public Pixel getPixel(int x, int y) {
		if (x<0 || x >= getWidth() || y<0 || y >= getHeight()){
			throw new RuntimeException("x, y coordinates out of bounds");
		}
		return sourceFrame.getPixel(x+x_offset, y+y_offset);
	}
	
	@Override
	public void setPixel(int x, int y, Pixel p) {
		//Check x and y are valid parameters
		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
			throw new RuntimeException("x,y coordinates out of bounds");
		}
		sourceFrame.setPixel(x+x_offset, y+y_offset, p);
	}

	@Override
	public void update(Frame frame, FrameArea frame_area) {

		//check if changed pixel is in interest area
		try{
			FrameArea intersect = frame_area.intersect(interestArea);
			notifyObservers(intersect.translate(-x_offset, -y_offset));
		} catch (NoIntersectionException e) {
			
		}
	}
	
	@Override
	public String toString() {
		return ("Indirect Frame: " + sourceFrame.getTitle() + " (" + getWidth() + " x " + getHeight() + ") at (" 
				+ x_offset + ", " + y_offset + ")");
	}

	@Override
	public boolean equals(Frame f) {
		//if not same dimensions or if compare pixels helper fcn returns false, they are not equal
		if (f.getWidth()!= getWidth() || f.getHeight()!= getHeight() || comparePixels(f)==false){
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
		if(getHeight()!=f.getHeight() || getWidth()!=f.getWidth()){
			result = false;
		} else { //if frames are the same dimensions, check to see if pixels have same brightness
			for(int x=0; x<getWidth(); x++){
				for(int y=0; y<getHeight(); y++){
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
	public ColorPixel getAverage() { 
		double totRed = 0;
		double totBlue = 0;
		double totGreen = 0;
		int entries = getHeight()*getWidth(); //total entries in indFrame
		
		if (sourceFrame instanceof GrayFrame){
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
				for(int y=0; y< getHeight(); y++){
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
		char[][] renderArray = new char[getHeight()][getWidth()];
		
		//Create string builder to turn char array into strings
		StringBuilder str = new StringBuilder();
		
		//Fill 2D char array with appropriate chars
		//Note: getPixel() already deals with the offset, so we don't need it here
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

	
	@Override
	public Iterator<Pixel> iterator() {
		return new PixelIterator(this);
	}



}
