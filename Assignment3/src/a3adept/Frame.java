package a3adept;

public interface Frame {
	
	int getWidth();
	int getHeight();
	Pixel getPixel(int x, int y);
	void setPixel(int x, int y, Pixel p);
	java.util.Iterator<Pixel> iterator();

}
