package a6adept;

public interface Pixel {
	double getRed();
	double getGreen();
	double getBlue();
	double getBrightness();
	boolean equals(Pixel p);
	char asChar();
}
