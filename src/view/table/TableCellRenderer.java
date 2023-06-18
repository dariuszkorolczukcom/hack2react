package view.table;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TableCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        System.out.println(String.valueOf(row) + String.valueOf(column) + value);
        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Color color = Color.WHITE;

        if (column == 3) {
            color = switch (value.toString()) {
                case "GREEN" -> Color.GREEN;
                case "RED" -> Color.RED;
                case "YELLOW" -> Color.YELLOW;
                default -> Color.WHITE;
            };

            System.out.println(Color.getColor(value.toString()));
        }

        c.setBackground(color);
        return c;
    }

}
