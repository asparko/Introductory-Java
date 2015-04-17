package a7;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.util.Arrays;

public class ImageAdjusterWidget extends JPanel implements ChangeListener {

	private FrameView frame_view;
	private JPanel sliders;
	private JSlider blur;
	private JSlider saturation;
	private JSlider brightness;
	private JLabel blurLab;
	private JLabel satLab;
	private JLabel briLab;
	private Frame original;
	
	public ImageAdjusterWidget(Frame f) throws IOException{

		original = A7Helper.readFromURL("https://farm4.staticflickr.com/3758/12053475244_3f189e7c38_z.jpg");

		setLayout(new BorderLayout());
		
		frame_view = new FrameView(f);
		add(frame_view, BorderLayout.CENTER);
		
		// Create holder for set of sliders
		sliders = new JPanel();
		sliders.setLayout(new GridLayout(6,1));
		add(sliders, BorderLayout.SOUTH);

		// Initialize sliders
		blur = new JSlider(0,5,0);
		blur.setMajorTickSpacing(1);
		blur.setSnapToTicks(true);
		blur.setPaintLabels(true);
		blur.setPaintTicks(true);
		
		saturation = new JSlider(-100,100,0);
		saturation.setMajorTickSpacing(25);
		saturation.setPaintLabels(true);
		saturation.setPaintTicks(true);
		
		brightness = new JSlider(-100,100,0);
		brightness.setMajorTickSpacing(25);
		brightness.setPaintLabels(true);
		brightness.setPaintTicks(true);

		
		//Initialize slider labels
		blurLab = new JLabel("BLUR");
		blurLab.setHorizontalAlignment(SwingConstants.CENTER);
		satLab = new JLabel("SATURATION");
		satLab.setHorizontalAlignment(SwingConstants.CENTER);
		briLab = new JLabel("BRIGHTNESS");
		briLab.setHorizontalAlignment(SwingConstants.CENTER);
		
		// Add sliders and their labels to Panel	
		sliders.add(blurLab);
		sliders.add(blur);
		sliders.add(satLab);
		sliders.add(saturation);
		sliders.add(briLab);
		sliders.add(brightness);	
		
		// Have this widget listen to sliders
		blur.addChangeListener(this);
		saturation.addChangeListener(this);
		brightness.addChangeListener(this);
				
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		
		JSlider source = (JSlider)e.getSource();
		
		if (!source.getValueIsAdjusting()){	
			//Find values of each slider
			int blurVal = (int)blur.getValue();
			double saturateVal = (double)saturation.getValue();
			double brightVal = (double)brightness.getValue();
			
			if (source.equals(blur)){
				blurFrame(blurVal);
				if (saturateVal>0 | saturateVal<0) {
					saturateFrame(saturateVal);
				}
				if (brightVal>0 | brightVal<0) {
					brightenFrame(brightVal);
				}				
			} else if (source.equals(saturation)){
				blurFrame(blurVal);
				saturateFrame(saturateVal);
				if (brightVal>0 | brightVal<0) {
					brightenFrame(brightVal);
				}	
			} else if (source.equals(brightness)){
				blurFrame(blurVal);
				brightenFrame(brightVal);
				if (saturateVal>0 | saturateVal<0) {
					saturateFrame(saturateVal);
				}
			}
		}
	}
	
	private void blurFrame(int value){
		//Access referenced frame and its data
		Frame frame = frame_view.getFrame();
		int height = original.getHeight();
		int width = original.getWidth();

		for (int x=0; x<width; x++){
			for (int y=0; y<height; y++){
				
				if (!(value==0)){
				//If need to blur, get dims of area to be averaged:
				
					//First, ensure pixels to be averaged are inside frame
					int xTop = Math.max(0, x-value);
					int yTop = Math.max(0, y-value);
					int xBott = Math.min(width, x+value);
					int yBott = Math.min(height, y+value);
				
					//Determine size of area to be averaged:
					int widthToAvg = xBott-xTop;
					int heightToAvg = yBott-yTop;
				
					//Take average of appropriate number of pixels:
					IndirectFrame areaToAvg = new IndirectFrame(original,xTop, yTop, widthToAvg, heightToAvg);
					original.unregisterFrameObserver(areaToAvg);
					Pixel newPixel = areaToAvg.getAverage();
					frame.setPixel(x,y, newPixel);
					
				} else {
					//If removing all blur, set all pixels to original pixel values
					frame.setPixel(x, y, original.getPixel(x, y));
				}
			}
		}		
	}
	
	private void saturateFrame(double value){
		//Access referenced frame and its dimensions
		Frame frame = frame_view.getFrame();
		int height = original.getHeight();
		int width = original.getWidth();
		
		//Initialize new RGB variables
		double newR;
		double newG;
		double newB;
				
		for (int x=0; x<width; x++){
			for (int y=0; y<height; y++){
				//Pixel p = original.getPixel(x,y);
				Pixel p = frame.getPixel(x,y);
				double r = p.getRed();
				double b = p.getBlue();
				double g = p.getGreen();
				double br = p.getBrightness();
				if (value < 0){
					newR = r * (1.0  + (value/100)) - (br*value / 100);
					newG = g * (1.0  + (value/100)) - (br*value / 100);
					newB = b * (1.0  + (value/100)) - (br*value / 100);
				} else {
					double[] rgb = {r, g, b};
					Arrays.sort(rgb);
					double large = rgb[2];
					double gain = (large + ((1.0 - large)*(value / 100.0))) / large;
					newR = r*gain;
					newG = g*gain;
					newB = b*gain;
				}
				frame.setPixel(x, y, new ColorPixel(newR,newG,newB));
			}
		}
	}
	
	private void brightenFrame(double value){
		//Access referenced frame and its data
		Frame frame = frame_view.getFrame();
		int height = original.getHeight();
		int width = original.getWidth();
		
		//Initialize new RGB variables
		double newR;
		double newG;
		double newB;
		
		for (int x=0; x<width; x++){
			for (int y=0; y<height; y++){
				//Pixel p = original.getPixel(x,y);
				Pixel p = frame.getPixel(x,y);
				double r = p.getRed();
				double b = p.getBlue();
				double g = p.getGreen();
				if (value < 0){
					newR = r + (r/100)*value;
					newG = g + (g/100)*value;
					newB = b + (b/100)*value;
				} else {
					newR = r + ((1-r)/100)*value;
					newG = g + ((1-g)/100)*value;
					newB = b + ((1-b)/100)*value;
				}
				frame.setPixel(x, y, new ColorPixel(newR,newG,newB));
			}	
		}
	}

}