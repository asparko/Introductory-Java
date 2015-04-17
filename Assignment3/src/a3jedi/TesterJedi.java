package a3jedi;

import java.util.Arrays;

public class TesterJedi {

	public static void main(String[] args) {
		ColorPixel a = new ColorPixel(0.3, 0.3, 0.3);
		//ColorPixel b = new ColorPixel(0.3, 0.2, 0.8);
		
		System.out.println("a brightness is " + a.getBrightness());
		/*
		System.out.println("a Red value is " + a.getRed());
		System.out.println("a Green value is " + a.getGreen());
		System.out.println("a Blue value is " + a.getBlue());
		
		System.out.println("b Red value is " + b.getRed());
		System.out.println("b Green value is " + b.getGreen());
		System.out.println("b Blue value is " + b.getBlue());
		
		 //Test lighten/darken:
		a = (ColorPixel)a.lighten(0.5);
		System.out.println("a Red value is " + a.getRed());
		System.out.println("a Green value is " + a.getGreen());
		System.out.println("a Blue value is " + a.getBlue());
		
		b = (ColorPixel)b.darken(0.01);
		System.out.println("b Red value is " + b.getRed());
		System.out.println("b Green value is " + b.getGreen());
		System.out.println("b Blue value is " + b.getBlue());
		*/
		
		//Test the iterator
		
		ColorFrame f = new ColorFrame(4, 2, a);
		/*
		PixelIterator p = (PixelIterator)f.iterator();
		
		while (p.hasNext()){
			System.out.println("Yes there's a next pixel at (" + p.getCol() +","+ p.getRow() +")");
			p.next();
		}*/
		
		ZigZagIterator z = (ZigZagIterator)f.zigzag();
		while (z.hasNext()){
			System.out.println("Yes there's a next pixel at (" + z.getCol() +","+ z.getRow() +")");
			z.next();
		}
		
	}

}
