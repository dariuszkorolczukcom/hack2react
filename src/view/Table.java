package view;

import traverse.TableDataDto;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class Table extends AbstractTableModel {
    String[] columnNames = {"file path",
            "creation time",
            "last action time"};
    private final List<TableDataDto> data;

    Table(List<TableDataDto> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TableDataDto dto = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> dto.path();
            case 1 -> dto.created();
            case 2 -> dto.modified();
            default -> null;
        };
    }
}
