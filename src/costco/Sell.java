import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

public class Sell extends JPanel {
	private JTable table;
	private JScrollPane scroll;
	private JButton button1,button2,button3;
	private SQL sql;
	private TableModel mtm;
	
	public Sell() {
		setPreferredSize(new Dimension(850, 550));
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER,15,5);
		setLayout(fl);
		
		sql = new SQL();
		mtm=new TableModel();
		scroll = new JScrollPane(createtable());
		add(scroll);
		add(addbutton(), BorderLayout.CENTER);
	}
	public JTable createtable() {			
		table =new JTable(mtm);
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
	
	public JButton addbutton() {
		button1 = new JButton("新增");
		button1.setFont(new Font("微軟正黑體",Font.PLAIN,15));
		JFrame af = new JFrame();		
		af.setSize(450,250);
		af.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		af.setLocationRelativeTo(null);
		af.setTitle("新增資料");
		af.setLayout(new FlowLayout(FlowLayout.CENTER,10,50));
		JPanel jp = new JPanel();
		jp.setPreferredSize(new Dimension(420, 55));
		jp.setLayout(new GridLayout(2, 5, 10, 5));
		
		JLabel label = new JLabel("ID", SwingConstants.CENTER);
		JLabel label1 = new JLabel("時間", SwingConstants.CENTER);
		JLabel label2 = new JLabel("數量", SwingConstants.CENTER);
		JLabel label3 = new JLabel("顧客ID", SwingConstants.CENTER);
		JLabel label4 = new JLabel("商品ID", SwingConstants.CENTER);
		int n = Integer.parseInt(table.getValueAt((table.getRowCount()-1), 0).toString())+1;
		JTextField jt1 = new JTextField(""+n, SwingConstants.CENTER);
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");		
		JLabel labelt = new JLabel(ft.format(new Date()), SwingConstants.CENTER);		
		JTextField jt2 = new JTextField();
		JTextField jt3 = new JTextField();
		JTextField jt4 = new JTextField();
		JButton jb = new JButton("確定");
		jb.setSize(new Dimension(300,30));
		jp.add(label);
		jp.add(label1);
		jp.add(label2);
		jp.add(label3);
		jp.add(label4);
		jp.add(jt1);
		jp.add(labelt);
		jp.add(jt2);
		jp.add(jt3);
		jp.add(jt4);
		af.add(jp,BorderLayout.CENTER);
		af.add(jb,BorderLayout.SOUTH);
		class AddActionListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				int n = Integer.parseInt(table.getValueAt((table.getRowCount()-1), 0).toString())+1;
				jt1.setText(""+n);
				jt2.setText("");
				jt3.setText("");
				jt4.setText("");
				af.setVisible(true);
				jb.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {			
						try {
							sql.addsell(Integer.parseInt(jt1.getText()), labelt.getText(),
									Integer.parseInt(jt2.getText()), Integer.parseInt(jt3.getText()), Integer.parseInt(jt4.getText()));
							mtm.update();
							af.dispose();
						} catch (Exception e1) {							
							JOptionPane.showMessageDialog(null,"輸入有誤","錯誤", JOptionPane.ERROR_MESSAGE);							
						}
						table.validate();
						table.updateUI();											
					}					
				});
			}			
		}
		AddActionListener l = new AddActionListener();
		button1.addActionListener(l);
		return button1;
	}
	
	class TableModel extends AbstractTableModel{
		Object[][] data;
		String[] columns={"ID", "時間", "姓名", "商品名", "數量"};
		public TableModel() {
			data=sql.selectsell();			
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
			data=sql.selectsell();
		}
	 }
}
