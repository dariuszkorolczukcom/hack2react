package view.button;

import csv.creator.CsvCreator;
import csv.creator.CsvSaveDto;
import traverse.DataParserUtil;
import traverse.FileDataDto;
import traverse.FileTraverse;
import traverse.TableDataDto;
import view.FilePath;
import view.table.Table;
import view.table.TableCellRenderer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import java.io.IOException;
import java.util.List;

public class SearchButton extends Button {
	
	private final FilePath scanPath;
    private final String csvPath;
    
	public SearchButton(JFrame f, String text, FilePath scanPath, String csvPath) {
		super(f, text);
	    this.scanPath = scanPath;
	    this.csvPath = csvPath;
	}
	
	public void startActionListener(JPanel contentPane, JButton backButton, boolean shouldProcessSubFolders) {
	    FileTraverse fr = new FileTraverse(shouldProcessSubFolders);
        this.addActionListener(e -> {
            try {
                List<FileDataDto> fileData = fr.traverse(scanPath.getValue());

                List<CsvSaveDto> csvData = DataParserUtil.convertFileDataTo(fileData, CsvSaveDto::new);
                List<TableDataDto> tableData = DataParserUtil.convertFileDataTo(fileData, TableDataDto::new);
                 

                CsvCreator csvCreator = new CsvCreator(csvPath, csvData);
                csvCreator.createCsv();

                TableModel tableModel = new Table(tableData);
                JTable table = new JTable(tableModel);
                
                table.setDefaultRenderer(Object.class, new TableCellRenderer());
                table.getColumnModel().getColumn(0).setPreferredWidth(220);
                table.getColumnModel().getColumn(1).setPreferredWidth(120);
                table.getColumnModel().getColumn(2).setPreferredWidth(100);
                table.getColumnModel().getColumn(3).setPreferredWidth(40);
                table.getColumnModel().getColumn(4).setPreferredWidth(20);
                
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBounds(30, 30, 720, 300);

                table.setFillsViewportHeight(true);

                
                contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
                jFrame.setContentPane(contentPane);
                contentPane.setLayout(null);

                contentPane.add(scrollPane);
                contentPane.add(backButton);
                scrollPane.setViewportView(table);
                jFrame.setVisible(true);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
	}
}
