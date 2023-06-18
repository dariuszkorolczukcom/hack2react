package view.button;

import view.FilePath;

import javax.swing.*;


public class LoadCsvButton extends Button {

	private FilePath scanPath;
    private String csvPath;
    
	public LoadCsvButton(JFrame f, String text) {
		super(f, text);
	}
	
	public void startActionListener() {
        this.addActionListener(e -> {
        	//TODO
        });
	}
}
