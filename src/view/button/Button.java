package view.button;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Button extends JButton {
    JFrame jFrame;
	public Button(JFrame f, String text) {
		super(text);
		this.jFrame = f;
	}
}
