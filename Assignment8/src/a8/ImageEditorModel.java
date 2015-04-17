package a8;

public class ImageEditorModel {

	private Frame original;
	private Frame current;
	
	public ImageEditorModel(Frame f) {
		original = f;
		current = original.copy();
	}

	public Frame getCurrent() {
		return current;
	}

	public Pixel getPixel(int x, int y) {
		return current.getPixel(x, y);
	}

	public void paintAt(int x, int y, Pixel brushColor, int brush_size) {
		current.suspendNotifications();
		
		for (int xpos=x-brush_size+1; xpos <=x+brush_size-1; xpos++) {
			for (int ypos=y-brush_size+1; ypos <=y+brush_size-1; ypos++) {
				if (xpos >= 0 &&
					xpos < current.getWidth() &&
					ypos >= 0 &&
					ypos < current.getHeight()) {
					current.setPixel(xpos, ypos, brushColor);
				}
			}
		}
		current.resumeNotifications();
	}
	
	public void blurAt(int x, int y, int blur_intensity, int brush_size) {
		current.suspendNotifications();

		//Cycle through pixels brush touches, ensuring they are inside frame
		for (int xpos=Math.max(0, x-brush_size+1); xpos < Math.min(current.getWidth(), x+brush_size-1); xpos++) {
			for (int ypos=Math.max(0, y-brush_size+1); ypos < Math.min(current.getHeight(), y+brush_size-1); ypos++) {
				
				//Get dims of area to be averaged:
				//First, ensure pixels to be averaged are inside frame
				int xTop = Math.max(0, xpos-blur_intensity);
				int yTop = Math.max(0, ypos-blur_intensity);
				int xBott = Math.min(current.getWidth(), xpos+blur_intensity);
				int yBott = Math.min(current.getHeight(), ypos+blur_intensity);
			
				//Determine size of area to be averaged:
				int widthToAvg = xBott-xTop;
				int heightToAvg = yBott-yTop;
				
				//Take average of appropriate number of pixels:
				IndirectFrame areaToAvg = new IndirectFrame(current,xTop, yTop, widthToAvg, heightToAvg);
				current.unregisterFrameObserver(areaToAvg);
				Pixel newPixel = areaToAvg.getAverage();
				current.setPixel(xpos,ypos, newPixel);
			}
		}
		current.resumeNotifications();
	}
}
