package a5test;

import static org.junit.Assert.*;

import org.junit.Test;

import a5adept.*;

public class A5AdeptTest {

	private static final ColorPixel RED = new ColorPixel(1.0, 0.0, 0.0);
	private static final ColorPixel BLUE = new ColorPixel(0.0, 0.0, 1.0);
	private static final ColorPixel GREEN = new ColorPixel(0.0, 1.0, 0.0);
	
	@Test
	public void basicIndirectTest() {
		ColorFrame cf = new ColorFrame(100, 100, RED, "source");
		
		IndirectFrame i1 = new IndirectFrame(cf, 15, 30, 10, 10);
		
		cf.setPixel(15, 30, BLUE);
		
		assertEquals(i1.getPixel(0,0), cf.getPixel(15, 30));
		assertEquals(true, cf.getAverage().getBrightness() != i1.getAverage().getBrightness());
		
		i1.setPixel(5, 5, GREEN);
		assertEquals(i1.getPixel(5,5), cf.getPixel(20, 35));
		
		assertEquals("Indirect Frame: source (10 x 10) at (15, 30)", i1.toString());
		
		assertEquals("source",  i1.getTitle());
		i1.setTitle("title changed");
		assertEquals("title changed", cf.getTitle());
		
		IndirectFrame i2 = cf.crop(10, 25, 30, 30);
		i2.setPixel(10, 10, BLUE);
		assertEquals(i1.getPixel(5, 5), i2.getPixel(10, 10));
		
		
	}
	
	@Test
	public void doubleIndirectTest() {
		ColorFrame cf = new ColorFrame(100, 100, RED, "source");
		
		IndirectFrame i1 = cf.crop(10, 10, 80, 80);
		IndirectFrame i2 = i1.crop(10, 10, 60, 60);
		
		i2.setPixel(0, 0, GREEN);
		assertEquals(i2.getPixel(0, 0), i1.getPixel(10, 10));
		assertEquals(i2.getPixel(0, 0), cf.getPixel(20, 20));
	}

}
