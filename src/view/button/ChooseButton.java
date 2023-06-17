package view.button;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import view.FilePath;


public class ChooseButton extends Button {
	public ChooseButton(JFrame f, String text) {
		super(f, text);
	}
	
	public void startActionListener(JLabel pathField, FilePath scanPath) {

        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        this.addActionListener(e -> {
            	fc.showOpenDialog(jFrame);

                File file = fc.getSelectedFile();
                //This is where a real application would open the file.
                scanPath.setValue(file.getPath());
                pathField.setText(scanPath.getValue());
                jFrame.setVisible(true);

    });
	}
}
