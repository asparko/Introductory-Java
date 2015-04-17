package a5test;

import static org.junit.Assert.*;

import org.junit.Test;


import a5jedi.*;

public class A5JediTest {

	private static final ColorPixel RED = new ColorPixel(1.0, 0.0, 0.0);
	private static final ColorPixel BLUE = new ColorPixel(0.0, 0.0, 1.0);
	private static final ColorPixel GREEN = new ColorPixel(0.0, 1.0, 0.0);

	@Test
	public void tileTest() {
		ColorFrame cf = new ColorFrame(103, 102, RED, "frame");
		
		IndirectFrame[][] tiles = cf.makeTiles(4, 5);
		
		assertEquals(4, tiles.length);
		assertEquals(5, tiles[0].length);
		
		for (IndirectFrame[] tile_column : tiles) {
			for (IndirectFrame tile : tile_column) {
				assertEquals(true, tile != null);
			}
		}
		
		assertEquals(26, tiles[0][0].getWidth());
		assertEquals(26, tiles[1][1].getWidth());
		assertEquals(26, tiles[2][2].getWidth());
		assertEquals(25, tiles[3][3].getWidth());

		assertEquals(21, tiles[0][0].getHeight());
		assertEquals(21, tiles[1][1].getHeight());
		assertEquals(20, tiles[2][2].getHeight());
		assertEquals(20, tiles[3][3].getHeight());

		tiles[0][0].setPixel(0, 0, GREEN);
		assertEquals(GREEN, cf.getPixel(0, 0));		
		tiles[1][1].setPixel(0, 0, GREEN);
		assertEquals(GREEN, cf.getPixel(26, 21));
		tiles[2][2].setPixel(0, 0, GREEN);
		assertEquals(GREEN, cf.getPixel(52, 42));
		tiles[3][3].setPixel(0, 0, GREEN);
		assertEquals(GREEN, cf.getPixel(78, 62));
		
	}

}
