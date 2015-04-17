package a4jedi;

public class ColorFrame extends FrameImpl {

	private static final Pixel DEFAULT_PIXEL_VALUE = new ColorPixel(0.5, 0.5, 0.5);

//Constructors
	public ColorFrame(int width, int height, Pixel init_color, String title) {
		super(width, height, init_color, title);
	}
	
	public ColorFrame(int width, int height) {
		this(width, height, ColorFrame.DEFAULT_PIXEL_VALUE, "untitled");
	}

//Methods
	public void setPixel(int x, int y, Pixel p) {
		if (!(p instanceof ColorPixel)) {
			throw new RuntimeException("ColorFrame can only accept ColorPixel");
		}
		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
			throw new RuntimeException("x,y coordinates out of bounds");
		}
		pixels[y][x] = p;
	}
	
	public GrayFrame[] separate(){
		int width = getWidth();
		int height = getHeight();
		
		GrayFrame[] gf = new GrayFrame[3]; //initialize GrayFrame array with 3 elements
		GrayFrame redFrame = new GrayFrame(width, height);
		GrayFrame greenFrame = new GrayFrame(width, height);
		GrayFrame blueFrame = new GrayFrame(width, height);
		
		for (int y = 0; y<height; y++) {
			for (int x = 0; x<width; x++) {
				
				//Create new GrayPixels based on the current Red, Green, or Blue value:
				GrayPixel grayRedPixel = new GrayPixel(this.getPixel(x, y).getRed());
				GrayPixel grayGreenPixel = new GrayPixel(this.getPixel(x, y).getGreen());
				GrayPixel grayBluePixel = new GrayPixel(this.getPixel(x, y).getBlue());
				
				//Set current pixel in new frames to the above GrayPixel objects:
				redFrame.setPixel(x, y, grayRedPixel);
				greenFrame.setPixel(x, y, grayGreenPixel);
				blueFrame.setPixel(x, y, grayBluePixel);
			}
		}
		
		//Fill gf[] array with these new frames:
		gf[0] = redFrame;
		gf[1] = greenFrame;
		gf[2] = blueFrame;
		
		return gf;
	}

}
