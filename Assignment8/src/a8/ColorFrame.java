package a8;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ColorFrame extends DirectFrame {

	private static final Pixel DEFAULT_PIXEL_VALUE = new ColorPixel(0.5, 0.5, 0.5);
    	
	public ColorFrame(int width, int height, Pixel init_color, String title) {
		super(width, height, init_color, title);

		pixels = new ColorPixel[height][width];
		for (int y = 0; y<height; y++) {
			for (int x = 0; x<width; x++) {
				pixels[y][x] = init_color;
			}
		}
	}
	
	public ColorFrame(int width, int height) {
		this(width, height, ColorFrame.DEFAULT_PIXEL_VALUE, DirectFrame.DEFAULT_TITLE);
	}


	public void setPixel(int x, int y, Pixel p) {
		if (!(p instanceof ColorPixel)) {
			p = new ColorPixel(p.getRed(), p.getGreen(), p.getBlue());
		}
		
		super.setPixel(x, y, p);
	}	
	
	public static ColorFrame readFromURL(String url) throws IOException {
		BufferedImage bi = ImageIO.read(new URL(url));
		ColorFrame cf = new ColorFrame(bi.getWidth(), bi.getHeight());
		for (int x=0; x<bi.getWidth(); x++) {
			for (int y=0; y<bi.getHeight(); y++) {
				cf.setPixel(x, y, new ColorPixel(bi.getRGB(x, y)));
			}
		}
		return cf;
	}
}
