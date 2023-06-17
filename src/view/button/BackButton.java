package view.button;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class BackButton extends Button {
	public BackButton(JFrame f, String text) {
		super(f, text);
	}
	
	public void startActionListener(JPanel contentPane, JLabel pathField, ChooseButton chooseButton, SearchButton searchButton, LoadCsvButton loadCsv) {
        this.addActionListener(e -> {
        	contentPane.removeAll();
        	contentPane.revalidate(); 
        	contentPane.repaint();
            jFrame.add(pathField);
            jFrame.add(searchButton);
            jFrame.add(chooseButton);
            jFrame.add(loadCsv);
            jFrame.setVisible(true);
        });
	}
}
