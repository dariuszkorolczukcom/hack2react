package view;

import csv.creator.CsvCreator;
import csv.creator.CsvSaveDto;
import traverse.DataParserUtil;
import traverse.FileDataDto;
import traverse.FileTraverse;
import traverse.TableDataDto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Window {
    boolean shouldProcessSubFolders = false;
    String scanPath = "./resources";
    String csvPath = "./output.csv";
    FileTraverse fr = new FileTraverse(shouldProcessSubFolders);

    public void run() {

        JFrame f = new JFrame();
        
        JLabel pathField = new JLabel("nothing entered");
        pathField.setBounds(130, 50, 500, 40);
        
        JButton searchButton = new JButton("Sprawdź wybrany folder");
        searchButton.setBounds(130, 100, 200, 40);
        
        JButton chooseButton = new JButton("Wybierz folder");
        chooseButton.setBounds(360, 100, 200, 40);
        
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        searchButton.addActionListener(e -> {
            try {
                List<FileDataDto> fileData = fr.traverse(scanPath);

                List<CsvSaveDto> csvData = DataParserUtil.convertFileDataTo(fileData, CsvSaveDto::new);
                List<TableDataDto> tableData = DataParserUtil.convertFileDataTo(fileData, TableDataDto::new);

                CsvCreator csvCreator = new CsvCreator(csvPath, csvData);
                csvCreator.createCsv();

                TableModel tableModel = new Table(tableData);
                JTable table = new JTable(tableModel);
                
                table.setDefaultRenderer(Object.class, new TableCellRenderer());
                
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBounds(26, 46, 752, 131);

                table.setFillsViewportHeight(true);

                JPanel contentPane = new JPanel();
                contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
                f.setContentPane(contentPane);
                contentPane.setLayout(null);

                contentPane.add(scrollPane);
                scrollPane.setViewportView(table);
                f.setVisible(true);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        chooseButton.addActionListener(e -> {
                int returnVal = fc.showOpenDialog(f);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    //This is where a real application would open the file.
                    this.scanPath = file.getPath();
                    pathField.setText(this.scanPath);
                    f.setVisible(true);
                }
        });
        
        JPanel buttonPanel = new JPanel(); //use FlowLayout
        buttonPanel.add(searchButton);
        buttonPanel.add(chooseButton);

        f.add(pathField);
        f.add(searchButton);
        f.add(chooseButton);
        f.setSize(800, 500);
        f.setLayout(null);
        f.setVisible(true);
    }
}
