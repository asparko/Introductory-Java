package a8;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class PixelInspectorUI extends JPanel implements ActionListener {

	public static Pixel reference = new ColorPixel(0.5,0.5,0.5);
	
	private JPanel info_panel;
	private JPanel setter_panel;
	
	private JLabel x_label;
	private JLabel y_label;
	private JLabel pixel_info;
	
	private JButton eyedropper;
	private FrameView color_preview;
	
	private List<PixelListener> listeners;
	
	public PixelInspectorUI() {
		x_label = new JLabel(" X: ");
		y_label = new JLabel(" Y: ");
		pixel_info = new JLabel(" (R,G,B):");
		eyedropper = new JButton("Set Paintbrush");
		color_preview = new FrameView(new ColorFrame(50,50));

		setLayout(new GridLayout(1,2));
		
		eyedropper.addActionListener(this);
		
		//Create info panel
		info_panel = new JPanel();
		info_panel.setLayout(new GridLayout(3,1));
		
		//Create setter panel
		setter_panel = new JPanel();
		setter_panel.setLayout(new BorderLayout());
		
		//Fill info panel
		info_panel.add(x_label);
		info_panel.add(y_label);
		info_panel.add(pixel_info);
		
		//Fill setter panel
		setter_panel.add(eyedropper, BorderLayout.CENTER);
		setter_panel.add(color_preview, BorderLayout.EAST);
		
		//Put info panel and setter panel into main panel
		add(info_panel);
		add(setter_panel);
		
		listeners = new ArrayList<PixelListener>();
	}
	
	public void setInfo(int x, int y, Pixel p) {
		x_label.setText(" X: " + x);
		y_label.setText(" Y: " + y);
		pixel_info.setText(String.format(" (R,G,B): (%3.2f, %3.2f, %3.2f)", p.getRed(), p.getBlue(), p.getGreen()));
		color_preview.setFrame(new ColorFrame(50,50,p,"preview"));
	}

	//Tell listeners about Pixel to use to set paint brush
	public void actionPerformed(ActionEvent e) {
		Pixel p = color_preview.getFrame().getPixel(0, 0);
		for (PixelListener l : listeners) {
			l.pixelChosen(p);
		}
	}
	
	public void addPixelListener(PixelListener l) {
		listeners.add(l);
	}
}
