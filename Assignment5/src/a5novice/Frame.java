package a5novice;

public interface Frame {
	int getWidth();
	int getHeight();
	Pixel getPixel(int x, int y);
	void setPixel(int x, int y, Pixel p);
	String getTitle();
	void setTitle(String new_title);
	boolean equals(Frame f);
	java.util.Iterator<Pixel> iterator();
	GrayFrame[] separate();	
	ColorPixel getAverage();
	String render();
}
