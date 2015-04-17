package a8;

public class GrayFrame extends DirectFrame {

	private static final Pixel DEFAULT_PIXEL_VALUE = new GrayPixel(0.5);
    	
	public GrayFrame(int width, int height, Pixel init_color, String title) {
		super(width, height, init_color, title);

		pixels = new GrayPixel[height][width];
		for (int y = 0; y<height; y++) {
			for (int x = 0; x<width; x++) {
				pixels[y][x] = init_color;
			}
		}
	}
	
	public GrayFrame(int width, int height) {
		this(width, height, GrayFrame.DEFAULT_PIXEL_VALUE, DirectFrame.DEFAULT_TITLE);
	}


	public void setPixel(int x, int y, Pixel p) {
		if (!(p instanceof GrayPixel)) {
			throw new RuntimeException("GrayFrame can only accept GrayPixel");
		}
		super.setPixel(x, y, p);
	}	
}
