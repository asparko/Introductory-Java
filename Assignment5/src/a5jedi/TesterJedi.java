package a5jedi;

public class TesterJedi {
	
	private static final ColorPixel RED = new ColorPixel(1.0, 0.0, 0.0);
	private static final ColorPixel BLUE = new ColorPixel(0.0, 0.0, 1.0);
	private static final ColorPixel GREEN = new ColorPixel(0.0, 1.0, 0.0);

	public static void main(String[] args) {
		
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
		*/
		/*
		DirectFrame f2= new DirectFrame(2, 2, new GrayPixel(0.2), "Hello");
		System.out.println(f2.getHeight());
		System.out.println(f2.render());
		IndirectFrame f3 = new IndirectFrame(f2, 0, 0, 1, 1);
		System.out.println(f3.getHeight());
		*/
		ColorFrame cf = new ColorFrame(103,102, RED, "frame");
		
		IndirectFrame[][] tiles = cf.makeTiles(4, 3);
		System.out.println(103%4);
		System.out.println(tiles[0][0].getWidth());
		System.out.println(tiles[1][1].getWidth());
		System.out.println(tiles[2][2].getWidth());
		System.out.println(tiles[3][2].getWidth());
		
		/*
		System.out.println(f.getPixel(1, 1).getBrightness());
		System.out.println(f2.getPixel(1, 1).getBrightness());
		System.out.println(f2.getPixel(0, 0).getBrightness());
		f.setPixel(1, 1, new GrayPixel(0.1));
		System.out.println(f.getPixel(1, 1).getBrightness());
		System.out.println(f2.getPixel(0, 0).getBrightness());
		*/
	}

}
