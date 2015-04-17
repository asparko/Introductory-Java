package a8;

public class FrameAreaObserverImpl implements FrameAreaObserver {

	private FrameObserver observer;
	private FrameArea area_of_interest;
	
	public FrameAreaObserverImpl(FrameObserver observer, FrameArea area_of_interest) {
		this.observer = observer;
		this.area_of_interest = area_of_interest;
	}

	@Override
	public void update(Frame frame, FrameArea area) {
		try {
			FrameArea intersection = area.intersect(area_of_interest);
			observer.update(frame, area);
		}
		catch (NoIntersectionException e) {			
		}
	}

	@Override
	public FrameObserver getObserver() {
		return observer;
	}

}
