package a3adept;

public class A3AdeptTester {

	public static void main(String[] args) {
		
		ColorPixel p = null;
		
		boolean test_failed = false;
		
		try {
			p = new ColorPixel(1.0, 0.5, 0.25);
		}
		catch (Exception e) {
			System.out.println("ColorPixel constructor failed.");
			System.exit(-1);
		}
		
		if (!eps_compare(p.getRed(), 1.0)) {
			System.out.println("ColorPixel has wrong red value. Expected 1.0, got: " + p.getRed());
			test_failed = true;
		}
		if (!eps_compare(p.getGreen(), 0.5)) {
			System.out.println("ColorPixel has wrong green value. Expected 0.5, got: " + p.getRed());
			test_failed = true;
		}
		if (!eps_compare(p.getBlue(), 0.25)) {
			System.out.println("ColorPixel has wrong blue value. Expected 0.25, got: " + p.getRed());
			test_failed = true;
		}
		if (!eps_compare(p.getBrightness(), 0.58825)) {
			System.out.println("ColorPixel has wrong brightness. Expected 0.58825, got: " + p.getBrightness());
			test_failed = true;
		}

		
		ColorFrame f = null;
		
		try {
			f = new ColorFrame(10, 5);
		}
		catch (Exception e) {
			System.out.println("2 argument ColorFrame constructor failed.");
			System.exit(-2);
		}

		if (f.getWidth() != 10) {
			System.out.println("Incorrect width. Expected 10, got: " + f.getWidth());
			test_failed = true;
		}
		if (f.getHeight() != 5) {
			System.out.println("Incorrect width. Expected 5, got: " + f.getHeight());
			test_failed = true;
		}
		
		Pixel p_from_f = f.getPixel(0, 0);
		if ((p_from_f.getRed() != 0.5) ||
			(p_from_f.getBlue() != 0.5) ||
			(p_from_f.getGreen() != 0.5) ||
			(p_from_f.getBrightness() != 0.5)) {
			System.out.println("Expected pixel to be medium gray.");
			test_failed = true;
		}
		
		f.setPixel(1,0,p);
		
		if (f.getPixel(1,0) != p) {
			System.out.println("Set pixel at (1,0) not equal to pixel retieved from (1,0)");
			test_failed = true;
		}
		
		try {
			f.setPixel(0, 0, null);
			System.out.println("Expected exception for setting of null pixel");
			test_failed = true;
		}
		catch (Exception e) {			
		}
		
		try {
			f = new ColorFrame(5, 10, p);
		}
		catch (Exception e) {
			System.out.println("3 argument ColorFrame constructor failed.");
			System.exit(-2);
		}

		if (f.getWidth() != 5) {
			System.out.println("Incorrect width. Expected 10, got: " + f.getWidth());
			test_failed = true;
		}
		if (f.getHeight() != 10) {
			System.out.println("Incorrect width. Expected 5, got: " + f.getHeight());
			test_failed = true;
		}
		
		p_from_f = f.getPixel(0, 0);
		if ((!eps_compare(p_from_f.getRed(), p.getRed())) ||
			(!eps_compare(p_from_f.getGreen(), p.getGreen())) ||
			(!eps_compare(p_from_f.getBlue(), p.getBlue())) ||
			(!eps_compare(p_from_f.getBrightness(), p.getBrightness()))) {
			System.out.println("Expected pixel to be same as init color pixel passed to constructor.");
			test_failed = true;
		}
		
		p = new ColorPixel(1.0, 0.5, 0.0);
		
		Pixel lighter_p = p.lighten(0.3);
		
		if (lighter_p == p) {
			System.out.println("Expected lighten() to produce a new pixel, not change existing one.");
			test_failed = true;
		}
		
		if ((!eps_compare(lighter_p.getRed(), 1.0)) ||
			(!eps_compare(lighter_p.getGreen(), 0.65)) ||
			(!eps_compare(lighter_p.getBlue(), 0.3))) {
			System.out.println("Values of pixel made lighter not correct.");
			test_failed = true;
		}

		p = new ColorPixel(1.0, 0.5, 0.0);
		
		Pixel darker_p = p.darken(0.3);
		
		if (darker_p == p) {
			System.out.println("Expected darnken() to produce a new pixel, not change existing one.");
			test_failed = true;
		}
		
		if ((!eps_compare(darker_p.getRed(), 0.7)) ||
			(!eps_compare(darker_p.getGreen(), 0.35)) ||
			(!eps_compare(darker_p.getBlue(), 0.0))) {
			System.out.println("Values of pixel made lighter not correct.");
			test_failed = true;
		}
		
		ColorFrame iter_test_frame = new ColorFrame(4,3);
		ColorPixel orange = new ColorPixel(1.0, 0.6, 0.0);
		ColorPixel cyan = new ColorPixel(0.0, 1.0, 1.0);
		
		iter_test_frame.setPixel(2, 0, orange);
		iter_test_frame.setPixel(1, 1, orange);
		iter_test_frame.setPixel(0, 2, orange);
		iter_test_frame.setPixel(3, 0, cyan);
		iter_test_frame.setPixel(2, 1, cyan);
		iter_test_frame.setPixel(1, 2, cyan);
		
		java.util.Iterator<Pixel> iter = iter_test_frame.iterator();
		
		iter.next();
		iter.next();
		iter.next();
		
		Pixel ip = iter.next();
		if (!pixel_compare(ip, cyan)) {
			System.out.println("Expected 4th pixel from iterator to be cyan");
			test_failed = true;
		}
		
		iter.next();
		iter.next();
		iter.next();
		iter.next();
	
		ip = iter.next();
		if (!pixel_compare(ip, orange)) {
			System.out.println("Expected 9th pixel from iterator to be orange");
			test_failed = true;
		}
		
		iter.next();
		iter.next();
		iter.next();
		
		if (iter.hasNext()) {
			System.out.println("Expected iterator to be out of pixels");
			test_failed = true;
		}
		
		if (!test_failed) {
			System.out.println("All tests passed.");
		}
	}

	public static boolean eps_compare(double a, double b) {
		return (Math.abs(a-b) < 0.01);
	}
	
	public static boolean pixel_compare(Pixel a, Pixel b) {
		return (eps_compare(a.getRed(), b.getRed()) &&
				eps_compare(a.getGreen(), b.getGreen()) &&
				eps_compare(a.getBlue(), b.getBlue()));
	}
}