package a8;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BlurBrushToolUI extends JPanel implements ChangeListener{

	private JSlider size_slider;
	private JSlider intensity_slider;
	private FrameView color_preview;
	
	public BlurBrushToolUI() {
		setLayout(new GridLayout(0,1));
		
		JPanel slider_panel = new JPanel();
		slider_panel.setLayout(new GridLayout(0,1));
		
		JPanel size_slider_panel = new JPanel();
		JLabel size_label = new JLabel("Brush Size:");
		size_slider_panel.setLayout(new BorderLayout());
		size_slider_panel.add(size_label, BorderLayout.WEST);
		size_slider = new JSlider(1,10);
		size_slider.setMajorTickSpacing(1);
		size_slider.setPaintTicks(true);
		size_slider.setPaintLabels(true);
		size_slider.setSnapToTicks(true);
		size_slider.addChangeListener(this);
		size_slider_panel.add(size_slider, BorderLayout.CENTER);
		
		JPanel intensity_slider_panel = new JPanel();
		JLabel intensity_label = new JLabel("Blur Intensity:");
		intensity_slider_panel.setLayout(new BorderLayout());
		intensity_slider_panel.add(intensity_label, BorderLayout.WEST);
		intensity_slider = new JSlider(1,10);
		intensity_slider.setMajorTickSpacing(1);
		intensity_slider.setPaintTicks(true);
		intensity_slider.setPaintLabels(true);
		intensity_slider.setSnapToTicks(true);
		intensity_slider.addChangeListener(this);
		intensity_slider_panel.add(intensity_slider, BorderLayout.CENTER);

		// Assumes intensity label is widest and asks other label
		// to be the same size.
		Dimension d = intensity_label.getPreferredSize();
		size_label.setPreferredSize(d);
		
		slider_panel.add(size_slider_panel);
		slider_panel.add(intensity_slider_panel);

		add(slider_panel);
		
		stateChanged(null);
	}

	@Override
	public void stateChanged(ChangeEvent e) {

	} 
	
	public int getBlurIntensity() {
		return intensity_slider.getValue();
	}
	
	public int getBrushSize() {
		return size_slider.getValue();
	}

}
