package view;

import javax.swing.*;

import view.button.BackButton;
import view.button.ChooseButton;
import view.button.LoadCsvButton;
import view.button.SearchButton;

public class Window {
	
	FilePath scanPath = new FilePath("C:\\Users\\GAMING\\www\\hack2react\\resources");
    String csvPath = "./output.csv";
    Integer redValue;
    Integer yellowValue;
    Integer blueValue;
    public void run() {

        JFrame f = new JFrame();
        
        JPanel contentPane = new JPanel();
        
        JLabel pathField = new JLabel("nothing entered");
        pathField.setBounds(130, 50, 500, 40);



        

        JSlider yellowSlider = new JSlider(JSlider.HORIZONTAL, 0, 20, 10);
        JLabel yellowSliderLabel = new JLabel("Pesele");
        yellowSliderLabel.setBounds(30, 200, 100, 40);
        yellowSlider.setMajorTickSpacing(1);
        yellowSlider.setPaintTicks(true);
        yellowSlider.setBounds(130, 200, 500, 40);
        yellowSlider.addChangeListener(e -> {
        	JSlider source = (JSlider)e.getSource();
        	if (!source.getValueIsAdjusting()) {
        		yellowValue = Integer.valueOf(source.getValue());
            }
        });

        JSlider redSlider = new JSlider(JSlider.HORIZONTAL, 0, 20, 10);
        JLabel redSliderLabel = new JLabel("Nazwiska");
        redSliderLabel.setBounds(30, 250, 100, 40);
        redSlider.setMajorTickSpacing(1);
        redSlider.setPaintTicks(true);
        redSlider.setBounds(130, 250, 500, 40);
        redSlider.addChangeListener(e -> {
        	JSlider source = (JSlider)e.getSource();
        	if (!source.getValueIsAdjusting()) {
        		redValue = Integer.valueOf(source.getValue());
            }
        });

        JSlider blueSlider = new JSlider(JSlider.HORIZONTAL, 0, 20, 10);
        JLabel blueSliderLabel = new JLabel("Adresy");
        blueSliderLabel.setBounds(30, 300, 100, 40);
        blueSlider.setMajorTickSpacing(1);
        blueSlider.setPaintTicks(true);
        blueSlider.setBounds(130,300, 500, 40);
        blueSlider.addChangeListener(e -> {
        	JSlider source = (JSlider)e.getSource();
        	if (!source.getValueIsAdjusting()) {
        		blueValue = Integer.valueOf(source.getValue());
            }
        });
        
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
        f.add(redSliderLabel);
        f.add(redSlider);
        f.add(yellowSliderLabel);
        f.add(yellowSlider);
        f.add(blueSliderLabel);
        f.add(blueSlider);
//        f.add(loadCsv);
        f.setSize(800, 500);
        f.setLayout(null);
        f.setVisible(true);
    }
}
