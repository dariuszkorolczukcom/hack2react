package view;

import javax.swing.*;

import traverse.FileTraverse;
import view.button.BackButton;
import view.button.ChooseButton;
import view.button.LoadCsvButton;
import view.button.SearchButton;

public class Window {
	
	FilePath scanPath = new FilePath("C:\\Users\\GAMING\\hack2react\\hack2react\\resources");
    String csvPath = "./output.csv";
    Integer redValue;
    Integer yellowValue;
    public void run() {

        JFrame f = new JFrame();
        
        JPanel contentPane = new JPanel();
        
        JLabel pathField = new JLabel("nothing entered");
        pathField.setBounds(130, 50, 500, 40);

        JSlider redSlider = new JSlider(JSlider.HORIZONTAL, 0, 20, 10);

        JSlider yellowSlider = new JSlider(JSlider.HORIZONTAL, 0, 20, 10);
        
        redSlider.addChangeListener(e -> {
        	JSlider source = (JSlider)e.getSource();
        	if (!source.getValueIsAdjusting()) {
        		redValue = Integer.valueOf(source.getValue());
            }
        });
        redSlider.setMajorTickSpacing(1);
        redSlider.setPaintTicks(true);
        redSlider.setBounds(130, 250, 500, 40);
        
        yellowSlider.addChangeListener(e -> {
        	JSlider source = (JSlider)e.getSource();
        	if (!source.getValueIsAdjusting()) {
        		yellowValue = Integer.valueOf(source.getValue());
            }
        });
        yellowSlider.setMajorTickSpacing(1);
        yellowSlider.setPaintTicks(true);
        yellowSlider.setBounds(130, 200, 500, 40);
        
        SearchButton searchButton = new SearchButton(f, "Sprawdź wybrany folder", scanPath, csvPath);
        searchButton.setBounds(30, 100, 200, 40);
        
        ChooseButton chooseButton = new ChooseButton(f, "Wybierz folder");
        chooseButton.setBounds(260, 100, 200, 40);
        
        LoadCsvButton loadCsv = new LoadCsvButton(f, "Wgraj plik");
        loadCsv.setBounds(490, 100, 200, 40);
        
        BackButton backButton = new BackButton(f, "Wróć");
        backButton.setBounds(550, 360, 200, 40);

        
        searchButton.startActionListener(contentPane, backButton, false);
        chooseButton.startActionListener(pathField, scanPath);
        backButton.startActionListener(contentPane, pathField, chooseButton, searchButton, loadCsv);
        

        
        JPanel buttonPanel = new JPanel(); //use FlowLayout
        buttonPanel.add(searchButton);
        buttonPanel.add(chooseButton);

        f.add(pathField);
        f.add(searchButton);
        f.add(chooseButton);
        f.add(redSlider);
        f.add(yellowSlider);
//        f.add(loadCsv);
        f.setSize(800, 500);
        f.setLayout(null);
        f.setVisible(true);
    }
}
