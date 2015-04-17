package a5jedi;

public interface Pixel {
	double getRed();
	double getGreen();
	double getBlue();
	double getBrightness();
	boolean equals(Pixel p);
	char asChar();
}
