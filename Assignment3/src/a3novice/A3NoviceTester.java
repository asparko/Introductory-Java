package a3novice;

public class A3NoviceTester {

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
		
		if (!test_failed) {
			System.out.println("All tests passed.");
		}
	}

	public static boolean eps_compare(double a, double b) {
		return (Math.abs(a-b) < 0.01);
	}
}