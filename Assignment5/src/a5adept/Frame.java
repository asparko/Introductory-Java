package a5adept;

public interface Frame {
	int getWidth();
	int getHeight();
	Pixel getPixel(int x, int y);
	String getTitle();
	ColorPixel getAverage();
	
	void setPixel(int x, int y, Pixel p);
	void setTitle(String new_title);
	
	String toString();
	boolean equals(Frame f);
	String render();
	IndirectFrame crop(int x, int y, int width, int height);
	
	java.util.Iterator<Pixel> iterator();
}
