package a3jedi;

public interface Frame {
	
	int getWidth();
	int getHeight();
	Pixel getPixel(int x, int y);
	void setPixel(int x, int y, Pixel p);
	java.util.Iterator<Pixel> iterator();
	java.util.Iterator<Pixel> zigzag();

}
