package a8;

public class DirectFrame extends FrameImpl {
	protected static final String DEFAULT_TITLE = "untitled";

	protected Pixel pixels[][];
	private String title;

	public DirectFrame(int width, int height, Pixel init_color, String title) {
		super(width, height);

		if (title == null) {
			throw new RuntimeException("Frame title must be specified.");
		}

		this.title = title;

		if (init_color == null) {
			throw new RuntimeException("Illegal initial pixel: null");
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String new_title) {
		if (new_title == null) {
			throw new RuntimeException("Attempt to set title to null");
		}
		title = new_title;
	}


	public Pixel getPixel(int x, int y) {
		check_coordinates(x,y);
		return pixels[y][x];
	}

	public void setPixel(int x, int y, Pixel p) {
		check_coordinates(x,y);
		pixels[y][x] = p;
		notifyObservers(x, y);
	}

	public String toString() {
		return "Frame: " + title + " (" + getWidth() + " x " + getHeight() + ")";
	}
}
