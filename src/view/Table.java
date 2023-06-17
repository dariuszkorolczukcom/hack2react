package view;

import traverse.TableDataDto;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class Table extends AbstractTableModel {
    private final List<TableDataDto> data;
    String[] columnNames = {"file path",
            "creation time",
            "last action time",
            "danger level",
            "danger rate",
            "message"};

    public Table(List<TableDataDto> data) {
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
            case 3 -> dto.dangerLevel();
            case 4 -> dto.dangerRate();
            case 5 -> dto.dangerMessage();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
}
