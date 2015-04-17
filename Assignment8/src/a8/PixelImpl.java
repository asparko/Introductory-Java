package a8;

public abstract class PixelImpl implements Pixel {
	private static char[] char_map = new char[] {' ', '.', ':', '*', '+', '?', 'X', '%', '#', '@', '@'};

	abstract public double getRed();

	abstract public double getGreen();

	abstract public double getBlue();

	public double getBrightness() {
		return 0.2126*getRed() + 0.7152*getGreen() + 0.0722*getBlue();
	}

	public boolean equals(Pixel other) {
		return (Math.abs(getBrightness() - other.getBrightness()) < 0.1);
	}

	public char asChar() {
		return char_map[(int) (getBrightness() / 0.1)];
	}
	
	public int toRGB() {
		return ((((int) (getRed() * 255.0 + 0.5)) << 16) |
				(((int) (getGreen() * 255.0 + 0.5)) << 8) |
				(((int) (getBlue() * 255.0 + 0.5))));
	}



}
