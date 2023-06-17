package view;

import csv.creator.CsvCreator;
import csv.creator.CsvSaveDto;
import traverse.FileDataDto;
import traverse.FileTraverser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import java.io.IOException;
import java.util.List;

import static traverse.DataParserUtil.provideCsvData;
import static traverse.DataParserUtil.provideTableData;

public class Window {
    FileTraverser fr = new FileTraverser();

    public void run() {
        String scanPath = "./resources";
        String csvPath = "./output.csv";
        JFrame f = new JFrame();//creating instance of JFrame

        JButton b = new JButton("Sprawdz obecny folder");//creating instance of JButton
        b.setBounds(130, 100, 100, 40);//x axis, y axis, width, height

        b.addActionListener(e -> {
            try {
                List<FileDataDto> fileData = fr.traverse(scanPath);

                List<CsvSaveDto> csvData = provideCsvData(fileData);

                CsvCreator csvCreator = new CsvCreator(csvPath, csvData);
                csvCreator.createCsv();

                TableModel tableData = new Table(provideTableData(fileData));
                JTable table = new JTable(tableData);

                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBounds(26, 46, 752, 131);

                table.setFillsViewportHeight(true);

                JPanel contentPane = new JPanel();
                contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
                f.setContentPane(contentPane);
                contentPane.setLayout(null);

                contentPane.add(scrollPane);
                scrollPane.setViewportView(table);
                f.setVisible(true);//making the frame visible
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        f.add(b);//adding button in JFrame
        f.setSize(800, 500);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
    }
}
