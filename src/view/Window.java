package view;

import view.button.BackButton;
import view.button.ChooseButton;
import view.button.LoadCsvButton;
import view.button.SearchButton;
import view.slider.Slider;
import view.slider.SliderValue;

import javax.swing.*;

public class Window {
	
	FilePath scanPath = new FilePath("C:\\Users\\GAMING\\www\\hack2react\\resources");
    String csvPath = "./output.csv";
    SliderValue redValue = new SliderValue(SwingConstants.HORIZONTAL);
    SliderValue yellowValue = new SliderValue(SwingConstants.HORIZONTAL);
    SliderValue blueValue = new SliderValue(SwingConstants.HORIZONTAL);
    public void run() {

        JFrame f = new JFrame();
        
        JPanel contentPane = new JPanel();
        
        JLabel pathField = new JLabel("nothing entered");
        pathField.setBounds(130, 50, 500, 40);

        Slider yellowSlider = new Slider("Nazwiska", yellowValue);
        yellowSlider.initiate(200);

        Slider redSlider = new Slider("Pesele", redValue);
        redSlider.initiate(250);

        Slider blueSlider = new Slider("Adresy", blueValue);
        blueSlider.initiate(300);
        
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
        f.add(redSlider.getLabel());
        f.add(redSlider);
        f.add(yellowSlider.getLabel());
        f.add(yellowSlider);
        f.add(blueSlider.getLabel());
        f.add(blueSlider);
        f.add(loadCsv);
        f.setSize(800, 500);
        f.setLayout(null);
        f.setVisible(true);
    }
}
