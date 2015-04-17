package a8;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class FrameView extends Canvas implements FrameObserver {

	private Frame frame;
	private BufferedImage buffered_image;
	
	public FrameView(Frame f) {
		setFrame(f);
	}

	public void setFrame(Frame f) {
		if (frame == f) {
			return;
		}
		
		if (frame != null) {
			frame.unregisterFrameObserver(this);
		}
		
		frame = f;
		frame.registerFrameObserver(this);
		buffered_image = new BufferedImage(f.getWidth(), f.getHeight(), BufferedImage.TYPE_INT_RGB);
		this.setPreferredSize(new Dimension(f.getWidth(), f.getHeight()));
		this.setSize(new Dimension(f.getWidth(), f.getHeight()));
		update(frame, new FrameArea(0,0,f.getWidth()-1,f.getHeight()-1));		
	}
	
	public Frame getFrame() {
		return frame;
	}
	
	public void paint(Graphics g) {
		g.drawImage(buffered_image, 0, 0, this);
	}

	public void update(Frame frame, FrameArea area) {
		for (int x=area.getMinX(); x<=area.getMaxX(); x++) {
			for (int y=area.getMinY(); y<=area.getMaxY(); y++) {
				buffered_image.setRGB(x, y, frame.getPixel(x, y).toRGB());
			}
		}
		repaint();
	}
}
