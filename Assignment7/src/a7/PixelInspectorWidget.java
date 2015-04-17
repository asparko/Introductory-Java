package a7;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class PixelInspectorWidget extends JPanel implements MouseListener {

	private FrameView frame_view;
	private JLabel title_label;
	private JPanel pixel_info;
	private JLabel x_coord;
	private JLabel y_coord;
	private JLabel red;
	private JLabel green;
	private JLabel blue;
	private JLabel brightness;
	
	public PixelInspectorWidget(Frame f) {
		setLayout(new BorderLayout());
		
		frame_view = new FrameView(f);
		frame_view.addMouseListener(this); //widget is its own controller
		add(frame_view, BorderLayout.CENTER);
		
		// Bottom Title for Frame
		title_label = new JLabel(f.getTitle());
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		add(title_label, BorderLayout.SOUTH);
		
		// Holder for pixel info
		pixel_info = new JPanel();
		pixel_info.setLayout(new GridLayout(6,1));
		add(pixel_info, BorderLayout.WEST);
		
		// Initialize text info fields
		x_coord = new JLabel(" X: " + 0);
		y_coord = new JLabel(" Y: " + 0);
		red = new JLabel(" Red: 0.00");
		green = new JLabel(" Green: 0.00");
		blue = new JLabel(" Blue: 0.00");
		brightness = new JLabel(" Brightness: 0.00 ");
		
		// Add text info fields to Frame
		pixel_info.add(x_coord);
		pixel_info.add(y_coord);
		pixel_info.add(red);
		pixel_info.add(green);
		pixel_info.add(blue);
		pixel_info.add(brightness);
				
	}

	public void setDisplay(String x, String y, String r, String g, String b, String bright) {
		x_coord.setText(" X: " + x);
		y_coord.setText(" Y: " + y);
		red.setText(" Red: " + r);
		green.setText(" Green: " + g);
		blue.setText(" Blue: " + b);
		brightness.setText(" Brightness: " + bright + " ");
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		//Get Pixel at clicked location
		Pixel p = frame_view.getFrame().getPixel(e.getX(),e.getY());
		
		//Get Coordinates at clicked location and convert to strings
		String x = Integer.toString(e.getX());
		String y = Integer.toString(e.getY());
		
		//Get Pixel characteristics and convert to strings
		String r = String.format("%.2f", p.getRed());
		String g = String.format("%.2f", p.getGreen());
		String b = String.format("%.2f", p.getBlue());
		String bright = String.format("%.2f", p.getBrightness());
		
		//Send pixel info to display
		setDisplay(x, y, r, g, b, bright);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}