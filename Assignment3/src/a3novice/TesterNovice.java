package a3novice;

import java.util.Arrays;

public class TesterNovice {

	public static void main(String[] args) {
		ColorPixel a = new ColorPixel(0.3, 0.2, 0.8);
		ColorPixel b = new ColorPixel(0.1, 0.1, 0.7);
		
		System.out.println("Red value is " + a.getRed());
		
		int[][] test = new int[5][3];
		for (int[] row : test){
		    Arrays.fill(row, 6);
		}
		System.out.println("Test array elem 3,2 is " + test[3][2]);
		
		ColorFrame f = new ColorFrame(3, 3, a);
		ColorFrame ff = new ColorFrame(3,4);
		
		System.out.println("Pixel at (1,1) has red " + f.getPixel(1, 1).getRed());
		
		f.setPixel(1,1,b);
		System.out.println("Pixel at (1,1) has red " + f.getPixel(1, 1).getRed());
		
		System.out.println("Pixel in ff at (1,1) has blue " + ff.getPixel(1, 1).getBlue());
		

	}

}
