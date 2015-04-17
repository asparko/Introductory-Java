package a3jedi;

public class GrayPixel implements Pixel {
	
	private double level;
	
	public GrayPixel(double level){
		if (level<0.0 | level>1.0) {
			throw new RuntimeException("Level must be between 0.0 and 1.0");
		} else {
			this.level=level;
		}
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

	public Pixel lighten(double factor) {
		if(factor>=0.0 && factor<=1.0){
			level = level * (1.0 - factor) + factor;
		} else {
			throw new RuntimeException("Factor must be between 0.0 and 1.0");
		}
		return new GrayPixel(level);
	}

	public Pixel darken(double factor) {
		if(factor>=0.0 && factor<=1.0){
			level = level * (1.0 - factor);
		} else {
			throw new RuntimeException("Factor must be between 0.0 and 1.0");
		}
		return new GrayPixel(level);
	}

}
