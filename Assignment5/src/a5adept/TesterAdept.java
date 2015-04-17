package a5adept;

public class TesterAdept {

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
		
		IndirectFrame f2= new IndirectFrame(f,1,1,2,2);
		System.out.println(f2.render());
		
		System.out.println(f.getPixel(1, 1).getBrightness());
		System.out.println(f2.getPixel(1, 1).getBrightness());
		System.out.println(f2.getPixel(0, 0).getBrightness());
		f.setPixel(1, 1, new GrayPixel(0.1));
		System.out.println(f.getPixel(1, 1).getBrightness());
		System.out.println(f2.getPixel(0, 0).getBrightness());
		
	}

}
