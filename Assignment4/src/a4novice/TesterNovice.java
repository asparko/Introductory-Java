package a4novice;

public class TesterNovice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GrayPixel gPix1 = new GrayPixel(0.2);
		GrayPixel gPix2 = new GrayPixel(0.215);
		
		ColorPixel cPix1 = new ColorPixel(0.2, 0.5, 0.3);
		ColorPixel cPix2 = new ColorPixel(0.2, 0.5001, 0.3);
		
		GrayFrame frame1 = new GrayFrame(5, 5, gPix1, "title1");
		GrayFrame frame2 = new GrayFrame(5,5,gPix2,"title2");
		
		ColorFrame cframe1 = new ColorFrame(5, 5, cPix1, "title1");
		ColorFrame cframe2 = new ColorFrame(5,5,cPix2,"title2");
		/*
		System.out.println("Title of frame1 is: " + frame1.getTitle());
		
		frame1.setTitle("Frame1New");
		
		System.out.println("Now, the title of frame1 is: " + frame1.getTitle());
		*/
		
		System.out.println("Are Frame1 and Frame2 equal? " + frame1.equals(frame2));
		
		System.out.println("Are cFrame1 and cFrame2 equal? " + cframe1.equals(cframe2));
		
		System.out.println(cframe1.toString());
		System.out.println(frame2.toString());
	}

}
