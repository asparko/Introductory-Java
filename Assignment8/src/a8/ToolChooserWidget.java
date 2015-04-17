package a8;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ToolChooserWidget extends JPanel implements ItemListener {

	private JComboBox tool_choices;
	private List<ToolChoiceListener> listeners;

	private static final String[] choices = {"Pixel Inspector", "Paint Brush", "Blur Brush"};

	public ToolChooserWidget() {
		setLayout(new BorderLayout());
		add(new JLabel(" Tool: "), BorderLayout.WEST);

		tool_choices = new JComboBox(choices);
		add(tool_choices, BorderLayout.CENTER);
		tool_choices.addItemListener(this);

		listeners = new ArrayList<ToolChoiceListener>();
	}

	public void addToolChoiceListener(ToolChoiceListener l) {
		listeners.add(l);
	}

	@Override

	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) { 
			String tool_name = (String) e.getItem();

			for (ToolChoiceListener l : listeners) {
				l.toolChosen(tool_name);
			}
		}
	}

	public String getCurrentToolName() {
		return (String) tool_choices.getSelectedItem();
	}
}
