package a4novice;

public class A4NoviceTester {

	public static void main(String[] args) {
		boolean test_failed = false;

		GrayFrame g = new GrayFrame(100, 100);
		
		if (!g.getTitle().equals("untitled")) {
			System.out.println("Expected GrayFrame default title to be \"untitled\".");
			test_failed = true;
		}
		
		g.setTitle("My Title");
		if (!g.getTitle().equals("My Title")) {
			System.out.println("Setting title on GrayFrame does not seem to work");
			test_failed = true;
		}
		
		if (!g.toString().equals("Frame: My Title (100 x 100)")) {
			System.out.println("toString() output seems wrong");
			test_failed = true;
		}
		
		if (!g.equals(g)) {
			System.out.println("GrayFrame expected to be equal to itself");
			test_failed = true;
		}
		
		GrayFrame g_same = new GrayFrame(100, 100);
		
		if (!g.equals(g_same)) {
			System.out.println("GrayFrame equals() test 1 failed");
			test_failed = true;
		}
		
		g_same.setPixel(0,0,new GrayPixel(0.0));
		
		if (g.equals(g_same)) {
			System.out.println("GrayFrame equals() test 2 failed");
			test_failed = true;
		}
		
		GrayFrame g_diff = new GrayFrame(50, 100);
		
		if (g.equals(g_diff)) {
			System.out.println("GrayFrame equals() test 3 failed");
			test_failed = true;
		}
		
		g_diff = new GrayFrame(100, 50);
		
		if (g.equals(g_diff)) {
			System.out.println("GrayFrame equals() test 4 failed");
			test_failed = true;
		}
		
		ColorFrame c = new ColorFrame(100, 100);
		
		if (!c.getTitle().equals("untitled")) {
			System.out.println("Expected ColorFrame default title to be \"untitled\".");
			test_failed = true;
		}
		
		c.setTitle("My Title");
		if (!c.getTitle().equals("My Title")) {
			System.out.println("Setting title on ColorFrame does not seem to work");
			test_failed = true;
		}
		
		if (!c.toString().equals("Frame: My Title (100 x 100)")) {
			System.out.println("toString() output seems wrong");
			test_failed = true;
		}
		
		if (!c.equals(c)) {
			System.out.println("ColorFrame expected to be equal to itself");
			test_failed = true;
		}
		
		ColorFrame c_same = new ColorFrame(100, 100);
		
		if (!c.equals(c_same)) {
			System.out.println("ColorFrame equals() test 1 failed");
			test_failed = true;
		}
		
		c_same.setPixel(0,0,new ColorPixel(0.0, 0.0, 0.0));
		
		if (c.equals(c_same)) {
			System.out.println("ColorFrame equals() test 2 failed");
			test_failed = true;
		}
		
		ColorFrame c_diff = new ColorFrame(50, 100);
		
		if (c.equals(c_diff)) {
			System.out.println("ColorFrame equals() test 3 failed");
			test_failed = true;
		}
		
		c_diff = new ColorFrame(100, 50);
		
		if (c.equals(c_diff)) {
			System.out.println("ColorFrame equals() test 4 failed");
			test_failed = true;
		}
		
		if (!c.equals(g)) {
			System.out.println("Expected color frame with medium gray to equal gray frame");
			test_failed = true;
		}
		
		if (!test_failed) {
			System.out.println("All tests passed");
		}
	}
}