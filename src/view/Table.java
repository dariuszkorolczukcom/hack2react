package view;

public class Table {
	Object[][] data;
	Table(Object [][] data) {
		this.data = data;
	}
	String[] columnNames = {"file path",
            "creation time",
            "last action time"};
	
	public Object[][] getData() {
		return this.data;
	}
	
	public String[] getColumnNames() {
		return this.columnNames;
	}
	
}
