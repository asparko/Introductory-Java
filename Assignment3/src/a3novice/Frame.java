package a3novice;

public interface Frame {
	
	int getWidth();
	int getHeight();
	Pixel getPixel(int x, int y);
	void setPixel(int x, int y, Pixel p);

}
