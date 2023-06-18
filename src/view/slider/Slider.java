package view.slider;

import javax.swing.*;

public class Slider extends JSlider {

	private final String name;
	private final JLabel yellowSliderLabel;
	private final SliderValue value;
	
	public Slider(String name, SliderValue value) {
		super(SwingConstants.HORIZONTAL, 0, 20, 10);
		this.name = name;
		this.value = value;
	    this.yellowSliderLabel = new JLabel(name);
	}
	
	public void initiate(int position) {
	    yellowSliderLabel.setBounds(30, position, 100, 40);
	    this.setMajorTickSpacing(1);
	    this.setPaintTicks(true);
	    this.setBounds(130 + 100, position, 500, 40);
	    this.addChangeListener(e -> {
	    	Integer v = this.getValue();
	    	System.out.println(v);
	    	this.value.setValue(v);
	    });
	}
	
	public JLabel getLabel() {
		return this.yellowSliderLabel;
	}
}
