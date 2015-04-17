package a6jedi;

public class FrameAreaObserverImpl implements FrameAreaObserver {

	private FrameObserver observer;
	private FrameArea interest;
	
	public FrameAreaObserverImpl(FrameObserver observer, FrameArea interest){
		this.observer = observer;
		this.interest = interest;
	}
	
	@Override
	public void update(Frame frame, FrameArea area) {
		observer.update(frame, area);
	}

	@Override
	public FrameArea getInterestArea() {
		return interest;
	}

	@Override
	public FrameObserver getWrappedObserver() {
		return observer;
	}
	
	@Override
	public void setInterestArea(FrameArea interest) {
		this.interest = interest;
	}

}
