package a6jedi;

class SimpleFrameObserver implements FrameObserver {
    public void update(Frame frame, FrameArea frame_area) {
    	    System.out.println(frame.toString() + " changed in Area " + frame_area.toString());
   }
}





public class TesterJedi {
	
	private static final ColorPixel RED = new ColorPixel(1.0, 0.0, 0.0);
	private static final ColorPixel BLUE = new ColorPixel(0.0, 0.0, 1.0);
	private static final ColorPixel GREEN = new ColorPixel(0.0, 1.0, 0.0);
	
	
	public static void main(String[] args) {
		//IndirectFrame if1 = new IndirectFrame();
		//IndirectFrame if2 = new IndirectFrame();
		SimpleFrameObserver o1 = new SimpleFrameObserver();
		SimpleFrameObserver o2 = new SimpleFrameObserver();
		
		ColorFrame c_f = new ColorFrame(100, 100);
		IndirectFrame i_f = new IndirectFrame(c_f, 50, 50, 10, 10);

		//c_f.registerFrameObserver(i_f);
		c_f.registerFrameObserver(o1);
		i_f.registerFrameObserver(o2);
		
		i_f.suspendNotifications();
		c_f.setPixel(40,40, new ColorPixel(1,1,1));
		c_f.setPixel(55,58, new ColorPixel(1,1,1));
		c_f.setPixel(57,52, new ColorPixel(1,1,1));
		c_f.setPixel(62,52, new ColorPixel(1,1,1));
		i_f.resumeNotifications();

		c_f.suspendNotifications();
		c_f.setPixel(5, 5, GREEN);
		c_f.setPixel(15, 15, GREEN);
		c_f.resumeNotifications();
		
		//c_f.setPixel(48,50, new ColorPixel(1,1,1));
		//c_f.unregisterFrameObserver(o1);
		
		//i_f.setPixel(0,0, new ColorPixel(0,0,0));
		/*
		ColorFrame cf = new ColorFrame(103,102, RED, "frame");
		
		FrameArea fa = new FrameArea(0,0,3,4);
		System.out.println(fa.toString());
		FrameArea test1 = new FrameArea(1,0,6,2);
		FrameArea test2 = new FrameArea(4,5,7,7);
		FrameArea test3 = null;
		/*
		try{
			FrameArea intersect1 = fa.intersect(test1);
			System.out.println(intersect1.toString());
			//FrameArea intersect2 = fa.intersect(test2);
			//System.out.println(intersect2.toString());
			//FrameArea intersect3 = fa.intersect(test3);
			//System.out.println(intersect3.toString());
		} catch (NoIntersectionException e){
			System.out.println("No Intersection!");
		}
		FrameArea union1 = fa.union(test2);
		System.out.println(union1.toString());
		
		FrameArea moved1 = fa.translate(2, -2);
		System.out.println(moved1.toString());
		
		
		/*
		GrayFrame f = new GrayFrame(3,3);
		f.setPixel(0,0, new GrayPixel(1.0));
		f.setPixel(1,0, new GrayPixel(0.15));
		f.setPixel(2,0, new GrayPixel(1.0));
		f.setPixel(0,1, new GrayPixel(0.25));
		f.setPixel(1,1, new GrayPixel(0.45));
		f.setPixel(2,1, new GrayPixel(0.25));
		f.setPixel(0,2, new GrayPixel(1.0));
		f.setPixel(1,2, new GrayPixel(0.15));
		f.setPixel(2,2, new GrayPixel(1.0));

		System.out.println(f.render());
		
		DirectFrame f2= new DirectFrame(2, 2, new GrayPixel(0.2), "Hello");
		System.out.println(f2.getHeight());
		System.out.println(f2.render());
		IndirectFrame f3 = new IndirectFrame(f2, 0, 0, 1, 1);
		System.out.println(f3.getHeight());
		
		
		
		IndirectFrame[][] tiles = cf.makeTiles(4, 3);
		System.out.println(103%4);
		System.out.println(tiles[0][0].getWidth());
		System.out.println(tiles[1][1].getWidth());
		System.out.println(tiles[2][2].getWidth());
		System.out.println(tiles[3][2].getWidth());
		
		
		System.out.println(f.getPixel(1, 1).getBrightness());
		System.out.println(f2.getPixel(1, 1).getBrightness());
		System.out.println(f2.getPixel(0, 0).getBrightness());
		f.setPixel(1, 1, new GrayPixel(0.1));
		System.out.println(f.getPixel(1, 1).getBrightness());
		System.out.println(f2.getPixel(0, 0).getBrightness());
		*/
	}

}
