package a5adept;

public class GrayPixel extends PixelImpl {

	private double level;
	
	public GrayPixel(double level) {
		super(level,level,level);
		this.level = level;
	}
	
	@Override
	public double getBrightness() {
		return level;
	}
}
