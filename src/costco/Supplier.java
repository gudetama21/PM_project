

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

public class Supplier extends JPanel {
	private JTable table;
	private JScrollPane scroll;
	private JButton button1,button2,button3;
	private SQL sql;
	private STableModel smtm;
	
	public Supplier() {
		setPreferredSize(new Dimension(850, 550));
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER,15,5);
		setLayout(fl);
		
		sql = new SQL();
		smtm=new STableModel();
		scroll = new JScrollPane(createtable());
		add(scroll);
	}
	public JTable createtable() {			
		table =new JTable(smtm);
		table.setPreferredScrollableViewportSize(new Dimension(830,380));
		table.setRowHeight(25);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
		DefaultTableCellRenderer  renderer  =  new  DefaultTableCellRenderer();   
		renderer.setHorizontalAlignment(JTextField.CENTER);
		int y=table.getColumnCount();
		for(int i=0;i<y;i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(renderer);
		}
	     
		return table;
	}
	
	class STableModel extends AbstractTableModel{
		Object[][] data;
		String[] columns={"ID", "時間", "供應商", "商品名", "數量"};
		public STableModel() {
			data=sql.selectsupply();			
		}		
		public int getColumnCount() {return columns.length;}
		public int getRowCount() {return data.length;}
		public Object getValueAt(int row, int col) {return data[row][col];}
		public String getColumnName(int col) {return columns[col];}
		public Class getColumnClass(int col) {
			return getValueAt(0,col).getClass();
		}
		public boolean isCellEditable(int row,int col) {return true;}
		public void setValueAt(Object value,int row,int col) {
			data[row][col]=value;
			fireTableCellUpdated(row,col);
	    }
		public void update() throws SQLException {
			data=sql.selectsupply();
		}
	 }

}
