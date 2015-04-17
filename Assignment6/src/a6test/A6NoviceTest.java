package a6test;

import static org.junit.Assert.*;

import org.junit.Test;

import a6novice.*;

public class A6NoviceTest {

	@Test
	public void basicFrameAreaTest() {
		FrameArea fa1 = new FrameArea(25,26,27,28);

		assertEquals(25, fa1.getMinX());
		assertEquals(26, fa1.getMinY());
		assertEquals(27, fa1.getMaxX());
		assertEquals(28, fa1.getMaxY());
	}

	@Test
	public void translateFrameAreaTest() {
		FrameArea fa1 = new FrameArea(0,0, 100, 100);
		FrameArea fa2 = fa1.translate(-10, 10);

		assertEquals(0, fa1.getMinX());
		assertEquals(0, fa1.getMinY());
		assertEquals(100, fa1.getMaxX());
		assertEquals(100, fa1.getMaxY());

		assertEquals(-10, fa2.getMinX());
		assertEquals(10, fa2.getMinY());
		assertEquals(90, fa2.getMaxX());
		assertEquals(110, fa2.getMaxY());

	}

	@Test
	public void unionFrameAreaTest() {
		FrameArea fa1 = new FrameArea(0,0, 100, 100);
		FrameArea fa2 = new FrameArea(50, 50, 150, 150);

		FrameArea u1 = fa1.union(fa2);
		assertEquals(0, u1.getMinX());
		assertEquals(0, u1.getMinY());
		assertEquals(150, u1.getMaxX());
		assertEquals(150, u1.getMaxY());

		FrameArea u2 = fa2.union(fa1);
		assertEquals(true, u1.getMinX() == u2.getMinX());
		assertEquals(true, u1.getMaxX() == u2.getMaxX());
		assertEquals(true, u1.getMinY() == u2.getMinY());
		assertEquals(true, u1.getMaxY() == u2.getMaxY());

		FrameArea fa3 = new FrameArea(15, 15, 25, 25);
		FrameArea u3 = fa1.union(fa3);
		assertEquals(true, u3.getMinX() == fa1.getMinX());
		assertEquals(true, u3.getMaxX() == fa1.getMaxX());
		assertEquals(true, u3.getMinY() == fa1.getMinY());
		assertEquals(true, u3.getMaxY() == fa1.getMaxY());
		
		u3 = fa3.union(fa1);
		assertEquals(true, u3.getMinX() == fa1.getMinX());
		assertEquals(true, u3.getMaxX() == fa1.getMaxX());
		assertEquals(true, u3.getMinY() == fa1.getMinY());
		assertEquals(true, u3.getMaxY() == fa1.getMaxY());
		
		FrameArea fa4 = new FrameArea(200, 200, 250, 250);
		FrameArea u4 = fa4.union(fa1);
		assertEquals(0, u4.getMinX());
		assertEquals(0, u4.getMinY());
		assertEquals(250, u4.getMaxX());
		assertEquals(250, u4.getMaxY());
		
	}

	@Test
	public void intersectFrameAreaTest() {
		try {
			FrameArea fa1 = new FrameArea(0,0, 100, 100);
			FrameArea fa2 = new FrameArea(50, 50, 150, 150);

			FrameArea i1 = fa1.intersect(fa2);
			assertEquals(50, i1.getMinX());
			assertEquals(50, i1.getMinY());
			assertEquals(100, i1.getMaxX());
			assertEquals(100, i1.getMaxY());

			FrameArea i2 = fa2.intersect(fa1);
			assertEquals(true, i1.getMinX() == i2.getMinX());
			assertEquals(true, i1.getMaxX() == i2.getMaxX());
			assertEquals(true, i1.getMinY() == i2.getMinY());
			assertEquals(true, i1.getMaxY() == i2.getMaxY());
		}
		catch (NoIntersectionException e) {
			fail("Should not be generating an exception.");
		}
	}

	@Test
	public void noIntersectionExceptionTest() {
		try {
			FrameArea fa1 = new FrameArea(0, 0, 5, 5);
			FrameArea fa2 = new FrameArea(6, 6, 10, 10);

			FrameArea intersection = fa1.intersect(fa2);
			fail("NoIntersectionException should have been thrown.");
		}
		catch (NoIntersectionException e) {
			// All good here, this is what was expected.
		}
		catch (Exception e) {
			fail("Exception thrown was not instance of NoIntersectionException");
		}
	}

}
