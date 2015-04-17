package a4adept;

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
		// else call frameImpl's version of setPixel
		super.setPixel(x, y, p);
	}
	

}
