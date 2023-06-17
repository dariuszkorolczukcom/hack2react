package view;

import traverse.FileReader;
import traverse.TableDataDto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class Window {
    FileReader fr = new FileReader();

    public void run() {
        JFrame f = new JFrame();//creating instance of JFrame

        JButton b = new JButton("Sprawdz obecny folder");//creating instance of JButton
        b.setBounds(130, 100, 100, 40);//x axis, y axis, width, height

        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<TableDataDto> data = fr.traverse();
                    TableModel tableData = new Table(data);
                    JTable table = new JTable(tableData);

                    JScrollPane scrollPane = new JScrollPane(table);
                    scrollPane.setBounds(26, 46, 352, 131);

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
            }

        });

        f.add(b);//adding button in JFrame
        f.setSize(400, 500);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
    }
}
