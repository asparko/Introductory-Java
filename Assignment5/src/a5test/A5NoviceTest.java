package a5test;

import static org.junit.Assert.*;

import org.junit.Test;

import a5novice.*;

public class A5NoviceTest {

	
	@Test
	public void asCharTest() {
		ColorPixel cp = new ColorPixel(0.05, 0.05, 0.05);
		assertEquals(' ', cp.asChar());		
		cp = new ColorPixel(0.15, 0.15, 0.15);
		assertEquals('.', cp.asChar());
		cp = new ColorPixel(0.25, 0.25, 0.25);
		assertEquals(':', cp.asChar());
		cp = new ColorPixel(0.35, 0.35, 0.35);
		assertEquals('*', cp.asChar());
		cp = new ColorPixel(0.45, 0.45, 0.45);
		assertEquals('+', cp.asChar());
		cp = new ColorPixel(0.55, 0.55, 0.55);
		assertEquals('?', cp.asChar());
		cp = new ColorPixel(0.65, 0.65, 0.65);
		assertEquals('X', cp.asChar());
		cp = new ColorPixel(0.75, 0.75, 0.75);
		assertEquals('%', cp.asChar());
		cp = new ColorPixel(0.85, 0.85, 0.85);
		assertEquals('#', cp.asChar());
		cp = new ColorPixel(0.95, 0.95, 0.95);
		assertEquals('@', cp.asChar());
		
		GrayPixel gp = new GrayPixel(0.05);
		assertEquals(' ', gp.asChar());		
		gp = new GrayPixel(0.15);
		assertEquals('.', gp.asChar());
		gp = new GrayPixel(0.25);
		assertEquals(':', gp.asChar());
		gp = new GrayPixel(0.35);
		assertEquals('*', gp.asChar());
		gp = new GrayPixel(0.45);
		assertEquals('+', gp.asChar());
		gp = new GrayPixel(0.55);
		assertEquals('?', gp.asChar());
		gp = new GrayPixel(0.65);
		assertEquals('X', gp.asChar());
		gp = new GrayPixel(0.75);
		assertEquals('%', gp.asChar());
		gp = new GrayPixel(0.85);
		assertEquals('#', gp.asChar());
		gp = new GrayPixel(0.95);
		assertEquals('@', gp.asChar());

	}
	
	@Test
	public void getAverageTest() {
		GrayFrame gf = new GrayFrame(10,10,new GrayPixel(1.0), "average test frame");
		
		GrayPixel zero_pixel = new GrayPixel(0.0);
		GrayPixel half_pixel = new GrayPixel(0.5);
		GrayPixel qtr_pixel = new GrayPixel(0.25);

		paint_row(gf, 0, zero_pixel);
		paint_row(gf, 1, zero_pixel);
		paint_row(gf, 2, half_pixel);
		paint_row(gf, 3, half_pixel);
		paint_row(gf, 4, qtr_pixel);
		paint_row(gf, 5, qtr_pixel);
		
		assertEquals(0.55, gf.getAverage().getBrightness(), 0.01);
		
		double pixel_sum = 0.0;
		for (int x=0; x<10; x++) {
			for (int y=0; y<10; y++) {
				double rnd_val = Math.random();
				GrayPixel random_gp = new GrayPixel(rnd_val);
	
				pixel_sum += rnd_val;				
				gf.setPixel(x, y, random_gp);
			}
		}
		
		assertEquals(pixel_sum/100.0, gf.getAverage().getBrightness(), 0.01);
	}
	
	@Test
	public void renderTest() {
		ColorFrame cf = new ColorFrame(4,4);
		
		cf.setPixel(0, 0, new ColorPixel(1.0, 0.7, 0.2));
		cf.setPixel(1, 0, new ColorPixel(0.6, 0.23, 0.8));
		cf.setPixel(2, 0, new ColorPixel(0.0, 0.3, 1.0));
		cf.setPixel(3, 0, new ColorPixel(1.0, 0.8, 0.9));
		cf.setPixel(0, 1, new ColorPixel(1.0, 0.7, 0.2));
		cf.setPixel(1, 1, new ColorPixel(0.6, 0.23, 0.8));
		cf.setPixel(2, 1, new ColorPixel(0.0, 0.3, 1.0));
		cf.setPixel(3, 1, new ColorPixel(1.0, 0.8, 0.9));
		cf.setPixel(0, 2, new ColorPixel(1.0, 0.7, 0.2));
		cf.setPixel(1, 2, new ColorPixel(0.6, 0.23, 0.8));
		cf.setPixel(2, 2, new ColorPixel(0.0, 0.3, 1.0));
		cf.setPixel(3, 2, new ColorPixel(1.0, 0.8, 0.9));
		cf.setPixel(0, 3, new ColorPixel(1.0, 0.7, 0.2));
		cf.setPixel(1, 3, new ColorPixel(0.6, 0.23, 0.8));
		cf.setPixel(2, 3, new ColorPixel(0.0, 0.3, 1.0));
		cf.setPixel(3, 3, new ColorPixel(1.0, 0.8, 0.9));

		assertEquals("%*:#\n%*:#\n%*:#\n%*:#", cf.render());
	}
	
	private static void paint_row(Frame f, int ridx, Pixel p) {
		for (int x=0; x<f.getWidth(); x++) {
			f.setPixel(x, ridx, p);
		}
	}

}
