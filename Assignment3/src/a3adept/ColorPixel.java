package a3adept;

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
	public Pixel lighten(double factor){
		if(factor>=0.0 && factor<=1.0){
			red = red * (1.0 - factor) + factor;
			blue = blue * (1.0 - factor) + factor;
			green = green * (1.0 - factor) + factor;
		} else {
			throw new RuntimeException("Factor must be between 0.0 and 1.0");
		}
		return new ColorPixel(red, green, blue);
	}
	
	public Pixel darken(double factor){
		if(factor>=0.0 && factor<=1.0){
			red = red * (1.0 - factor);
			blue = blue * (1.0 - factor);
			green = green * (1.0 - factor);
		} else {
			throw new RuntimeException("Factor must be between 0.0 and 1.0");
		}
		return new ColorPixel(red, green, blue);
	}
}
