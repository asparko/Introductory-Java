package a7;

public interface Frame {
	//Getters
	int getWidth();
	int getHeight();
	Pixel getPixel(int x, int y);
	String getTitle();
	ColorPixel getAverage();

	//Setters
	void setPixel(int x, int y, Pixel p);
	void setTitle(String new_title);
	
	//Misc
	String toString();
	boolean equals(Frame f);
	String render();
	IndirectFrame crop(int x, int y, int width, int height);
	IndirectFrame[][] makeTiles(int num_across, int num_down);
	
	//Observer/Observable methods
	void registerFrameObserver(FrameObserver observer);
	void registerFrameObserver(FrameObserver observer, FrameArea area_of_interest);
	void unregisterFrameObserver(FrameObserver observer);
	void suspendNotifications();
	void resumeNotifications();
	
	java.util.Iterator<Pixel> iterator();
	
}
