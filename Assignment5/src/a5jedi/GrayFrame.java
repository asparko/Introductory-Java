package a5jedi;

public class GrayFrame extends DirectFrame {

	private static final Pixel DEFAULT_PIXEL_VALUE = new GrayPixel(0.5);
	
//Constructors
	public GrayFrame(int width, int height, Pixel init_color, String title) {
		super(width, height, init_color, title);
	}
	
	public GrayFrame(int width, int height) {
		this(width, height, DEFAULT_PIXEL_VALUE, "untitled");
	}
	
	
//Methods
	public void setPixel(int x, int y, Pixel p) {
		if (!(p instanceof GrayPixel)) {
			throw new RuntimeException("GrayFrame can only accept GrayPixel");
		} else {
			super.setPixel(x, y, p);
		}
	}
	
}
