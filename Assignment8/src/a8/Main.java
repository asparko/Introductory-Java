package a8;

public class Main {
	public static void main(String[] args) {
		SimpleFrameObserver o1 = new SimpleFrameObserver();
		SimpleFrameObserver o2 = new SimpleFrameObserver();

		ColorFrame c_f = new ColorFrame(100, 100);
		IndirectFrame i_f = new IndirectFrame(c_f, 50, 50, 10, 10);

		c_f.registerFrameObserver(o1);
		i_f.registerFrameObserver(o2);
		
		i_f.suspendNotifications();
		c_f.setPixel(40,40, new ColorPixel(1,1,1));
		c_f.setPixel(55,58, new ColorPixel(1,1,1));
		c_f.setPixel(57,52, new ColorPixel(1,1,1));
		c_f.setPixel(62,52, new ColorPixel(1,1,1));
		i_f.resumeNotifications();		
	}
}

class SimpleFrameObserver implements FrameObserver {
	public void update(Frame frame, FrameArea frame_area) {
		System.out.println(frame.toString() + " changed in area " + frame_area.toString());
	}
}