package a4adept;

public class GrayPixel implements Pixel {

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

	public double getBrightness() {
		return level;
	}
	
	//compare brightness of two pixels
	public boolean equals(Pixel p){
		double diff = Math.abs(this.getBrightness()-p.getBrightness());
		if(diff<=0.01){
			return true;
		} else {
			return false;
		}
	}
}
