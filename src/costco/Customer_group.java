import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Customer_group extends JPanel {
	
	private SQL sql;
	private JTable table;
	private JScrollPane scroll;
	private CusTableModel ctm;
		
	public Customer_group() {
		setPreferredSize(new Dimension(850, 550));
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER,15,5);
		setLayout(fl);		
		
		sql = new SQL();		
		ctm=new CusTableModel();
		scroll = new JScrollPane(createtable());
		add(scroll);
	}
	
	
	public JTable createtable() {			
		table =new JTable(ctm);
		table.setPreferredScrollableViewportSize(new Dimension(830,380));
		table.setRowHeight(25);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
		DefaultTableCellRenderer  renderer  =  new  DefaultTableCellRenderer();   
		renderer.setHorizontalAlignment(JTextField.CENTER);
		int y=table.getColumnCount();
		for(int i=0;i<y-1;i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(renderer);
		}
	     
		return table;
	}
	
	
	class CusTableModel extends AbstractTableModel{
		Object[][] data;
		String[] columns= {"ID","總購買金額", "最近一個月購買次數","最近購買日期"};
		public CusTableModel() {
			data=sql.selectCusGroup();			
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
			data=sql.selectcus();
		}
	 }
		
}