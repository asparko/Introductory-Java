package a4jedi;

public class GrayFrame extends FrameImpl {

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
		}

		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
			throw new RuntimeException("x,y coordinates out of bounds");
		}
		
		pixels[y][x] = p;
	}
	
	public GrayFrame[] separate(){
		GrayFrame[] gf = new GrayFrame[1];
		gf[0]= this;
		return gf;
	}
}
