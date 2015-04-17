package a8;

public class ColorPixel extends PixelImpl {

	private double red;
	private double green;
	private double blue;

	public ColorPixel(double red, double green, double blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	public ColorPixel(int RGB) {
		red = ((double) ((RGB >> 16) & 0xff)) / 255.0;
		green = ((double) ((RGB >> 8) & 0xff)) / 255.0;
		blue = ((double) (RGB & 0xff)) / 255.0;	
	}
	
	public double getRed() {
		return red;
	}

	public double getGreen() {
		return green;
	}

	public double getBlue() {
		return blue;
	}
}
