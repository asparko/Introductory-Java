package a4jedi;

public class A4JediTester {

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
		
		// Jedi-specific test
		
		ColorFrame j_color = new ColorFrame(3,3);
		
		j_color.setPixel(0, 0,  new ColorPixel(1.0, 1.0, 1.0));
		j_color.setPixel(1, 0,  new ColorPixel(1.0, 0.0, 0.0));
		j_color.setPixel(2, 0,  new ColorPixel(1.0, 0.0, 0.0));

		j_color.setPixel(0, 1,  new ColorPixel(0.0, 1.0, 0.0));
		j_color.setPixel(1, 1,  new ColorPixel(1.0, 1.0, 1.0));
		j_color.setPixel(2, 1,  new ColorPixel(0.0, 1.0, 0.0));

		j_color.setPixel(0, 2,  new ColorPixel(0.0, 0.0, 1.0));
		j_color.setPixel(1, 2,  new ColorPixel(0.0, 0.0, 1.0));
		j_color.setPixel(2, 2,  new ColorPixel(1.0, 1.0, 1.0));

		GrayFrame[] j_separates = j_color.separate();

		GrayPixel max = new GrayPixel(1.0);
		GrayPixel min = new GrayPixel(0.0);
		
		GrayFrame r_plane = new GrayFrame(3,3);
		r_plane.setPixel(0, 0, max);
		r_plane.setPixel(1, 0, max);
		r_plane.setPixel(2, 0, max);
		r_plane.setPixel(0, 1, min);
		r_plane.setPixel(1, 1, max);
		r_plane.setPixel(2, 1, min);
		r_plane.setPixel(0, 2, min);
		r_plane.setPixel(1, 2, min);
		r_plane.setPixel(2, 2, max);

		GrayFrame g_plane = new GrayFrame(3,3);
		g_plane.setPixel(0, 0, max);
		g_plane.setPixel(1, 0, min);
		g_plane.setPixel(2, 0, min);
		g_plane.setPixel(0, 1, max);
		g_plane.setPixel(1, 1, max);
		g_plane.setPixel(2, 1, max);
		g_plane.setPixel(0, 2, min);
		g_plane.setPixel(1, 2, min);
		g_plane.setPixel(2, 2, max);

		GrayFrame b_plane = new GrayFrame(3,3);
		b_plane.setPixel(0, 0, max);
		b_plane.setPixel(1, 0, min);
		b_plane.setPixel(2, 0, min);
		b_plane.setPixel(0, 1, min);
		b_plane.setPixel(1, 1, max);
		b_plane.setPixel(2, 1, min);
		b_plane.setPixel(0, 2, max);
		b_plane.setPixel(1, 2, max);
		b_plane.setPixel(2, 2, max);

		if (j_separates.length != 3) {
			System.out.println("Expected color frame to separate into 3 frames.");
			test_failed = true;
		}

		if (!j_separates[0].equals(r_plane)) {
			System.out.println("Separated red plane does not have expected values.");
			test_failed = true;
		}

		if (!j_separates[1].equals(g_plane)) {
			System.out.println("Separated green plane does not have expected values.");
			test_failed = true;
		}
		
		if (!j_separates[2].equals(b_plane)) {
			System.out.println("Separated blue plane does not have expected values.");
			test_failed = true;
		}
		
		GrayFrame[] r_plane_separated = r_plane.separate();
		
		if (r_plane_separated.length != 1) {
			System.out.println("Expected gray frame to seaprate into 1 frame.");
			test_failed = true;
		}

		if (!r_plane_separated[0].equals(r_plane)) {
			System.out.println("Expected red plane to separate into a copy.");
			test_failed = true;
		}

		if (!test_failed) {
			System.out.println("All tests passed");
		}
	}
}