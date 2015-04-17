package a3novice;

public class ColorPixel implements Pixel {

	private double red;
	private double green;
	private double blue;
	
	public ColorPixel(double red, double green, double blue){
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	public double getRed(){
		return red;
	}
	public double getGreen(){
		return green;
	}
	public double getBlue(){
		return blue;
	}
	public double getBrightness(){
		return 0.2126 * red + 0.7152 * green + 0.0722 * blue;
	}
	
}
