package view.slider;

import javax.swing.*;

public class Slider extends JSlider {
	private final String name;
	private final JLabel sliderLabel;

	public Slider(String name) {
		super(SwingConstants.HORIZONTAL, 0, 20, 10);
		this.name = name;
		this.sliderLabel = new JLabel(name);
	}

	public void initiate(SliderValue value, int position) {
		sliderLabel.setBounds(30, position, 100, 40);
		this.setMajorTickSpacing(1);
		this.setPaintTicks(true);
		this.setBounds(130 + 100, position, 500, 40);
		this.addChangeListener(e -> {
			JSlider source = (JSlider)e.getSource();
			if (!source.getValueIsAdjusting()) {
				value.setValue(source.getValue());
			}
		});
	}

	public JLabel getLabel() {
		return this.sliderLabel;
	}
}