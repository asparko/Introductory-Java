package a4jedi;

public class TesterJedi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GrayPixel gPix1 = new GrayPixel(0.2);
		GrayPixel gPix2 = new GrayPixel(0.215);
		
		ColorPixel cPix1 = new ColorPixel(0.2, 0.5, 0.3);
		ColorPixel cPix2 = new ColorPixel(0.21, 0.5001, 0.31);
		
		GrayFrame frame1 = new GrayFrame(5, 5, gPix1, "title1");
		GrayFrame frame2 = new GrayFrame(5,5,gPix2,"title2");
		
		ColorFrame cframe1 = new ColorFrame(5, 5, cPix1, "title1");
		ColorFrame cframe2 = new ColorFrame(5,5,cPix2,"title2");
		
		frame1.setPixel(1,2,gPix2);
		
		GrayFrame[] graySep = frame1.separate();
		GrayFrame graySep2 = graySep[0];
		System.out.println("The level of a pixel in graySep is: "+ graySep2.getPixel(1, 2).getRed()+ " " + graySep2.getPixel(1, 1).getRed());
		
		
		cframe1.setPixel(1,2,cPix2);
		
		GrayFrame[] colSep = cframe1.separate();
		GrayFrame colSepR = colSep[0];
		GrayFrame colSepG = colSep[1];
		GrayFrame colSepB = colSep[2];
		
		System.out.println("colSepR: "+ colSepR.getPixel(1, 1).getBrightness()+ " " + colSepR.getPixel(1, 2).getBrightness());
		System.out.println("colSepG: "+ colSepG.getPixel(1, 1).getBrightness()+ " " + colSepG.getPixel(1, 2).getBrightness());
		System.out.println("colSepB: "+ colSepB.getPixel(1, 1).getBrightness()+ " " + colSepB.getPixel(1, 2).getBrightness());
		/*
		System.out.println("Title of frame1 is: " + frame1.getTitle());
		
		frame1.setTitle("Frame1New");
		
		System.out.println("Now, the title of frame1 is: " + frame1.getTitle());
		
		
		System.out.println("Are Frame1 and Frame2 equal? " + frame1.equals(frame2));
		
		System.out.println("Are cFrame1 and cFrame2 equal? " + cframe1.equals(cframe2));
		
		System.out.println(cframe1.toString());
		System.out.println(frame2.toString());
		
		System.out.println(frame2.getPixel(1,3).getBlue());
		System.out.println(frame2.getPixel(1,3).getGreen());
		
		System.out.println(frame2.getPixel(1,2).getBrightness());
		frame2.setPixel(1,2,gPix1);
		System.out.println(frame2.getPixel(1,2).getBrightness());
		*/
		
	}

}
