package a6jedi;

public interface FrameAreaObserver extends FrameObserver {
	FrameArea getInterestArea();
	FrameObserver getWrappedObserver();
	void setInterestArea(FrameArea interest);
}
