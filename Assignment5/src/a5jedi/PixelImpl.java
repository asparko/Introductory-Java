package a5jedi;

public class PixelImpl implements Pixel{
	
	private double red;
	private double green;
	private double blue;

	public PixelImpl(double r, double g, double b){
		red=r;
		green=g;
		blue=b;
	}
	
	@Override
	public double getRed() {
		return red;
	}

	@Override
	public double getGreen() {
		return green;
	}

	@Override
	public double getBlue() {
		return blue;
	}

	@Override
	public double getBrightness() {
		return 0.2126*red + 0.7152*green + 0.0722*blue;
	}

	@Override
	//compare brightness of two pixels
	public boolean equals(Pixel p){
		
		//Calculate difference in pixel brightness values
		double diff = Math.abs(this.getBrightness()-p.getBrightness());
		
		//if diff below a certain threshold, pixels are equal
		if(diff<=0.01){
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public char asChar() {
		double brightness = getBrightness();
		if(brightness < 0.1){
			return ' ';
		} else if(brightness < 0.2){
			return '.';
		} else if(brightness < 0.3){
			return ':';
		} else if(brightness < 0.4){
			return '*';
		} else if(brightness < 0.5){
			return '+';
		} else if(brightness < 0.6){
			return '?';
		} else if(brightness < 0.7){
			return 'X';
		} else if(brightness < 0.8){
			return '%';
		} else if(brightness < 0.9){
			return '#';
		} else {
			return '@';
		}
	}

}
