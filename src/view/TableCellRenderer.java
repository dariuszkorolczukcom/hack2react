package view;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
public class TableCellRenderer extends DefaultTableCellRenderer {
	public TableCellRenderer(){
		super();
	}
	
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
            final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            Color color = Color.WHITE;
            if (column == 3) {
            	switch (value.toString()) {
            		case "GREEN":
            			color = Color.GREEN;
            			break;
            		case "RED":
        				color = Color.RED;
        				break;
	            	case "YELLOW":
	        			color = Color.YELLOW;
	        			break;
	        		default:
	        			break;
            	}
            	System.out.println(Color.getColor(value.toString()));
            }
            
            c.setBackground(color);
            return c;
        }
    
}
