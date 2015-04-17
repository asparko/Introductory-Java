package a8;

public class GrayPixel extends PixelImpl {

	private double level;

	public GrayPixel(double level) {
		this.level = level;
	}
	
	public double getRed() {
		return level;
	}

	public double getGreen() {
		return level;
	}

	public double getBlue() {
		return level;
	}
}
