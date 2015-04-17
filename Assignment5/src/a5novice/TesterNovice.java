package a5novice;

public class TesterNovice {

	public static void main(String[] args) {
		
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
		
		System.out.println(f.getAverage().getRed());
		System.out.println(f.getAverage().getBlue());
		System.out.println(f.getAverage().getGreen());
		
		ColorFrame f2 = new ColorFrame(3,3);
		f2.setPixel(0,0, new ColorPixel(1.0,0.5,0.3));
		f2.setPixel(1,0, new ColorPixel(1.0,0.5,0.4));
		f2.setPixel(2,0, new ColorPixel(1.0,0.5,0.3));
		f2.setPixel(0,1, new ColorPixel(1.0,0.5,0.3));
		f2.setPixel(1,1, new ColorPixel(1.0,0.5,0.3));
		f2.setPixel(2,1, new ColorPixel(0.0,0.5,0.4));
		f2.setPixel(0,2, new ColorPixel(1.0,0.1,0.3));
		f2.setPixel(1,2, new ColorPixel(1.0,0.1,0.3));
		f2.setPixel(2,2, new ColorPixel(1.0,0.1,0.3));
		
		System.out.println(f2.render());
		
		System.out.println(f2.getWidth());
		
		System.out.println(f2.getAverage().getRed());
		System.out.println(f2.getAverage().getGreen());
		System.out.println(f2.getAverage().getBlue());

		
	}

}
