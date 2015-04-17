package a3jedi;

public interface Pixel {
	double getRed();
	double getGreen();
	double getBlue();
	double getBrightness();
	Pixel lighten(double factor);
	Pixel darken(double factor);
}
