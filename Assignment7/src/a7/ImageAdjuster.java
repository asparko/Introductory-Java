package a7;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImageAdjuster {
	public static void main(String[] args) throws IOException {
		Frame f = A7Helper.readFromURL("https://farm4.staticflickr.com/3758/12053475244_3f189e7c38_z.jpg");
		f.setTitle(" FRANKENSTEIN THE CAT!");
		ImageAdjusterWidget adjuster_widget = new ImageAdjusterWidget(f);
		
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Assignment 7 Image Adjuster");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		top_panel.add(adjuster_widget, BorderLayout.CENTER);
		main_frame.setContentPane(top_panel);

		main_frame.pack();
		main_frame.setVisible(true);
	}
}