package a8;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class BlurBrushTool implements Tool {

	private BlurBrushToolUI ui;
	private ImageEditorModel model;
	private int brush_size;
	private int blur_intensity;
	
	public BlurBrushTool(ImageEditorModel model) {
		this.model = model;
		ui = new BlurBrushToolUI();
		brush_size = 5;
		blur_intensity = 1;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		brush_size=ui.getBrushSize();
		blur_intensity = ui.getBlurIntensity();
		model.blurAt(e.getX(), e.getY(), blur_intensity, brush_size);
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

	@Override
	public void mouseDragged(MouseEvent e) {
		model.blurAt(e.getX(), e.getY(), blur_intensity, brush_size);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		return "Blur Brush";
	}

	@Override
	public JPanel getUI() {
		return ui;
	}

}
