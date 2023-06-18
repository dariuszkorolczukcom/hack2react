package view.slider;

import javax.swing.JLabel;
import javax.swing.JSlider;

public class Slider extends JSlider {
	
	String name;
	JLabel yellowSliderLabel;
	
	public Slider(String name) {
		super(JSlider.HORIZONTAL, 0, 20, 10);
		this.name = name;
	    this.yellowSliderLabel = new JLabel(name);
	}
	
	public void initiate(SliderValue value, int position) {
	    yellowSliderLabel.setBounds(30, position, 100, 40);
	    this.setMajorTickSpacing(1);
	    this.setPaintTicks(true);
	    this.setBounds(130 + 100, position, 500, 40);
	    this.addChangeListener(e -> {
	    	JSlider source = (JSlider)e.getSource();
	    	if (!source.getValueIsAdjusting()) {
	    		value.setValue(Integer.valueOf(source.getValue()));
	        }
	    });
	}
	
	public JLabel getLabel() {
		return this.yellowSliderLabel;
	}
}
