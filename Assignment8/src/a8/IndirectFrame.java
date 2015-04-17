package a8;

public class IndirectFrame extends FrameImpl implements FrameObserver {

	private Frame source;
	private int x_offset;
	private int y_offset;
	private FrameArea source_area;
	
	public IndirectFrame(Frame source, int x_offset, int y_offset, int width, int height) {
		super(width, height);
		if (x_offset < 0 || x_offset >= source.getWidth() ||
			y_offset < 0 || y_offset >= source.getHeight() ||
			x_offset + width > source.getWidth() ||
			y_offset + height > source.getHeight()) {
			throw new IllegalArgumentException("Indirect frame specification is illegal.");
		}

		this.source = source;
		this.x_offset = x_offset;
		this.y_offset = y_offset;
		source_area = new FrameArea(x_offset, y_offset, x_offset+width-1, y_offset+height-1);

		source.registerFrameObserver(this);
	}
	
	
	@Override
	public Pixel getPixel(int x, int y) {
		check_coordinates(x,y);
		return source.getPixel(x+x_offset, y+y_offset);
	}

	@Override
	public void setPixel(int x, int y, Pixel p) {
		check_coordinates(x,y);
		source.setPixel(x+x_offset, y+y_offset, p);
	}

	@Override
	public String getTitle() {
		return source.getTitle();
	}

	@Override
	public void setTitle(String new_title) {
		source.setTitle(new_title);
	}

	public String toString() {
		return "Indirect Frame: " + getTitle() + 
			   " (" + getWidth() + " x " + getHeight() + ") at (" + 
			   x_offset + ", " + y_offset + ")";
	}

	@Override
	public void update(Frame frame, FrameArea area) {
		try {
			FrameArea intersection = area.intersect(source_area);
			notifyObservers(intersection.translate(-x_offset, -y_offset));
		}
		catch (NoIntersectionException e) {
			// Do nothing since updated area in source does
			// not intersect area of indirect frame.
		}
	}
}
