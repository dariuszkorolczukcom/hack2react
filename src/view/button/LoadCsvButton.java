package view.button;

import javax.swing.JFrame;

import view.FilePath;


public class LoadCsvButton extends Button {
	
	FilePath scanPath;
    String csvPath;
    
	public LoadCsvButton(JFrame f, String text) {
		super(f, text);
	}
	
	public void startActionListener() {
        this.addActionListener(e -> {
        	//TODO
        });
	}
}
