package a8;

public interface Pixel {
	double getRed();
	double getGreen();
	double getBlue();
	double getBrightness();
	boolean equals(Pixel other);
	char asChar();
	int toRGB();
}
