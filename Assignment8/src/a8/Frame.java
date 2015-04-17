package a8;

public interface Frame {
	int getWidth();
	int getHeight();
	Pixel getPixel(int x, int y);
	void setPixel(int x, int y, Pixel p);
	String getTitle();
	void setTitle(String new_title);
	boolean equals(Frame other);
	ColorPixel getAverage();
	String render();
	IndirectFrame crop(int x, int y, int width, int height);
	IndirectFrame[][] makeTiles(int num_across, int num_down);
	void registerFrameObserver(FrameObserver observer);
	void unregisterFrameObserver(FrameObserver observer);
	void registerFrameObserver(FrameObserver observer, FrameArea area_of_interest);
	void suspendNotifications();
	void resumeNotifications();
	Frame copy();

}
