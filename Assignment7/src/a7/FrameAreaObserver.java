package a7;

public interface FrameAreaObserver extends FrameObserver {
	FrameArea getInterestArea();
	FrameObserver getWrappedObserver();
	void setInterestArea(FrameArea interest);
}
