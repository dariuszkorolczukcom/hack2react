package view;

import csv.creator.CsvCreator;
import csv.creator.CsvSaveDto;
import traverse.FileDataDto;
import traverse.FileTraverse;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import java.io.IOException;
import java.util.List;

import static traverse.DataParserUtil.provideCsvData;
import static traverse.DataParserUtil.provideTableData;

public class Window {
    boolean shouldProcessSubFolders = false;
    String scanPath = "./resources";
    String csvPath = "./output.csv";
    FileTraverse fr = new FileTraverse(shouldProcessSubFolders);

    public void run() {

        JFrame f = new JFrame();

        JButton b = new JButton("Sprawdź obecny folder");
        b.setBounds(130, 100, 100, 40);

        b.addActionListener(e -> {
            try {
                List<FileDataDto> fileData = fr.traverse(scanPath);

                List<CsvSaveDto> csvData = provideCsvData(fileData);

                CsvCreator csvCreator = new CsvCreator(csvPath, csvData);
                csvCreator.createCsv();

                TableModel tableData = new Table(provideTableData(fileData));
                JTable table = new JTable(tableData);
                
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

        f.add(b);
        f.setSize(800, 500);
        f.setLayout(null);
        f.setVisible(true);
    }
}
