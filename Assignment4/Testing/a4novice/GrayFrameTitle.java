package a4novice;

import static org.junit.Assert.*;

import org.junit.Test;

public class GrayFrameTitle {

	@Test
	public void testUnititled() {
		GrayFrame g = new GrayFrame(100, 100);
		assertEquals("untitled", g.getTitle());
	}
	
	@Test
	public void setTitle() {
		GrayFrame g = new GrayFrame(100, 100);
		g.setTitle("My Title");
		assertEquals("My Title", g.getTitle());
	}
	
	@Test
	public void testToString() {
		GrayFrame g = new GrayFrame(100, 100);
		g.setTitle("My Title");
		assertEquals("Frame: My Title (100 x 100)", g.toString());
	}

}
